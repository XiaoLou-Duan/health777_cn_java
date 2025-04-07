package cn.iocoder.yudao.module.nutrition.service.meal;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealExportReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.MealUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.meal.vo.AppMealPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.meal.MealDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 用餐记录 Service 接口
 */
public interface MealService {

    /**
     * 创建用餐记录
     *
     * @param createReqVO 创建信息
     * @return 用餐记录编号
     */
    Long createMeal(@Valid MealCreateReqVO createReqVO);

    /**
     * 更新用餐记录
     *
     * @param updateReqVO 更新信息
     */
    void updateMeal(@Valid MealUpdateReqVO updateReqVO);

    /**
     * 删除用餐记录
     *
     * @param id 编号
     */
    void deleteMeal(Long id);

    /**
     * 获得用餐记录
     *
     * @param id 编号
     * @return 用餐记录
     */
    MealDO getMeal(Long id);

    /**
     * 获得用餐记录列表
     *
     * @param ids 编号
     * @return 用餐记录列表
     */
    List<MealDO> getMealList(Collection<Long> ids);

    /**
     * 获得用餐记录分页
     *
     * @param pageReqVO 分页查询
     * @return 用餐记录分页
     */
    PageResult<MealDO> getMealPage(MealPageReqVO pageReqVO);

    /**
     * 获得用餐记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 用餐记录列表
     */
    List<MealDO> getMealList(MealExportReqVO exportReqVO);

    /**
     * 获得APP用餐记录分页
     *
     * @param pageReqVO 分页查询
     * @return 用餐记录分页
     */
    PageResult<MealDO> getMealPage(AppMealPageReqVO pageReqVO);

}