package cn.iocoder.yudao.module.nutrition.dal.dataobject.ai;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * AI识别日志 DO
 *
 * @author 芋道源码
 */
@TableName("ai_recognition_logs")
@KeySequence("ai_recognition_logs_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AiRecognitionLogDO extends BaseDO {

    /**
     * 日志ID
     */
    @TableId
    private Long id;
    /**
     * 关联的饮食日志ID
     */
    private Long dietLogId;
    /**
     * 请求发送时间
     */
    private LocalDateTime requestTimestamp;
    /**
     * 请求内容摘要或ID
     */
    private String requestPayload;
    /**
     * 收到响应时间
     */
    private LocalDateTime responseTimestamp;
    /**
     * 响应内容摘要或结果
     */
    private String responsePayload;
    /**
     * 外部API响应状态码
     */
    private Integer statusCode;
    /**
     * 调用是否成功
     */
    private Boolean success;
    /**
     * 错误信息
     */
    private String errorMessage;

}
