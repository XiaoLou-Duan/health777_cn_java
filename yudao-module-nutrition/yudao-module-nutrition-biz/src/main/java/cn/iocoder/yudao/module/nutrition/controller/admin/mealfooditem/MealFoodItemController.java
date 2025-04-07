package cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.mealfooditem.vo.*;
import cn.iocoder.yudao.module.nutrition.convert.mealfooditem.MealFoodItemConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.mealfooditem.MealFoodItemDO;
import cn.iocoder.yudao.module.nutrition.service.mealfooditem.MealFoodItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 餐食食物明细")
@RestController
@RequestMapping("/nutrition/meal-food-item")
@Validated
public class MealFoodItemController {

    @Resource
    private MealFoodItemService mealFoodItemService;

    @PostMapping("/create")
    @Operation(summary = "创建餐食食物明细")
    @PreAuthorize("@ss.hasPermission('nutrition:meal-food-item:create')")
    public CommonResult<Long> createMealFoodItem(@Valid @RequestBody MealFoodItemCreateReqVO createReqVO) {
        return success(mealFoodItemService.createMealFoodItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新餐食食物明细")
    @PreAuthorize("@ss.hasPermission('nutrition:meal-food-item:update')")
    public CommonResult<Boolean> updateMealFoodItem(@Valid @RequestBody MealFoodItemUpdateReqVO updateReqVO) {
        mealFoodItemService.updateMealFoodItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除餐食食物明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('nutrition:meal-food-item:delete')")
    public CommonResult<Boolean> deleteMealFoodItem(@RequestParam("id") Long id) {
        mealFoodItemService.deleteMealFoodItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得餐食食物明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:meal-food-item:query')")
    public CommonResult<MealFoodItemRespVO> getMealFoodItem(@RequestParam("id") Long id) {
        MealFoodItemDO mealFoodItem = mealFoodItemService.getMealFoodItem(id);
        return success(MealFoodItemConvert.INSTANCE.convert(mealFoodItem));
    }

    @GetMapping("/list")
    @Operation(summary = "获得餐食食物明细列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('nutrition:meal-food-item:query')")
    public CommonResult<List<MealFoodItemRespVO>> getMealFoodItemList(@RequestParam("ids") Collection<Long> ids) {
        List<MealFoodItemDO> list = mealFoodItemService.getMealFoodItemList(ids);
        return success(MealFoodItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list-by-meal-id")
    @Operation(summary = "获得指定餐食ID下的食物明细列表")
    @Parameter(name = "mealId", description = "餐食编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:meal-food-item:query')")
    public CommonResult<List<MealFoodItemRespVO>> getMealFoodItemListByMealId(@RequestParam("mealId") Long mealId) {
        List<MealFoodItemDO> list = mealFoodItemService.getMealFoodItemListByMealId(mealId);
        return success(MealFoodItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得餐食食物明细分页")
    @PreAuthorize("@ss.hasPermission('nutrition:meal-food-item:query')")
    public CommonResult<PageResult<MealFoodItemRespVO>> getMealFoodItemPage(@Valid MealFoodItemPageReqVO pageVO) {
        PageResult<MealFoodItemDO> pageResult = mealFoodItemService.getMealFoodItemPage(pageVO);
        return success(MealFoodItemConvert.INSTANCE.convertPage(pageResult));
    }

}