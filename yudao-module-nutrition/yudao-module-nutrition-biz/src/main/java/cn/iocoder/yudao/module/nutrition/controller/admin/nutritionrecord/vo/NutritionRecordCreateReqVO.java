package cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 营养摄入记录创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NutritionRecordCreateReqVO extends NutritionRecordBaseVO {

}