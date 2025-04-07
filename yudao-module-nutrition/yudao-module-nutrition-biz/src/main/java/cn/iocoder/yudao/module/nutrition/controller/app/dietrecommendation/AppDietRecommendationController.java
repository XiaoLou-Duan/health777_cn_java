package cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationRespVO;
import cn.iocoder.yudao.module.nutrition.convert.dietrecommendation.DietRecommendationConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietrecommendation.DietRecommendationDO;
import cn.iocoder.yudao.module.nutrition.service.dietrecommendation.DietRecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户APP - 饮食建议")
@RestController
@RequestMapping("/nutrition/app/diet-recommendation")
@Validated
public class AppDietRecommendationController {

    @Resource
    private DietRecommendationService dietRecommendationService;

    @PostMapping("/create")
    @Operation(summary = "创建饮食建议")
    public CommonResult<Long> createDietRecommendation(
            @Valid @RequestBody AppDietRecommendationCreateReqVO createReqVO) {
        return success(dietRecommendationService.createDietRecommendation(createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得饮食建议")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<AppDietRecommendationRespVO> getDietRecommendation(@RequestParam("id") Long id) {
        DietRecommendationDO dietRecommendation = dietRecommendationService.getDietRecommendation(id);
        return success(DietRecommendationConvert.INSTANCE.convert02(dietRecommendation));
    }

    @GetMapping("/get-by-user-and-date")
    @Operation(summary = "获得饮食建议")
    @Parameter(name = "userId", description = "用户编号", required = true, example = "1024")
    @Parameter(name = "recommendationDate", description = "建议日期", required = true)
    public CommonResult<AppDietRecommendationRespVO> getDietRecommendationByUserIdAndRecommendationDate(
            @RequestParam("userId") Long userId,
            @RequestParam("recommendationDate") LocalDate recommendationDate) {
        DietRecommendationDO dietRecommendation = dietRecommendationService
                .getDietRecommendationByUserIdAndRecommendationDate(userId, recommendationDate);
        return success(DietRecommendationConvert.INSTANCE.convert02(dietRecommendation));
    }

    @GetMapping("/list-by-user")
    @Operation(summary = "获得饮食建议列表")
    @Parameter(name = "userId", description = "用户编号", required = true, example = "1024")
    public CommonResult<List<AppDietRecommendationRespVO>> getDietRecommendationListByUserId(
            @RequestParam("userId") Long userId) {
        List<DietRecommendationDO> list = dietRecommendationService.getDietRecommendationListByUserId(userId);
        return success(DietRecommendationConvert.INSTANCE.convertList02(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得饮食建议分页")
    public CommonResult<PageResult<AppDietRecommendationRespVO>> getDietRecommendationPage(
            @Valid AppDietRecommendationPageReqVO pageVO) {
        PageResult<DietRecommendationDO> pageResult = dietRecommendationService.getDietRecommendationPage(pageVO);
        return success(DietRecommendationConvert.INSTANCE.convertPage02(pageResult));
    }

}