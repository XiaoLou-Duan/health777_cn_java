package cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 用餐记录导出 Request VO")
@Data
public class MealExportReqVO {

    @Schema(description = "用户ID", example = "1024")
    private Long userId;

    @Schema(description = "餐食类型", example = "1")
    private Integer mealType;

    @Schema(description = "用餐时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] mealTime;

    @Schema(description = "是否AI识别", example = "true")
    private Boolean aiRecognized;

}