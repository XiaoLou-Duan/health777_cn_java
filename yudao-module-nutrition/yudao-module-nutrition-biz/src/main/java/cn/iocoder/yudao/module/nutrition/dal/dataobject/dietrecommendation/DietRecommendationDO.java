package cn.iocoder.yudao.module.nutrition.dal.dataobject.dietrecommendation;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 饮食建议 DO
 */
@TableName("diet_recommendations")
@KeySequence("diet_recommendations_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DietRecommendationDO extends BaseDO {

    /**
     * 建议ID
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 建议日期
     */
    private LocalDate recommendationDate;

    /**
     * 蛋白质缺口(g)
     */
    private BigDecimal proteinGap;

    /**
     * 建议内容
     */
    private String recommendationContent;

    /**
     * 是否已读: 0-未读, 1-已读
     */
    private Integer isRead;

}