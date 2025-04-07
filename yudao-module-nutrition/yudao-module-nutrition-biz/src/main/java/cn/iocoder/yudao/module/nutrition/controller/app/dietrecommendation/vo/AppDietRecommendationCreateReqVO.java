package cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "用户APP - 饮食建议创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppDietRecommendationCreateReqVO extends DietRecommendationBaseVO {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "蛋白质缺口(克)", requiredMode = Schema.RequiredMode.REQUIRED, example = "50.0")
    @NotNull(message = "蛋白质缺口不能为空")
    private BigDecimal proteinGap;

    @Schema(description = "建议内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "建议增加蛋白质摄入")
    @NotNull(message = "建议内容不能为空")
    private String recommendationContent;

}