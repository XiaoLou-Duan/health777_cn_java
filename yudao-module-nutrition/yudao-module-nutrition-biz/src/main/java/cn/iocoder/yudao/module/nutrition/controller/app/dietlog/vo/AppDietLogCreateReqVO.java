package cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "用户 App - 饮食日志创建 Request VO")
@Data
public class AppDietLogCreateReqVO {

    @Schema(description = "餐次类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "餐次类型不能为空")
    private Integer mealType;

    @Schema(description = "记录时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime logTime;

    @Schema(description = "食物照片", requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile photo;

    @Schema(description = "用户备注", example = "午餐吃得比较清淡")
    private String userNotes;

}
