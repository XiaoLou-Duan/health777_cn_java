package cn.iocoder.yudao.module.nutrition.convert.proteinsupplement;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementRespVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementRespVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.proteinsupplement.ProteinSupplementDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 蛋白质补充剂摄入记录 Convert
 */
@Mapper
public interface ProteinSupplementConvert {

    ProteinSupplementConvert INSTANCE = Mappers.getMapper(ProteinSupplementConvert.class);

    ProteinSupplementDO convert(ProteinSupplementCreateReqVO bean);

    ProteinSupplementDO convert(ProteinSupplementUpdateReqVO bean);

    ProteinSupplementRespVO convert(ProteinSupplementDO bean);

    List<ProteinSupplementRespVO> convertList(List<ProteinSupplementDO> list);

    PageResult<ProteinSupplementRespVO> convertPage(PageResult<ProteinSupplementDO> page);

    ProteinSupplementDO convert(AppProteinSupplementCreateReqVO bean);

    AppProteinSupplementRespVO convert02(ProteinSupplementDO bean);

    PageResult<AppProteinSupplementRespVO> convertPage02(PageResult<ProteinSupplementDO> page);

    List<AppProteinSupplementRespVO> convertList02(List<ProteinSupplementDO> list);

}