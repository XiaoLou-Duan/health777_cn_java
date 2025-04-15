package cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "用户 App - 乳清蛋白摄入记录 Response VO")
@Data
public class AppWheyProteinLogRespVO {

    @Schema(description = "乳清蛋白日志ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "摄入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime intakeTime;

    @Schema(description = "摄入量（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "15.0")
    private BigDecimal amountGrams;

    @Schema(description = "确认照片URL", example = "https://www.iocoder.cn/1.jpg")
    private String confirmationPhotoUrl;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
