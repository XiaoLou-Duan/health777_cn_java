package cn.iocoder.yudao.module.nutrition.service.analysis;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.member.api.user.MemberUserApi;
import cn.iocoder.yudao.module.member.api.user.dto.MemberUserRespDTO;
import cn.iocoder.yudao.module.nutrition.controller.app.analysis.vo.AppNutritionAnalysisRespVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietlog.DietLogDO;
import cn.iocoder.yudao.module.nutrition.enums.diet.DietMealTypeEnum;
import cn.iocoder.yudao.module.nutrition.service.dietlog.DietLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 营养分析 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class NutritionAnalysisServiceImpl implements NutritionAnalysisService {

    @Resource
    private DietLogService dietLogService;

    @Resource
    private MemberUserApi memberUserApi;

    @Override
    public AppNutritionAnalysisRespVO getNutritionAnalysis(Long userId, LocalDate date) {
        // 获取用户信息，包括蛋白质和热量目标
        MemberUserRespDTO user = memberUserApi.getUser(userId);
        
        // 获取用户当天的饮食日志
        List<DietLogDO> dietLogs = dietLogService.getDietLogListByUserIdAndDate(userId, date);
        
        // 创建分析结果对象
        AppNutritionAnalysisRespVO analysis = new AppNutritionAnalysisRespVO();
        analysis.setDate(date);
        
        // 设置目标值
        BigDecimal proteinTarget = user.getProteinTarget() != null ? user.getProteinTarget() : new BigDecimal("100");
        BigDecimal caloriesTarget = user.getCaloriesTarget() != null ? user.getCaloriesTarget() : new BigDecimal("2000");
        analysis.setProteinTarget(proteinTarget);
        analysis.setCaloriesTarget(caloriesTarget);
        
        // 如果没有饮食记录，返回空分析
        if (CollUtil.isEmpty(dietLogs)) {
            analysis.setActualProtein(BigDecimal.ZERO);
            analysis.setActualCalories(BigDecimal.ZERO);
            analysis.setProteinAchievementRate(BigDecimal.ZERO);
            analysis.setCaloriesAchievementRate(BigDecimal.ZERO);
            analysis.setProteinGap(proteinTarget);
            analysis.setCaloriesGap(caloriesTarget);
            analysis.setDietSuggestion("今天还没有记录饮食，请记得及时记录您的饮食情况。");
            analysis.setMealNutritions(new ArrayList<>());
            return analysis;
        }
        
        // 计算实际摄入的蛋白质和热量总量
        BigDecimal actualProtein = dietLogs.stream()
                .map(DietLogDO::getEstimatedProtein)
                .filter(p -> p != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal actualCalories = dietLogs.stream()
                .map(DietLogDO::getEstimatedCalories)
                .filter(c -> c != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        analysis.setActualProtein(actualProtein);
        analysis.setActualCalories(actualCalories);
        
        // 计算达标率
        BigDecimal proteinAchievementRate = proteinTarget.compareTo(BigDecimal.ZERO) > 0 
                ? actualProtein.multiply(new BigDecimal("100")).divide(proteinTarget, 1, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        
        BigDecimal caloriesAchievementRate = caloriesTarget.compareTo(BigDecimal.ZERO) > 0
                ? actualCalories.multiply(new BigDecimal("100")).divide(caloriesTarget, 1, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        
        analysis.setProteinAchievementRate(proteinAchievementRate);
        analysis.setCaloriesAchievementRate(caloriesAchievementRate);
        
        // 计算缺口
        BigDecimal proteinGap = proteinTarget.subtract(actualProtein);
        if (proteinGap.compareTo(BigDecimal.ZERO) < 0) {
            proteinGap = BigDecimal.ZERO;
        }
        
        BigDecimal caloriesGap = caloriesTarget.subtract(actualCalories);
        if (caloriesGap.compareTo(BigDecimal.ZERO) < 0) {
            caloriesGap = BigDecimal.ZERO;
        }
        
        analysis.setProteinGap(proteinGap);
        analysis.setCaloriesGap(caloriesGap);
        
        // 生成饮食建议
        analysis.setDietSuggestion(generateDietSuggestion(proteinGap, caloriesGap, proteinAchievementRate, caloriesAchievementRate));
        
        // 按餐次分组
        Map<Integer, List<DietLogDO>> mealTypeMap = dietLogs.stream()
                .collect(Collectors.groupingBy(DietLogDO::getMealType));
        
        // 创建各餐次营养摄入详情
        List<AppNutritionAnalysisRespVO.MealNutrition> mealNutritions = new ArrayList<>();
        for (DietMealTypeEnum mealType : DietMealTypeEnum.values()) {
            List<DietLogDO> mealLogs = mealTypeMap.getOrDefault(mealType.getType(), new ArrayList<>());
            if (CollUtil.isNotEmpty(mealLogs)) {
                AppNutritionAnalysisRespVO.MealNutrition mealNutrition = new AppNutritionAnalysisRespVO.MealNutrition();
                mealNutrition.setMealType(mealType.getType());
                mealNutrition.setMealTypeName(mealType.getName());
                
                // 计算该餐次的蛋白质和热量总量
                BigDecimal mealProtein = mealLogs.stream()
                        .map(DietLogDO::getEstimatedProtein)
                        .filter(p -> p != null)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                BigDecimal mealCalories = mealLogs.stream()
                        .map(DietLogDO::getEstimatedCalories)
                        .filter(c -> c != null)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                mealNutrition.setProtein(mealProtein);
                mealNutrition.setCalories(mealCalories);
                
                // 合并该餐次的食物列表
                String foods = mealLogs.stream()
                        .map(DietLogDO::getRecognizedFoods)
                        .filter(f -> f != null && !f.isEmpty())
                        .collect(Collectors.joining(","));
                
                mealNutrition.setFoods(foods);
                
                mealNutritions.add(mealNutrition);
            }
        }
        
        analysis.setMealNutritions(mealNutritions);
        
        return analysis;
    }
    
    /**
     * 根据营养缺口生成饮食建议
     */
    private String generateDietSuggestion(BigDecimal proteinGap, BigDecimal caloriesGap, 
                                         BigDecimal proteinAchievementRate, BigDecimal caloriesAchievementRate) {
        StringBuilder suggestion = new StringBuilder();
        
        // 蛋白质建议
        if (proteinAchievementRate.compareTo(new BigDecimal("90")) >= 0) {
            suggestion.append("您今天的蛋白质摄入已接近目标，做得很好！");
        } else if (proteinAchievementRate.compareTo(new BigDecimal("70")) >= 0) {
            suggestion.append("您今天的蛋白质摄入略有不足，还差约").append(proteinGap.setScale(1, RoundingMode.HALF_UP))
                    .append("克蛋白质。建议增加一些高蛋白食物，如鸡胸肉、鱼肉或豆制品。");
        } else {
            suggestion.append("您今天的蛋白质摄入严重不足，还差约").append(proteinGap.setScale(1, RoundingMode.HALF_UP))
                    .append("克蛋白质。建议增加高蛋白食物，如100克鸡胸肉(约24克蛋白质)、150克鱼肉(约30克蛋白质)或两个鸡蛋(约12克蛋白质)。");
        }
        
        suggestion.append(" ");
        
        // 热量建议
        if (caloriesAchievementRate.compareTo(new BigDecimal("90")) >= 0) {
            suggestion.append("您今天的热量摄入已接近目标，能量供应充足。");
        } else if (caloriesAchievementRate.compareTo(new BigDecimal("70")) >= 0) {
            suggestion.append("您今天的热量摄入略有不足，还差约").append(caloriesGap.setScale(0, RoundingMode.HALF_UP))
                    .append("千卡。建议适当增加主食或健康脂肪的摄入。");
        } else {
            suggestion.append("您今天的热量摄入严重不足，还差约").append(caloriesGap.setScale(0, RoundingMode.HALF_UP))
                    .append("千卡。建议增加主食、坚果或健康油脂的摄入，确保有足够的能量支持日常活动。");
        }
        
        return suggestion.toString();
    }
}
