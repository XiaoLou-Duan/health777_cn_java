package cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.*;
import cn.iocoder.yudao.module.nutrition.convert.dietrecommendation.DietRecommendationConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietrecommendation.DietRecommendationDO;
import cn.iocoder.yudao.module.nutrition.service.dietrecommendation.DietRecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 饮食推荐")
@RestController
@RequestMapping("/nutrition/diet-recommendation")
@Validated
public class DietRecommendationController {

    @Resource
    private DietRecommendationService dietRecommendationService;

    @PostMapping("/create")
    @Operation(summary = "创建饮食推荐")
    @PreAuthorize("@ss.hasPermission('nutrition:diet-recommendation:create')")
    public CommonResult<Long> createDietRecommendation(@Valid @RequestBody DietRecommendationCreateReqVO createReqVO) {
        return success(dietRecommendationService.createDietRecommendation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新饮食推荐")
    @PreAuthorize("@ss.hasPermission('nutrition:diet-recommendation:update')")
    public CommonResult<Boolean> updateDietRecommendation(
            @Valid @RequestBody DietRecommendationUpdateReqVO updateReqVO) {
        dietRecommendationService.updateDietRecommendation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除饮食推荐")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('nutrition:diet-recommendation:delete')")
    public CommonResult<Boolean> deleteDietRecommendation(@RequestParam("id") Long id) {
        dietRecommendationService.deleteDietRecommendation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得饮食推荐")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:diet-recommendation:query')")
    public CommonResult<DietRecommendationRespVO> getDietRecommendation(@RequestParam("id") Long id) {
        DietRecommendationDO dietRecommendation = dietRecommendationService.getDietRecommendation(id);
        return success(DietRecommendationConvert.INSTANCE.convert(dietRecommendation));
    }

    @GetMapping("/page")
    @Operation(summary = "获得饮食推荐分页")
    @PreAuthorize("@ss.hasPermission('nutrition:diet-recommendation:query')")
    public CommonResult<PageResult<DietRecommendationRespVO>> getDietRecommendationPage(
            @Valid DietRecommendationPageReqVO pageVO) {
        PageResult<DietRecommendationDO> pageResult = dietRecommendationService.getDietRecommendationPage(pageVO);
        return success(DietRecommendationConvert.INSTANCE.convertPage(pageResult));
    }
}