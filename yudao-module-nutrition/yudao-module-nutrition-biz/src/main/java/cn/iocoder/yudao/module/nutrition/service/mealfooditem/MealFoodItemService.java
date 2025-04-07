package cn.iocoder.yudao.module.nutrition.service.mealfooditem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.mealfooditem.vo.AppMealFoodItemPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.mealfooditem.MealFoodItemDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 餐食食物明细 Service 接口
 */
public interface MealFoodItemService {

    /**
     * 创建餐食食物明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMealFoodItem(@Valid MealFoodItemCreateReqVO createReqVO);

    /**
     * 更新餐食食物明细
     *
     * @param updateReqVO 更新信息
     */
    void updateMealFoodItem(@Valid MealFoodItemUpdateReqVO updateReqVO);

    /**
     * 删除餐食食物明细
     *
     * @param id 编号
     */
    void deleteMealFoodItem(Long id);

    /**
     * 获得餐食食物明细
     *
     * @param id 编号
     * @return 餐食食物明细
     */
    MealFoodItemDO getMealFoodItem(Long id);

    /**
     * 获得餐食食物明细列表
     *
     * @param ids 编号
     * @return 餐食食物明细列表
     */
    List<MealFoodItemDO> getMealFoodItemList(Collection<Long> ids);

    /**
     * 获得餐食食物明细分页
     *
     * @param pageReqVO 分页查询
     * @return 餐食食物明细分页
     */
    PageResult<MealFoodItemDO> getMealFoodItemPage(MealFoodItemPageReqVO pageReqVO);

    /**
     * 获得指定餐食ID下的食物明细列表
     *
     * @param mealId 餐食ID
     * @return 餐食食物明细列表
     */
    List<MealFoodItemDO> getMealFoodItemListByMealId(Long mealId);

    /**
     * 获得APP餐食食物明细分页
     *
     * @param pageReqVO 分页查询
     * @return 餐食食物明细分页
     */
    PageResult<MealFoodItemDO> getMealFoodItemPage(AppMealFoodItemPageReqVO pageReqVO);

}