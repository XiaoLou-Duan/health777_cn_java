package cn.iocoder.yudao.module.nutrition.dal.dataobject.wheyprotein;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 乳清蛋白摄入记录 DO
 *
 * @author 芋道源码
 */
@TableName("whey_protein_logs")
@KeySequence("whey_protein_logs_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WheyProteinLogDO extends BaseDO {

    /**
     * 乳清蛋白日志ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 摄入时间
     */
    private LocalDateTime intakeTime;
    /**
     * 摄入量（克）
     */
    private BigDecimal amountGrams;
    /**
     * 确认照片URL
     */
    private String confirmationPhotoUrl;

}
