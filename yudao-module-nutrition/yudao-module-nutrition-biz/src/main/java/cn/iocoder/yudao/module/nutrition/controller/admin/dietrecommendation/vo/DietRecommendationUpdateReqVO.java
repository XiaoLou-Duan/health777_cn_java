package cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 饮食建议更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DietRecommendationUpdateReqVO extends DietRecommendationBaseVO {

    @Schema(description = "建议ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "建议ID不能为空")
    private Long id;

}