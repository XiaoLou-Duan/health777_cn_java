package cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "用户 App - 饮食日志更新 Request VO")
@Data
public class AppDietLogUpdateReqVO {

    @Schema(description = "饮食日志ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "饮食日志ID不能为空")
    private Long id;

    @Schema(description = "AI识别出的食物文本描述", example = "米饭,鸡肉,青菜")
    private String recognizedFoods;

    @Schema(description = "估算蛋白质（克）", example = "20.5")
    private BigDecimal estimatedProtein;

    @Schema(description = "估算热量（千卡）", example = "350.5")
    private BigDecimal estimatedCalories;

    @Schema(description = "用户备注", example = "午餐吃得比较清淡")
    private String userNotes;

}
