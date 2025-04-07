package cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Schema(description = "管理后台 - 饮食建议分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DietRecommendationPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "1024")
    private Long userId;

    @Schema(description = "建议日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recommendationDate;

    @Schema(description = "是否已读", example = "0")
    private Integer isRead;

}