package cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 饮食建议创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DietRecommendationCreateReqVO extends DietRecommendationBaseVO {

}