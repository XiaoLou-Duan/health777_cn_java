package cn.iocoder.yudao.module.nutrition.dal.dataobject.nutritionrecord;

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
 * 营养摄入记录 DO
 */
@TableName("nutrition_records")
@KeySequence("nutrition_records_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NutritionRecordDO extends BaseDO {

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
     * 记录日期
     */
    private LocalDate recordDate;

    /**
     * 总蛋白质摄入量(g)
     */
    private BigDecimal totalProtein;

    /**
     * 总热量摄入量(kcal)
     */
    private BigDecimal totalCalories;

    /**
     * 蛋白质目标摄入量(g)
     */
    private BigDecimal proteinTarget;

    /**
     * 热量目标摄入量(kcal)
     */
    private BigDecimal caloriesTarget;

    /**
     * 达标率(%)
     */
    private BigDecimal achievementRate;

    /**
     * 蛋白质缺口(g)
     */
    private BigDecimal proteinGap;
}