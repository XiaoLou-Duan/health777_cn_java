package cn.iocoder.yudao.module.nutrition.dal.dataobject.proteinsupplement;

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
 * 蛋白质补充剂摄入记录 DO
 */
@TableName("protein_supplements")
@KeySequence("protein_supplements_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProteinSupplementDO extends BaseDO {

    /**
     * 记录ID
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
    private LocalDateTime supplementTime;

    /**
     * 补充剂类型
     */
    private String supplementType;

    /**
     * 蛋白质含量(g)
     */
    private BigDecimal proteinAmount;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 备注
     */
    private String notes;

}