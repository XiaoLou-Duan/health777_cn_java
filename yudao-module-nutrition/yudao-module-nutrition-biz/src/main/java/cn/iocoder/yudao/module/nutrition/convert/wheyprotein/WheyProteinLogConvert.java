package cn.iocoder.yudao.module.nutrition.convert.wheyprotein;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogRespVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogSaveReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogRespVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.wheyprotein.WheyProteinLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 乳清蛋白摄入记录 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface WheyProteinLogConvert {

    WheyProteinLogConvert INSTANCE = Mappers.getMapper(WheyProteinLogConvert.class);

    WheyProteinLogDO convert(WheyProteinLogSaveReqVO bean);

    WheyProteinLogRespVO convert(WheyProteinLogDO bean);

    List<WheyProteinLogRespVO> convertList(List<WheyProteinLogDO> list);

    PageResult<WheyProteinLogRespVO> convertPage(PageResult<WheyProteinLogDO> page);

    @Mapping(target = "confirmationPhotoUrl", ignore = true)
    WheyProteinLogDO convert(AppWheyProteinLogCreateReqVO bean);

    AppWheyProteinLogRespVO convertToApp(WheyProteinLogDO bean);

    List<AppWheyProteinLogRespVO> convertListToApp(List<WheyProteinLogDO> list);

    PageResult<AppWheyProteinLogRespVO> convertPageToApp(PageResult<WheyProteinLogDO> page);

}
