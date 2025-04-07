package cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 蛋白质补充剂摄入记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProteinSupplementBaseVO {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "摄入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "摄入时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime supplementTime;

    @Schema(description = "补充剂类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "乳清蛋白")
    @NotNull(message = "补充剂类型不能为空")
    private String supplementType;

    @Schema(description = "蛋白质含量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "30.5")
    @NotNull(message = "蛋白质含量不能为空")
    private BigDecimal proteinAmount;

    @Schema(description = "图片URL", example = "https://example.com/images/protein.jpg")
    private String imageUrl;

    @Schema(description = "备注", example = "训练前摄入")
    private String notes;

}