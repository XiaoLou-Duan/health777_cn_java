package cn.iocoder.yudao.module.nutrition.controller.app.nutritionrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

@Schema(description = "用户 APP - 营养摄入记录 Response VO")
@Data
public class AppNutritionRecordRespVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long userId;

    @Schema(description = "记录日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDate recordDate;

    @Schema(description = "总蛋白质摄入量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "120.5")
    private BigDecimal totalProtein;

    @Schema(description = "总热量摄入量(kcal)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2500")
    private BigDecimal totalCalories;

    @Schema(description = "蛋白质目标摄入量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "150")
    private BigDecimal proteinTarget;

    @Schema(description = "热量目标摄入量(kcal)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2800")
    private BigDecimal caloriesTarget;

    @Schema(description = "达标率(%)", requiredMode = Schema.RequiredMode.REQUIRED, example = "85.5")
    private BigDecimal achievementRate;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}