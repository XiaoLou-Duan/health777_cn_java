package cn.iocoder.yudao.module.nutrition.dal.dataobject.dietlog;

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
 * 饮食日志 DO
 *
 * @author 芋道源码
 */
@TableName("diet_logs")
@KeySequence("diet_logs_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DietLogDO extends BaseDO {

    /**
     * 饮食日志ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 餐次类型
     *
     * 枚举 {@link cn.iocoder.yudao.module.nutrition.enums.diet.DietMealTypeEnum}
     */
    private Integer mealType;
    /**
     * 记录时间（用户实际用餐时间）
     */
    private LocalDateTime logTime;
    /**
     * 食物照片URL
     */
    private String photoUrl;
    /**
     * AI识别出的食物文本描述
     */
    private String recognizedFoods;
    /**
     * 估算蛋白质（克）
     */
    private BigDecimal estimatedProtein;
    /**
     * 估算热量（千卡）
     */
    private BigDecimal estimatedCalories;
    /**
     * 用户备注
     */
    private String userNotes;

}
