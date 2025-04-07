package cn.iocoder.yudao.module.nutrition.controller.app.meal;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.app.meal.vo.AppMealPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.meal.vo.AppMealRespVO;
import cn.iocoder.yudao.module.nutrition.convert.meal.MealConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.meal.MealDO;
import cn.iocoder.yudao.module.nutrition.service.meal.MealService;
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

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 APP - 用餐记录")
@RestController
@RequestMapping("/nutrition/meal")
@Validated
public class AppMealController {

    @Resource
    private MealService mealService;

    @GetMapping("/get")
    @Operation(summary = "获得用餐记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<AppMealRespVO> getMeal(@RequestParam("id") Long id) {
        MealDO meal = mealService.getMeal(id);
        return success(MealConvert.INSTANCE.convert02(meal));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用餐记录分页")
    public CommonResult<PageResult<AppMealRespVO>> getMealPage(@Valid AppMealPageReqVO pageVO) {
        PageResult<MealDO> pageResult = mealService.getMealPage(pageVO);
        return success(MealConvert.INSTANCE.convertPage02(pageResult));
    }

}