package cn.iocoder.yudao.module.nutrition.controller.app.mealfooditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "用户 APP - 餐食食物明细 Response VO")
@Data
public class AppMealFoodItemRespVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "餐食ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long mealId;

    @Schema(description = "食物ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2048")
    private Long foodItemId;

    @Schema(description = "食物名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "鸡胸肉")
    private String foodName;

    @Schema(description = "食用量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private BigDecimal quantity;

    @Schema(description = "蛋白质含量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "23.3")
    private BigDecimal protein;

    @Schema(description = "热量(kcal)", requiredMode = Schema.RequiredMode.REQUIRED, example = "113")
    private BigDecimal calories;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}