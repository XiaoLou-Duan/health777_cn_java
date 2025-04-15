package cn.iocoder.yudao.module.nutrition.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Nutrition 错误码枚举类
 *
 * nutrition 系统，使用 1-019-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 饮食日志相关 1-019-000-000 ==========
    ErrorCode DIET_LOG_NOT_EXISTS = new ErrorCode(1_019_000_001, "饮食日志不存在");
    ErrorCode DIET_LOG_NOT_SELF = new ErrorCode(1_019_000_002, "不能操作他人的饮食日志");
    ErrorCode DIET_LOG_UPLOAD_PHOTO_FAIL = new ErrorCode(1_019_000_003, "上传食物照片失败");

    // ========== 乳清蛋白摄入记录相关 1-019-001-000 ==========
    ErrorCode WHEY_PROTEIN_LOG_NOT_EXISTS = new ErrorCode(1_019_001_001, "乳清蛋白摄入记录不存在");
    ErrorCode WHEY_PROTEIN_LOG_NOT_SELF = new ErrorCode(1_019_001_002, "不能操作他人的乳清蛋白摄入记录");
    ErrorCode WHEY_PROTEIN_LOG_UPLOAD_PHOTO_FAIL = new ErrorCode(1_019_001_003, "上传乳清蛋白照片失败");

    // ========== AI识别日志相关 1-019-002-000 ==========
    ErrorCode AI_RECOGNITION_LOG_NOT_EXISTS = new ErrorCode(1_019_002_001, "AI识别日志不存在");
    ErrorCode AI_RECOGNITION_FAIL = new ErrorCode(1_019_002_002, "AI识别失败");

}
