package cn.iocoder.yudao.module.nutrition.convert.dietlog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogRespVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogSaveReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogRespVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietlog.DietLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 饮食日志 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DietLogConvert {

    DietLogConvert INSTANCE = Mappers.getMapper(DietLogConvert.class);

    DietLogDO convert(DietLogSaveReqVO bean);

    DietLogRespVO convert(DietLogDO bean);

    List<DietLogRespVO> convertList(List<DietLogDO> list);

    PageResult<DietLogRespVO> convertPage(PageResult<DietLogDO> page);

    @Mapping(target = "photoUrl", ignore = true)
    DietLogDO convert(AppDietLogCreateReqVO bean);

    DietLogDO convert(AppDietLogUpdateReqVO bean);

    AppDietLogRespVO convertToApp(DietLogDO bean);

    List<AppDietLogRespVO> convertListToApp(List<DietLogDO> list);

    PageResult<AppDietLogRespVO> convertPageToApp(PageResult<DietLogDO> page);

}
