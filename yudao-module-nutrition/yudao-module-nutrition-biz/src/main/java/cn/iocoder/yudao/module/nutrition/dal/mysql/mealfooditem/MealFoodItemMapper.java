package cn.iocoder.yudao.module.nutrition.dal.mysql.mealfooditem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemExportReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.mealfooditem.vo.AppMealFoodItemPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.mealfooditem.MealFoodItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 餐食食物明细 Mapper
 */
@Mapper
public interface MealFoodItemMapper extends BaseMapperX<MealFoodItemDO> {

    default PageResult<MealFoodItemDO> selectPage(MealFoodItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MealFoodItemDO>()
                .eqIfPresent(MealFoodItemDO::getMealId, reqVO.getMealId())
                .likeIfPresent(MealFoodItemDO::getFoodName, reqVO.getFoodName())
                .eqIfPresent(MealFoodItemDO::getAiRecognized, reqVO.getAiRecognized())
                .orderByDesc(MealFoodItemDO::getId));
    }

    default PageResult<MealFoodItemDO> selectPage(AppMealFoodItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MealFoodItemDO>()
                .eqIfPresent(MealFoodItemDO::getMealId, reqVO.getMealId())
                .likeIfPresent(MealFoodItemDO::getFoodName, reqVO.getFoodName())
                .eqIfPresent(MealFoodItemDO::getAiRecognized, reqVO.getAiRecognized())
                .orderByDesc(MealFoodItemDO::getId));
    }

    default List<MealFoodItemDO> selectList(MealFoodItemExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MealFoodItemDO>()
                .eqIfPresent(MealFoodItemDO::getMealId, reqVO.getMealId())
                .likeIfPresent(MealFoodItemDO::getFoodName, reqVO.getFoodName())
                .eqIfPresent(MealFoodItemDO::getAiRecognized, reqVO.getAiRecognized())
                .betweenIfPresent(MealFoodItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MealFoodItemDO::getId));
    }

    default List<MealFoodItemDO> selectListByMealId(Long mealId) {
        return selectList(MealFoodItemDO::getMealId, mealId);
    }

    default List<MealFoodItemDO> selectList(MealFoodItemPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MealFoodItemDO>()
                .eqIfPresent(MealFoodItemDO::getMealId, reqVO.getMealId())
                .likeIfPresent(MealFoodItemDO::getFoodName, reqVO.getFoodName())
                .eqIfPresent(MealFoodItemDO::getAiRecognized, reqVO.getAiRecognized())
                .betweenIfPresent(MealFoodItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MealFoodItemDO::getId));
    }

    default List<MealFoodItemDO> selectList(AppMealFoodItemPageReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MealFoodItemDO>()
                .eqIfPresent(MealFoodItemDO::getMealId, reqVO.getMealId())
                .likeIfPresent(MealFoodItemDO::getFoodName, reqVO.getFoodName())
                .eqIfPresent(MealFoodItemDO::getAiRecognized, reqVO.getAiRecognized())
                .orderByDesc(MealFoodItemDO::getId));
    }
}