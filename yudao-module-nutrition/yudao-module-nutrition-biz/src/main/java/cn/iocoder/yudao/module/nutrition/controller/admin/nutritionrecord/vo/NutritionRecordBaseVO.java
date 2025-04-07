package cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

/**
 * 营养摄入记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class NutritionRecordBaseVO {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "记录日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDate recordDate;

    @Schema(description = "总蛋白质摄入量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "120.5")
    @NotNull(message = "总蛋白质摄入量不能为空")
    private BigDecimal totalProtein;

    @Schema(description = "总热量摄入量(kcal)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2500")
    @NotNull(message = "总热量摄入量不能为空")
    private BigDecimal totalCalories;

    @Schema(description = "蛋白质目标摄入量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "150")
    @NotNull(message = "蛋白质目标摄入量不能为空")
    private BigDecimal proteinTarget;

    @Schema(description = "热量目标摄入量(kcal)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2800")
    @NotNull(message = "热量目标摄入量不能为空")
    private BigDecimal caloriesTarget;

    @Schema(description = "达标率(%)", requiredMode = Schema.RequiredMode.REQUIRED, example = "85.5")
    private BigDecimal achievementRate;

}