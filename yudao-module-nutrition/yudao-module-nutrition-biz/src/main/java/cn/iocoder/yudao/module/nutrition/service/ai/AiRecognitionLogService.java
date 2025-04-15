package cn.iocoder.yudao.module.nutrition.service.ai;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.ai.vo.AiRecognitionLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.ai.AiRecognitionLogDO;

import java.util.Map;

/**
 * AI识别日志 Service 接口
 *
 * @author 芋道源码
 */
public interface AiRecognitionLogService {

    /**
     * 创建AI识别日志
     *
     * @param dietLogId 饮食日志ID
     * @param requestPayload 请求内容
     * @return 编号
     */
    Long createAiRecognitionLog(Long dietLogId, String requestPayload);

    /**
     * 更新AI识别日志的响应结果
     *
     * @param id 编号
     * @param responsePayload 响应内容
     * @param statusCode 状态码
     * @param success 是否成功
     * @param errorMessage 错误信息
     */
    void updateAiRecognitionLogResult(Long id, String responsePayload, Integer statusCode, Boolean success, String errorMessage);

    /**
     * 获得AI识别日志
     *
     * @param id 编号
     * @return AI识别日志
     */
    AiRecognitionLogDO getAiRecognitionLog(Long id);

    /**
     * 获得AI识别日志分页
     *
     * @param pageReqVO 分页查询
     * @return AI识别日志分页
     */
    PageResult<AiRecognitionLogDO> getAiRecognitionLogPage(AiRecognitionLogPageReqVO pageReqVO);

    /**
     * 获得饮食日志的AI识别日志
     *
     * @param dietLogId 饮食日志ID
     * @return AI识别日志
     */
    AiRecognitionLogDO getAiRecognitionLogByDietLogId(Long dietLogId);

    /**
     * 调用AI食物识别服务
     *
     * @param dietLogId 饮食日志ID
     * @param imageUrl 图片URL
     * @return 识别结果，key为食物名称，value为营养信息
     */
    Map<String, Map<String, Object>> recognizeFood(Long dietLogId, String imageUrl);

}
