package cn.iocoder.yudao.module.nutrition.service.nutritionrecord.impl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.nutritionrecord.vo.AppNutritionRecordPageReqVO;
import cn.iocoder.yudao.module.nutrition.convert.nutritionrecord.NutritionRecordConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.nutritionrecord.NutritionRecordDO;
import cn.iocoder.yudao.module.nutrition.dal.mysql.nutritionrecord.NutritionRecordMapper;
import cn.iocoder.yudao.module.nutrition.service.nutritionrecord.NutritionRecordService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.nutrition.enums.ErrorCodeConstants.NUTRITION_RECORD_NOT_EXISTS;

/**
 * 营养摄入记录 Service 实现类
 */
@Service
@Validated
public class NutritionRecordServiceImpl implements NutritionRecordService {

    @Resource
    private NutritionRecordMapper nutritionRecordMapper;

    @Override
    public Long createNutritionRecord(NutritionRecordCreateReqVO createReqVO) {
        // 计算达标率
        BigDecimal achievementRate = calculateAchievementRate(createReqVO.getTotalProtein(),
                createReqVO.getProteinTarget(),
                createReqVO.getTotalCalories(), createReqVO.getCaloriesTarget());

        // 插入
        NutritionRecordDO nutritionRecord = NutritionRecordConvert.INSTANCE.convert(createReqVO);
        nutritionRecord.setAchievementRate(achievementRate);
        nutritionRecordMapper.insert(nutritionRecord);
        // 返回
        return nutritionRecord.getId();
    }

    @Override
    public void updateNutritionRecord(NutritionRecordUpdateReqVO updateReqVO) {
        // 校验存在
        validateNutritionRecordExists(updateReqVO.getId());

        // 计算达标率
        BigDecimal achievementRate = calculateAchievementRate(updateReqVO.getTotalProtein(),
                updateReqVO.getProteinTarget(),
                updateReqVO.getTotalCalories(), updateReqVO.getCaloriesTarget());

        // 更新
        NutritionRecordDO updateObj = NutritionRecordConvert.INSTANCE.convert(updateReqVO);
        updateObj.setAchievementRate(achievementRate);
        nutritionRecordMapper.updateById(updateObj);
    }

    /**
     * 计算达标率
     * 
     * @param totalProtein   实际蛋白质摄入量
     * @param proteinTarget  蛋白质目标摄入量
     * @param totalCalories  实际热量摄入量
     * @param caloriesTarget 热量目标摄入量
     * @return 达标率(%)
     */
    private BigDecimal calculateAchievementRate(BigDecimal totalProtein, BigDecimal proteinTarget,
            BigDecimal totalCalories, BigDecimal caloriesTarget) {
        // 蛋白质达标率
        BigDecimal proteinRate = totalProtein.divide(proteinTarget, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        // 热量达标率
        BigDecimal caloriesRate = totalCalories.divide(caloriesTarget, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        // 综合达标率（权重可根据需要调整）
        BigDecimal weightProtein = BigDecimal.valueOf(0.6); // 蛋白质权重60%
        BigDecimal weightCalories = BigDecimal.valueOf(0.4); // 热量权重40%

        return proteinRate.multiply(weightProtein).add(caloriesRate.multiply(weightCalories))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void deleteNutritionRecord(Long id) {
        // 校验存在
        validateNutritionRecordExists(id);
        // 删除
        nutritionRecordMapper.deleteById(id);
    }

    private void validateNutritionRecordExists(Long id) {
        if (nutritionRecordMapper.selectById(id) == null) {
            throw exception(NUTRITION_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public NutritionRecordDO getNutritionRecord(Long id) {
        return nutritionRecordMapper.selectById(id);
    }

    @Override
    public NutritionRecordDO getNutritionRecordByUserIdAndDate(Long userId, LocalDate recordDate) {
        return nutritionRecordMapper.selectByUserIdAndRecordDate(userId, recordDate);
    }

    @Override
    public List<NutritionRecordDO> getNutritionRecordListByUserId(Long userId) {
        return nutritionRecordMapper.selectListByUserId(userId);
    }

    @Override
    public PageResult<NutritionRecordDO> getNutritionRecordPage(NutritionRecordPageReqVO pageReqVO) {
        return nutritionRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<NutritionRecordDO> getNutritionRecordPage(AppNutritionRecordPageReqVO pageReqVO) {
        return nutritionRecordMapper.selectPage(pageReqVO);
    }
}