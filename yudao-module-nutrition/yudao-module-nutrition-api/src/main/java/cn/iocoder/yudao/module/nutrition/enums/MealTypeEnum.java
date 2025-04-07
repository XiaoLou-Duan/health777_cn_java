package cn.iocoder.yudao.module.nutrition.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 餐食类型枚举
 */
@Getter
@AllArgsConstructor
public enum MealTypeEnum {

    BREAKFAST(1, "早餐"),
    LUNCH(2, "午餐"),
    DINNER(3, "晚餐"),
    SNACK(4, "加餐");

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 描述
     */
    private final String desc;

}