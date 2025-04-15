package cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "用户 App - 乳清蛋白摄入报告 Response VO")
@Data
public class AppWheyProteinReportRespVO {

    @Schema(description = "报告开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate startDate;

    @Schema(description = "报告结束日期", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate endDate;

    @Schema(description = "总摄入次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "14")
    private Integer totalIntakeCount;

    @Schema(description = "总摄入量（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "210.0")
    private BigDecimal totalIntakeAmount;

    @Schema(description = "日均摄入次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2.0")
    private BigDecimal averageDailyIntakeCount;

    @Schema(description = "日均摄入量（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30.0")
    private BigDecimal averageDailyIntakeAmount;

    @Schema(description = "每日摄入详情")
    private List<DailyIntake> dailyIntakes;

    @Schema(description = "每日摄入详情")
    @Data
    public static class DailyIntake {

        @Schema(description = "日期", requiredMode = Schema.RequiredMode.REQUIRED)
        private LocalDate date;

        @Schema(description = "摄入次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
        private Integer intakeCount;

        @Schema(description = "摄入量（克）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30.0")
        private BigDecimal intakeAmount;
    }

}
