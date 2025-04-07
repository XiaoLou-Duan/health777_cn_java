package cn.iocoder.yudao.module.nutrition.convert.meal;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.*;
import cn.iocoder.yudao.module.nutrition.controller.app.meal.vo.AppMealRespVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.meal.MealDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用餐记录 Convert
 */
@Mapper
public interface MealConvert {

    MealConvert INSTANCE = Mappers.getMapper(MealConvert.class);

    MealDO convert(MealCreateReqVO bean);

    MealDO convert(MealUpdateReqVO bean);

    MealRespVO convert(MealDO bean);

    List<MealRespVO> convertList(List<MealDO> list);

    PageResult<MealRespVO> convertPage(PageResult<MealDO> page);

    AppMealRespVO convert02(MealDO bean);

    PageResult<AppMealRespVO> convertPage02(PageResult<MealDO> page);

}