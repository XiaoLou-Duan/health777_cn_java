package cn.iocoder.yudao.module.nutrition.service.analysis;

import cn.iocoder.yudao.module.nutrition.controller.app.analysis.vo.AppNutritionAnalysisRespVO;

import java.time.LocalDate;

/**
 * 营养分析 Service 接口
 *
 * @author 芋道源码
 */
public interface NutritionAnalysisService {

    /**
     * 获取用户指定日期的营养分析
     *
     * @param userId 用户编号
     * @param date 日期
     * @return 营养分析结果
     */
    AppNutritionAnalysisRespVO getNutritionAnalysis(Long userId, LocalDate date);

}
