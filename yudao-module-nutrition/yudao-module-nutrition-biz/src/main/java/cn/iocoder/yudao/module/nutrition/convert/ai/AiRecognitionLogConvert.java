package cn.iocoder.yudao.module.nutrition.convert.ai;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.ai.vo.AiRecognitionLogRespVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.ai.AiRecognitionLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * AI识别日志 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface AiRecognitionLogConvert {

    AiRecognitionLogConvert INSTANCE = Mappers.getMapper(AiRecognitionLogConvert.class);

    AiRecognitionLogRespVO convert(AiRecognitionLogDO bean);

    List<AiRecognitionLogRespVO> convertList(List<AiRecognitionLogDO> list);

    PageResult<AiRecognitionLogRespVO> convertPage(PageResult<AiRecognitionLogDO> page);

}
