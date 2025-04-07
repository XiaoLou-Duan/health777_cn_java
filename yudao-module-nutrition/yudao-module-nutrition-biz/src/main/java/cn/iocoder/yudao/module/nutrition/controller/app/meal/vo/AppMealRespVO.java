package cn.iocoder.yudao.module.nutrition.controller.app.meal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "用户 APP - 用餐记录 Response VO")
@Data
public class AppMealRespVO {

    @Schema(description = "餐食ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long userId;

    @Schema(description = "餐食类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer mealType;

    @Schema(description = "用餐时间", requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}