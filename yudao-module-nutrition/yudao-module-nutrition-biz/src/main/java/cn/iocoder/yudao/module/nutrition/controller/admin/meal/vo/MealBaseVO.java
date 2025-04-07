package cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用餐记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MealBaseVO {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "餐食类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "餐食类型不能为空")
    private Integer mealType;

    @Schema(description = "用餐时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用餐时间不能为空")
    private LocalDateTime mealTime;

    @Schema(description = "餐食图片URL", example = "https://example.com/images/meal.jpg")
    private String imageUrl;

    @Schema(description = "是否AI识别", example = "true")
    private Boolean aiRecognized;

    @Schema(description = "总蛋白质含量(g)", example = "30.5")
    private BigDecimal totalProtein;

    @Schema(description = "总热量(kcal)", example = "500")
    private BigDecimal totalCalories;

    @Schema(description = "备注", example = "午餐吃得很健康")
    private String notes;

}