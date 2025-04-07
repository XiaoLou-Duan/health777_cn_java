package cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 餐食食物明细 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MealFoodItemBaseVO {

    @Schema(description = "餐食ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "餐食ID不能为空")
    private Long mealId;

    @Schema(description = "食物名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "鸡胸肉")
    @NotEmpty(message = "食物名称不能为空")
    private String foodName;

    @Schema(description = "食用量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @NotNull(message = "食用量不能为空")
    private BigDecimal quantity;

    @Schema(description = "蛋白质含量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "23.3")
    @NotNull(message = "蛋白质含量不能为空")
    private BigDecimal protein;

    @Schema(description = "热量(kcal)", requiredMode = Schema.RequiredMode.REQUIRED, example = "113")
    @NotNull(message = "热量不能为空")
    private BigDecimal calories;

    @Schema(description = "是否AI识别", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    @NotNull(message = "是否AI识别不能为空")
    private Boolean aiRecognized;
}