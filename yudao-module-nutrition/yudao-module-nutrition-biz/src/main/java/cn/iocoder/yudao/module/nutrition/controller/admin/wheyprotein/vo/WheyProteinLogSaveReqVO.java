package cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 乳清蛋白摄入记录创建/更新 Request VO")
@Data
public class WheyProteinLogSaveReqVO {

    @Schema(description = "乳清蛋白日志ID", example = "1024")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "摄入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "摄入时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime intakeTime;

    @Schema(description = "摄入量（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "15.0")
    @NotNull(message = "摄入量不能为空")
    private BigDecimal amountGrams;

    @Schema(description = "确认照片URL", example = "https://www.iocoder.cn/1.jpg")
    private String confirmationPhotoUrl;

}
