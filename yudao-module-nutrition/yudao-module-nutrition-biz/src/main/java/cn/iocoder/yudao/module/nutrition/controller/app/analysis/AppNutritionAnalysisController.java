package cn.iocoder.yudao.module.nutrition.controller.app.analysis;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.nutrition.controller.app.analysis.vo.AppNutritionAnalysisRespVO;
import cn.iocoder.yudao.module.nutrition.service.analysis.NutritionAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

@Tag(name = "用户 App - 营养分析")
@RestController
@RequestMapping("/nutrition/analysis")
@Validated
public class AppNutritionAnalysisController {

    @Resource
    private NutritionAnalysisService nutritionAnalysisService;

    @GetMapping("/get")
    @Operation(summary = "获得营养分析")
    @Parameter(name = "date", description = "日期", required = true, example = "2023-01-01")
    public CommonResult<AppNutritionAnalysisRespVO> getNutritionAnalysis(@RequestParam("date") @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY) LocalDate date) {
        return success(nutritionAnalysisService.getNutritionAnalysis(SecurityFrameworkUtils.getLoginUserId(), date));
    }

}
