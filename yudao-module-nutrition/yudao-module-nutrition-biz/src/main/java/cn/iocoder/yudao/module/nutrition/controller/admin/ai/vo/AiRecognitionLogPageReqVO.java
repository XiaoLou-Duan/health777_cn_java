package cn.iocoder.yudao.module.nutrition.controller.admin.ai.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - AI识别日志分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiRecognitionLogPageReqVO extends PageParam {

    @Schema(description = "关联的饮食日志ID", example = "1024")
    private Long dietLogId;

    @Schema(description = "调用是否成功", example = "true")
    private Boolean success;

    @Schema(description = "请求发送时间开始")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime requestTimeStart;

    @Schema(description = "请求发送时间结束")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime requestTimeEnd;

}
