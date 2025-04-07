package cn.iocoder.yudao.module.nutrition.service.mealfooditem.impl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.MealFoodItemUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.mealfooditem.vo.AppMealFoodItemPageReqVO;
import cn.iocoder.yudao.module.nutrition.convert.mealfooditem.MealFoodItemConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.mealfooditem.MealFoodItemDO;
import cn.iocoder.yudao.module.nutrition.dal.mysql.mealfooditem.MealFoodItemMapper;
import cn.iocoder.yudao.module.nutrition.service.mealfooditem.MealFoodItemService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.nutrition.enums.ErrorCodeConstants.MEAL_FOOD_ITEM_NOT_EXISTS;

/**
 * 餐食食物明细 Service 实现类
 */
@Service
@Validated
public class MealFoodItemServiceImpl implements MealFoodItemService {

    @Resource
    private MealFoodItemMapper mealFoodItemMapper;

    @Override
    public Long createMealFoodItem(MealFoodItemCreateReqVO createReqVO) {
        // 插入
        MealFoodItemDO mealFoodItem = MealFoodItemConvert.INSTANCE.convert(createReqVO);
        mealFoodItemMapper.insert(mealFoodItem);
        // 返回
        return mealFoodItem.getId();
    }

    @Override
    public void updateMealFoodItem(MealFoodItemUpdateReqVO updateReqVO) {
        // 校验存在
        validateMealFoodItemExists(updateReqVO.getId());
        // 更新
        MealFoodItemDO updateObj = MealFoodItemConvert.INSTANCE.convert(updateReqVO);
        mealFoodItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteMealFoodItem(Long id) {
        // 校验存在
        validateMealFoodItemExists(id);
        // 删除
        mealFoodItemMapper.deleteById(id);
    }

    private void validateMealFoodItemExists(Long id) {
        if (mealFoodItemMapper.selectById(id) == null) {
            throw exception(MEAL_FOOD_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public MealFoodItemDO getMealFoodItem(Long id) {
        return mealFoodItemMapper.selectById(id);
    }

    @Override
    public List<MealFoodItemDO> getMealFoodItemList(Collection<Long> ids) {
        return mealFoodItemMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MealFoodItemDO> getMealFoodItemPage(MealFoodItemPageReqVO pageReqVO) {
        return mealFoodItemMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MealFoodItemDO> getMealFoodItemListByMealId(Long mealId) {
        return mealFoodItemMapper.selectListByMealId(mealId);
    }

    @Override
    public PageResult<MealFoodItemDO> getMealFoodItemPage(AppMealFoodItemPageReqVO pageReqVO) {
        return mealFoodItemMapper.selectPage(pageReqVO);
    }
}