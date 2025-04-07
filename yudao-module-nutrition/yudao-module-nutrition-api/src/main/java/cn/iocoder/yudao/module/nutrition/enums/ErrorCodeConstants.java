package cn.iocoder.yudao.module.nutrition.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 营养模块错误码枚举类
 *
 * 营养模块，使用 1002001xxx 段
 */
public interface ErrorCodeConstants {

    // ========== 营养摄入记录相关 1002001000 ==========
    ErrorCode NUTRITION_RECORD_NOT_EXISTS = new ErrorCode(1002001000, "营养摄入记录不存在");

    // ========== 蛋白质补充剂摄入相关 1002001200 ==========
    ErrorCode PROTEIN_SUPPLEMENT_NOT_EXISTS = new ErrorCode(1002001200, "蛋白质补充剂摄入记录不存在");

    // ========== 餐食相关 1002001400 ==========
    ErrorCode MEAL_NOT_EXISTS = new ErrorCode(1002001400, "餐食记录不存在");

    // ========== 餐食食品项相关 1002001600 ==========
    ErrorCode MEAL_FOOD_ITEM_NOT_EXISTS = new ErrorCode(1002001600, "餐食食品项不存在");

    // ========== 食物项相关 1002001800 ==========
    ErrorCode FOOD_ITEM_NOT_EXISTS = new ErrorCode(1002001800, "食物项不存在");

}