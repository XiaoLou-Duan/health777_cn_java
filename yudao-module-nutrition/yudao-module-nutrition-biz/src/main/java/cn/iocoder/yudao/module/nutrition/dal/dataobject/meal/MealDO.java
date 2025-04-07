package cn.iocoder.yudao.module.nutrition.dal.dataobject.meal;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.module.nutrition.enums.MealTypeEnum;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户餐食记录 DO
 */
@TableName("meals")
@KeySequence("meals_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MealDO extends BaseDO {

    /**
     * 餐食ID
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 餐食类型: 1-早餐, 2-午餐, 3-晚餐, 4-加餐
     *
     * 枚举 {@link MealTypeEnum}
     */
    private Integer mealType;

    /**
     * 用餐时间
     */
    private LocalDateTime mealTime;

    /**
     * 餐食图片URL
     */
    private String imageUrl;

    /**
     * 是否AI识别: 0-否, 1-是
     */
    private Boolean aiRecognized;

    /**
     * 总蛋白质含量(g)
     */
    private BigDecimal totalProtein;

    /**
     * 总热量(kcal)
     */
    private BigDecimal totalCalories;

    /**
     * 备注
     */
    private String notes;
}