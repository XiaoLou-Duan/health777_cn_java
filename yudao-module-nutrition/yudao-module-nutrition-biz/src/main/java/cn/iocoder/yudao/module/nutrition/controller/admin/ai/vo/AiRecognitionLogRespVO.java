package cn.iocoder.yudao.module.nutrition.controller.admin.ai.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - AI识别日志 Response VO")
@Data
public class AiRecognitionLogRespVO {

    @Schema(description = "日志ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "关联的饮食日志ID", example = "1024")
    private Long dietLogId;

    @Schema(description = "请求发送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime requestTimestamp;

    @Schema(description = "请求内容摘要或ID", example = "请求识别食物图片")
    private String requestPayload;

    @Schema(description = "收到响应时间")
    private LocalDateTime responseTimestamp;

    @Schema(description = "响应内容摘要或结果", example = "识别结果：米饭,鸡肉,青菜")
    private String responsePayload;

    @Schema(description = "外部API响应状态码", example = "200")
    private Integer statusCode;

    @Schema(description = "调用是否成功", example = "true")
    private Boolean success;

    @Schema(description = "错误信息", example = "API调用超时")
    private String errorMessage;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
