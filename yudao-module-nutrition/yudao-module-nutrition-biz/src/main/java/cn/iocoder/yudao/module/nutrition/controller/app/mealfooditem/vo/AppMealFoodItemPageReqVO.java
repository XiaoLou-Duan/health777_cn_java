package cn.iocoder.yudao.module.nutrition.controller.app.mealfooditem.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "用户 APP - 餐食食物明细分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppMealFoodItemPageReqVO extends PageParam {

    @Schema(description = "餐食ID", example = "1024")
    private Long mealId;

    @Schema(description = "食物名称", example = "鸡胸肉")
    private String foodName;

    @Schema(description = "是否AI识别", example = "true")
    private Boolean aiRecognized;

}