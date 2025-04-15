package cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "用户 App - 乳清蛋白摄入记录创建 Request VO")
@Data
public class AppWheyProteinLogCreateReqVO {

    @Schema(description = "摄入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "摄入时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime intakeTime;

    @Schema(description = "摄入量（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "15.0")
    @NotNull(message = "摄入量不能为空")
    private BigDecimal amountGrams;

    @Schema(description = "确认照片", requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile photo;

}
