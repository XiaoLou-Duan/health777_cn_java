package cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DietRecommendationBaseVO {

    @Schema(description = "建议日期", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate recommendationDate;

    @Schema(description = "蛋白质缺口(g)", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal proteinGap;

    @Schema(description = "建议内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private String recommendationContent;

    @Schema(description = "是否已读", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isRead;
}