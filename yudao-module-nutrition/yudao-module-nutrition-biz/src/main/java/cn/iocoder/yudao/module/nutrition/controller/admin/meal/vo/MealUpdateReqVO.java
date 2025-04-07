package cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 用餐记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MealUpdateReqVO extends MealBaseVO {

    @Schema(description = "餐食ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "餐食ID不能为空")
    private Long id;

}