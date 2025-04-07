package cn.iocoder.yudao.module.nutrition.service.meal.impl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealExportReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.meal.vo.AppMealPageReqVO;
import cn.iocoder.yudao.module.nutrition.convert.meal.MealConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.meal.MealDO;
import cn.iocoder.yudao.module.nutrition.dal.mysql.meal.MealMapper;
import cn.iocoder.yudao.module.nutrition.service.meal.MealService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.nutrition.enums.ErrorCodeConstants.MEAL_NOT_EXISTS;

/**
 * 用餐记录 Service 实现类
 */
@Service
@Validated
public class MealServiceImpl implements MealService {

    @Resource
    private MealMapper mealMapper;

    @Override
    public Long createMeal(MealCreateReqVO createReqVO) {
        // 插入
        MealDO meal = MealConvert.INSTANCE.convert(createReqVO);
        mealMapper.insert(meal);
        // 返回
        return meal.getId();
    }

    @Override
    public void updateMeal(MealUpdateReqVO updateReqVO) {
        // 校验存在
        validateMealExists(updateReqVO.getId());
        // 更新
        MealDO updateObj = MealConvert.INSTANCE.convert(updateReqVO);
        mealMapper.updateById(updateObj);
    }

    @Override
    public void deleteMeal(Long id) {
        // 校验存在
        validateMealExists(id);
        // 删除
        mealMapper.deleteById(id);
    }

    private void validateMealExists(Long id) {
        if (mealMapper.selectById(id) == null) {
            throw exception(MEAL_NOT_EXISTS);
        }
    }

    @Override
    public MealDO getMeal(Long id) {
        return mealMapper.selectById(id);
    }

    @Override
    public List<MealDO> getMealList(Collection<Long> ids) {
        return mealMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MealDO> getMealPage(MealPageReqVO pageReqVO) {
        return mealMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MealDO> getMealList(MealExportReqVO exportReqVO) {
        return mealMapper.selectList(exportReqVO);
    }

    @Override
    public PageResult<MealDO> getMealPage(AppMealPageReqVO pageReqVO) {
        return mealMapper.selectPage(pageReqVO);
    }

}