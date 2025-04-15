package cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "用户 App - 饮食日志 Response VO")
@Data
public class AppDietLogRespVO {

    @Schema(description = "饮食日志ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "餐次类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer mealType;

    @Schema(description = "记录时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime logTime;

    @Schema(description = "食物照片URL", example = "https://www.iocoder.cn/1.jpg")
    private String photoUrl;

    @Schema(description = "AI识别出的食物文本描述", example = "米饭,鸡肉,青菜")
    private String recognizedFoods;

    @Schema(description = "估算蛋白质（克）", example = "20.5")
    private BigDecimal estimatedProtein;

    @Schema(description = "估算热量（千卡）", example = "350.5")
    private BigDecimal estimatedCalories;

    @Schema(description = "用户备注", example = "午餐吃得比较清淡")
    private String userNotes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
