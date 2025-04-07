package cn.iocoder.yudao.module.nutrition.dal.mysql.meal;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealExportReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.meal.vo.AppMealPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.meal.MealDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用餐记录 Mapper
 */
@Mapper
public interface MealMapper extends BaseMapperX<MealDO> {

    default PageResult<MealDO> selectPage(MealPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MealDO>()
                .eqIfPresent(MealDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MealDO::getMealType, reqVO.getMealType())
                .eqIfPresent(MealDO::getAiRecognized, reqVO.getAiRecognized())
                .betweenIfPresent(MealDO::getMealTime, reqVO.getMealTime())
                .orderByDesc(MealDO::getId));
    }

    default List<MealDO> selectList(MealExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MealDO>()
                .eqIfPresent(MealDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MealDO::getMealType, reqVO.getMealType())
                .eqIfPresent(MealDO::getAiRecognized, reqVO.getAiRecognized())
                .betweenIfPresent(MealDO::getMealTime, reqVO.getMealTime())
                .orderByDesc(MealDO::getId));
    }

    default PageResult<MealDO> selectPage(AppMealPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MealDO>()
                .eqIfPresent(MealDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MealDO::getMealType, reqVO.getMealType())
                .betweenIfPresent(MealDO::getMealTime, reqVO.getMealTime())
                .orderByDesc(MealDO::getId));
    }
}