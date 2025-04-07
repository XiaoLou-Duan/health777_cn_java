package cn.iocoder.yudao.module.nutrition.dal.dataobject.mealfooditem;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 餐食食物明细 DO
 */
@TableName("meal_food_items")
@KeySequence("meal_food_items_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MealFoodItemDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long id;

    /**
     * 餐食ID
     */
    private Long mealId;

    /**
     * 食物名称
     */
    private String foodName;

    /**
     * 食用量(g)
     */
    private BigDecimal quantity;

    /**
     * 蛋白质含量(g)
     */
    private BigDecimal protein;

    /**
     * 热量(kcal)
     */
    private BigDecimal calories;

    /**
     * 是否AI识别
     */
    private Boolean aiRecognized;
}