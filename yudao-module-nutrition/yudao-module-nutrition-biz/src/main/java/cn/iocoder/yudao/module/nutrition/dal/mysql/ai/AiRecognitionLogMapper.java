package cn.iocoder.yudao.module.nutrition.dal.mysql.ai;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.nutrition.controller.admin.ai.vo.AiRecognitionLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.ai.AiRecognitionLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AI识别日志 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AiRecognitionLogMapper extends BaseMapperX<AiRecognitionLogDO> {

    default PageResult<AiRecognitionLogDO> selectPage(AiRecognitionLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiRecognitionLogDO>()
                .eqIfPresent(AiRecognitionLogDO::getDietLogId, reqVO.getDietLogId())
                .eqIfPresent(AiRecognitionLogDO::getSuccess, reqVO.getSuccess())
                .betweenIfPresent(AiRecognitionLogDO::getRequestTimestamp, reqVO.getRequestTimeStart(), reqVO.getRequestTimeEnd())
                .orderByDesc(AiRecognitionLogDO::getId));
    }

    default AiRecognitionLogDO selectByDietLogId(Long dietLogId) {
        return selectOne(AiRecognitionLogDO::getDietLogId, dietLogId);
    }

}
