package cn.iocoder.yudao.module.nutrition.controller.app.mealfooditem;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.app.mealfooditem.vo.AppMealFoodItemPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.mealfooditem.vo.AppMealFoodItemRespVO;
import cn.iocoder.yudao.module.nutrition.convert.mealfooditem.MealFoodItemConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.mealfooditem.MealFoodItemDO;
import cn.iocoder.yudao.module.nutrition.service.mealfooditem.MealFoodItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 APP - 餐食食物明细")
@RestController
@RequestMapping("/nutrition/meal-food-item")
@Validated
public class AppMealFoodItemController {

    @Resource
    private MealFoodItemService mealFoodItemService;

    @GetMapping("/get")
    @Operation(summary = "获得餐食食物明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<AppMealFoodItemRespVO> getMealFoodItem(@RequestParam("id") Long id) {
        MealFoodItemDO mealFoodItem = mealFoodItemService.getMealFoodItem(id);
        return success(MealFoodItemConvert.INSTANCE.convert02(mealFoodItem));
    }

    @GetMapping("/list-by-meal-id")
    @Operation(summary = "获得指定餐食ID下的食物明细列表")
    @Parameter(name = "mealId", description = "餐食编号", required = true, example = "1024")
    public CommonResult<List<AppMealFoodItemRespVO>> getMealFoodItemListByMealId(@RequestParam("mealId") Long mealId) {
        List<MealFoodItemDO> list = mealFoodItemService.getMealFoodItemListByMealId(mealId);
        return success(MealFoodItemConvert.INSTANCE.convertList02(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得餐食食物明细分页")
    public CommonResult<PageResult<AppMealFoodItemRespVO>> getMealFoodItemPage(@Valid AppMealFoodItemPageReqVO pageVO) {
        PageResult<MealFoodItemDO> pageResult = mealFoodItemService.getMealFoodItemPage(pageVO);
        return success(MealFoodItemConvert.INSTANCE.convertPage02(pageResult));
    }

}