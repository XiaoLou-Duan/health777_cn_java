package cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 餐食食物明细创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MealFoodItemCreateReqVO extends MealFoodItemBaseVO {

}