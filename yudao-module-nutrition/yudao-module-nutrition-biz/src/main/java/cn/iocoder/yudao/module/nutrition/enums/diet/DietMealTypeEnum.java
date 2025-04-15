package cn.iocoder.yudao.module.nutrition.enums.diet;

import cn.iocoder.yudao.framework.common.core.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 餐次类型枚举
 *
 * @author 芋道源码
 */
@Getter
@AllArgsConstructor
public enum DietMealTypeEnum implements ArrayValuable<Integer> {

    BREAKFAST(1, "早餐"),
    LUNCH(2, "午餐"),
    DINNER(3, "晚餐"),
    SNACK(4, "加餐");

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(DietMealTypeEnum::getType).toArray(Integer[]::new);

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 名称
     */
    private final String name;

    @Override
    public Integer[] array() {
        return ARRAYS;
    }

}
