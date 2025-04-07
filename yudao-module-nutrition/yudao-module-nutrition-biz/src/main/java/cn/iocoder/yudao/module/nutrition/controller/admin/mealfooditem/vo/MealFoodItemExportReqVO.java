package cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 餐食食物明细导出 Request VO")
@Data
public class MealFoodItemExportReqVO {

    @Schema(description = "餐食ID", example = "1024")
    private Long mealId;

    @Schema(description = "食物名称", example = "鸡胸肉")
    private String foodName;

    @Schema(description = "是否AI识别", example = "true")
    private Boolean aiRecognized;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] createTime;

}