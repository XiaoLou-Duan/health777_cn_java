package cn.iocoder.yudao.module.nutrition.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 营养模块的错误码枚举类
 *
 * 营养模块，使用 1-023-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 食物项目相关 1023001000 ==========
    ErrorCode FOOD_ITEM_NOT_EXISTS = new ErrorCode(1023001000, "食物项目不存在");

    // ========== 餐食相关 1023002000 ==========
    ErrorCode MEAL_NOT_EXISTS = new ErrorCode(1023002000, "餐食不存在");

    // ========== 餐食食物相关 1023003000 ==========
    ErrorCode MEAL_FOOD_ITEM_NOT_EXISTS = new ErrorCode(1023003000, "餐食食物不存在");

    // ========== 蛋白质补充剂相关 1023004000 ==========
    ErrorCode PROTEIN_SUPPLEMENT_NOT_EXISTS = new ErrorCode(1023004000, "蛋白质补充剂不存在");

    // ========== 营养记录相关 1023005000 ==========
    ErrorCode NUTRITION_RECORD_NOT_EXISTS = new ErrorCode(1023005000, "营养记录不存在");

    // ========== 饮食推荐相关 1023006000 ==========
    ErrorCode DIET_RECOMMENDATION_NOT_EXISTS = new ErrorCode(1002001000, "饮食建议不存在");

}