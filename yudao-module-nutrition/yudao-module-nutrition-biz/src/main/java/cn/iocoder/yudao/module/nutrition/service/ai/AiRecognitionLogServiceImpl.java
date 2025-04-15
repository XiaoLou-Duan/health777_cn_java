package cn.iocoder.yudao.module.nutrition.service.ai;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.ai.vo.AiRecognitionLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.ai.AiRecognitionLogDO;
import cn.iocoder.yudao.module.nutrition.dal.mysql.ai.AiRecognitionLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.nutrition.enums.ErrorCodeConstants.AI_RECOGNITION_LOG_NOT_EXISTS;

/**
 * AI识别日志 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class AiRecognitionLogServiceImpl implements AiRecognitionLogService {

    @Resource
    private AiRecognitionLogMapper aiRecognitionLogMapper;

    @Override
    public Long createAiRecognitionLog(Long dietLogId, String requestPayload) {
        AiRecognitionLogDO aiRecognitionLog = new AiRecognitionLogDO();
        aiRecognitionLog.setDietLogId(dietLogId);
        aiRecognitionLog.setRequestTimestamp(LocalDateTime.now());
        aiRecognitionLog.setRequestPayload(requestPayload);
        aiRecognitionLogMapper.insert(aiRecognitionLog);
        return aiRecognitionLog.getId();
    }

    @Override
    public void updateAiRecognitionLogResult(Long id, String responsePayload, Integer statusCode, Boolean success, String errorMessage) {
        // 校验存在
        validateAiRecognitionLogExists(id);
        
        // 更新
        AiRecognitionLogDO updateObj = new AiRecognitionLogDO();
        updateObj.setId(id);
        updateObj.setResponseTimestamp(LocalDateTime.now());
        updateObj.setResponsePayload(responsePayload);
        updateObj.setStatusCode(statusCode);
        updateObj.setSuccess(success);
        updateObj.setErrorMessage(errorMessage);
        aiRecognitionLogMapper.updateById(updateObj);
    }

    private void validateAiRecognitionLogExists(Long id) {
        if (aiRecognitionLogMapper.selectById(id) == null) {
            throw exception(AI_RECOGNITION_LOG_NOT_EXISTS);
        }
    }

    @Override
    public AiRecognitionLogDO getAiRecognitionLog(Long id) {
        return aiRecognitionLogMapper.selectById(id);
    }

    @Override
    public PageResult<AiRecognitionLogDO> getAiRecognitionLogPage(AiRecognitionLogPageReqVO pageReqVO) {
        return aiRecognitionLogMapper.selectPage(pageReqVO);
    }

    @Override
    public AiRecognitionLogDO getAiRecognitionLogByDietLogId(Long dietLogId) {
        return aiRecognitionLogMapper.selectByDietLogId(dietLogId);
    }

    @Override
    public Map<String, Map<String, Object>> recognizeFood(Long dietLogId, String imageUrl) {
        // 记录请求日志
        Long logId = createAiRecognitionLog(dietLogId, "识别食物图片: " + imageUrl);
        
        try {
            // TODO: 实际项目中，这里应该调用第三方AI食物识别API
            // 这里为了演示，模拟一些识别结果
            Map<String, Map<String, Object>> result = simulateAiRecognition();
            
            // 记录成功响应
            updateAiRecognitionLogResult(
                    logId,
                    "识别成功，识别出" + result.size() + "种食物",
                    200,
                    true,
                    null
            );
            
            return result;
        } catch (Exception e) {
            log.error("[recognizeFood][饮食日志({}) AI识别异常]", dietLogId, e);
            
            // 记录失败响应
            updateAiRecognitionLogResult(
                    logId,
                    null,
                    500,
                    false,
                    e.getMessage()
            );
            
            // 返回空结果
            return new HashMap<>();
        }
    }
    
    /**
     * 模拟AI识别结果
     * 实际项目中应替换为真实的AI识别API调用
     */
    private Map<String, Map<String, Object>> simulateAiRecognition() {
        Map<String, Map<String, Object>> result = new HashMap<>();
        
        // 模拟识别出米饭
        Map<String, Object> rice = new HashMap<>();
        rice.put("weight", 150);
        rice.put("protein", 3.5);
        rice.put("calories", 195);
        result.put("米饭", rice);
        
        // 模拟识别出鸡胸肉
        Map<String, Object> chicken = new HashMap<>();
        chicken.put("weight", 100);
        chicken.put("protein", 24.0);
        chicken.put("calories", 165);
        result.put("鸡胸肉", chicken);
        
        // 模拟识别出青菜
        Map<String, Object> vegetables = new HashMap<>();
        vegetables.put("weight", 100);
        vegetables.put("protein", 1.5);
        vegetables.put("calories", 25);
        result.put("青菜", vegetables);
        
        return result;
    }
}
