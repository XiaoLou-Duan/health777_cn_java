package cn.iocoder.yudao.module.nutrition.convert.mealfooditem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemRespVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.mealfooditem.vo.AppMealFoodItemRespVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.mealfooditem.MealFoodItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 餐食食物明细 Convert
 */
@Mapper
public interface MealFoodItemConvert {

    MealFoodItemConvert INSTANCE = Mappers.getMapper(MealFoodItemConvert.class);

    MealFoodItemDO convert(MealFoodItemCreateReqVO bean);

    MealFoodItemDO convert(MealFoodItemUpdateReqVO bean);

    MealFoodItemRespVO convert(MealFoodItemDO bean);

    List<MealFoodItemRespVO> convertList(List<MealFoodItemDO> list);

    PageResult<MealFoodItemRespVO> convertPage(PageResult<MealFoodItemDO> page);

    AppMealFoodItemRespVO convert02(MealFoodItemDO bean);

    PageResult<AppMealFoodItemRespVO> convertPage02(PageResult<MealFoodItemDO> page);

    List<AppMealFoodItemRespVO> convertList02(List<MealFoodItemDO> list);

}