package cn.iocoder.yudao.module.nutrition.convert.nutritionrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordRespVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.nutritionrecord.vo.AppNutritionRecordRespVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.nutritionrecord.NutritionRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 营养摄入记录 Convert
 */
@Mapper
public interface NutritionRecordConvert {

    NutritionRecordConvert INSTANCE = Mappers.getMapper(NutritionRecordConvert.class);

    NutritionRecordDO convert(NutritionRecordCreateReqVO bean);

    NutritionRecordDO convert(NutritionRecordUpdateReqVO bean);

    NutritionRecordRespVO convert(NutritionRecordDO bean);

    List<NutritionRecordRespVO> convertList(List<NutritionRecordDO> list);

    PageResult<NutritionRecordRespVO> convertPage(PageResult<NutritionRecordDO> page);

    AppNutritionRecordRespVO convert02(NutritionRecordDO bean);

    PageResult<AppNutritionRecordRespVO> convertPage02(PageResult<NutritionRecordDO> page);

    List<AppNutritionRecordRespVO> convertList02(List<NutritionRecordDO> list);

}