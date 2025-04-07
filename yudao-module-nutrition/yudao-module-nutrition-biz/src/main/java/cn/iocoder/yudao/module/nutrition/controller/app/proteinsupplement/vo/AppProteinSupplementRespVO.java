package cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "用户 APP - 蛋白质补充剂摄入记录 Response VO")
@Data
public class AppProteinSupplementRespVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long userId;

    @Schema(description = "摄入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime supplementTime;

    @Schema(description = "补充剂类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "乳清蛋白")
    private String supplementType;

    @Schema(description = "蛋白质含量(g)", requiredMode = Schema.RequiredMode.REQUIRED, example = "30.5")
    private BigDecimal proteinAmount;

    @Schema(description = "图片URL", example = "https://example.com/images/protein.jpg")
    private String imageUrl;

    @Schema(description = "备注", example = "训练前摄入")
    private String notes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}