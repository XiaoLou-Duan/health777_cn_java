package cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

/**
 * 饮食建议 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DietRecommendationBaseVO {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "建议日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "建议日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDate recommendationDate;

    @Schema(description = "蛋白质缺口(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "30.5")
    @NotNull(message = "蛋白质缺口不能为空")
    private BigDecimal proteinGap;

    @Schema(description = "建议内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "建议增加蛋白质摄入量")
    @NotNull(message = "建议内容不能为空")
    private String recommendationContent;

    @Schema(description = "是否已读: 0-未读, 1-已读", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "是否已读不能为空")
    private Integer isRead;

}