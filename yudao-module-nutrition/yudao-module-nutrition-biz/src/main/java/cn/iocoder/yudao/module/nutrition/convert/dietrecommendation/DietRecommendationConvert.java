package cn.iocoder.yudao.module.nutrition.convert.dietrecommendation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationRespVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationRespVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietrecommendation.DietRecommendationDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DietRecommendationConvert {

    DietRecommendationConvert INSTANCE = Mappers.getMapper(DietRecommendationConvert.class);

    DietRecommendationDO convert(DietRecommendationCreateReqVO bean);

    DietRecommendationDO convert(DietRecommendationUpdateReqVO bean);

    DietRecommendationRespVO convert(DietRecommendationDO bean);

    List<DietRecommendationRespVO> convertList(List<DietRecommendationDO> list);

    PageResult<DietRecommendationRespVO> convertPage(PageResult<DietRecommendationDO> page);

    DietRecommendationDO convert(AppDietRecommendationCreateReqVO bean);

    AppDietRecommendationRespVO convert02(DietRecommendationDO bean);

    List<AppDietRecommendationRespVO> convertList02(List<DietRecommendationDO> list);

    PageResult<AppDietRecommendationRespVO> convertPage02(PageResult<DietRecommendationDO> page);

}