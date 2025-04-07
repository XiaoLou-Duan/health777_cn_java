package cn.iocoder.yudao.module.nutrition.controller.admin.meal;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.meal.vo.*;
import cn.iocoder.yudao.module.nutrition.convert.meal.MealConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.meal.MealDO;
import cn.iocoder.yudao.module.nutrition.service.meal.MealService;
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

@Tag(name = "管理后台 - 用餐记录")
@RestController
@RequestMapping("/nutrition/meal")
@Validated
public class MealController {

    @Resource
    private MealService mealService;

    @PostMapping("/create")
    @Operation(summary = "创建用餐记录")
    @PreAuthorize("@ss.hasPermission('nutrition:meal:create')")
    public CommonResult<Long> createMeal(@Valid @RequestBody MealCreateReqVO createReqVO) {
        return success(mealService.createMeal(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用餐记录")
    @PreAuthorize("@ss.hasPermission('nutrition:meal:update')")
    public CommonResult<Boolean> updateMeal(@Valid @RequestBody MealUpdateReqVO updateReqVO) {
        mealService.updateMeal(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用餐记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('nutrition:meal:delete')")
    public CommonResult<Boolean> deleteMeal(@RequestParam("id") Long id) {
        mealService.deleteMeal(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用餐记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:meal:query')")
    public CommonResult<MealRespVO> getMeal(@RequestParam("id") Long id) {
        MealDO meal = mealService.getMeal(id);
        return success(MealConvert.INSTANCE.convert(meal));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用餐记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('nutrition:meal:query')")
    public CommonResult<List<MealRespVO>> getMealList(@RequestParam("ids") Collection<Long> ids) {
        List<MealDO> list = mealService.getMealList(ids);
        return success(MealConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用餐记录分页")
    @PreAuthorize("@ss.hasPermission('nutrition:meal:query')")
    public CommonResult<PageResult<MealRespVO>> getMealPage(@Valid MealPageReqVO pageVO) {
        PageResult<MealDO> pageResult = mealService.getMealPage(pageVO);
        return success(MealConvert.INSTANCE.convertPage(pageResult));
    }

}