package cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 饮食日志创建/更新 Request VO")
@Data
public class DietLogSaveReqVO {

    @Schema(description = "饮食日志ID", example = "1024")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "餐次类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "餐次类型不能为空")
    private Integer mealType;

    @Schema(description = "记录时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime logTime;

    @Schema(description = "食物照片URL", example = "https://www.iocoder.cn/1.jpg")
    private String photoUrl;

    @Schema(description = "AI识别出的食物文本描述", example = "米饭,鸡肉,青菜")
    private String recognizedFoods;

    @Schema(description = "估算蛋白质（克）", example = "20.5")
    private BigDecimal estimatedProtein;

    @Schema(description = "估算热量（千卡）", example = "350.5")
    private BigDecimal estimatedCalories;

    @Schema(description = "用户备注", example = "午餐吃得比较清淡")
    private String userNotes;

}
