package cn.iocoder.yudao.module.nutrition.controller.app.nutritionrecord;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.app.nutritionrecord.vo.AppNutritionRecordPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.nutritionrecord.vo.AppNutritionRecordRespVO;
import cn.iocoder.yudao.module.nutrition.convert.nutritionrecord.NutritionRecordConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.nutritionrecord.NutritionRecordDO;
import cn.iocoder.yudao.module.nutrition.service.nutritionrecord.NutritionRecordService;
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
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 APP - 营养摄入记录")
@RestController
@RequestMapping("/nutrition/nutrition-record")
@Validated
public class AppNutritionRecordController {

    @Resource
    private NutritionRecordService nutritionRecordService;

    @GetMapping("/get")
    @Operation(summary = "获得营养摄入记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<AppNutritionRecordRespVO> getNutritionRecord(@RequestParam("id") Long id) {
        NutritionRecordDO nutritionRecord = nutritionRecordService.getNutritionRecord(id);
        return success(NutritionRecordConvert.INSTANCE.convert02(nutritionRecord));
    }

    @GetMapping("/get-by-user-and-date")
    @Operation(summary = "根据用户ID和日期获取营养摄入记录")
    public CommonResult<AppNutritionRecordRespVO> getNutritionRecordByUserAndDate(
            @RequestParam("userId") Long userId,
            @RequestParam("recordDate") LocalDate recordDate) {
        NutritionRecordDO nutritionRecord = nutritionRecordService.getNutritionRecordByUserIdAndDate(userId,
                recordDate);
        return success(NutritionRecordConvert.INSTANCE.convert02(nutritionRecord));
    }

    @GetMapping("/list-by-user")
    @Operation(summary = "获取用户的所有营养摄入记录")
    @Parameter(name = "userId", description = "用户编号", required = true, example = "1024")
    public CommonResult<List<AppNutritionRecordRespVO>> getNutritionRecordListByUser(
            @RequestParam("userId") Long userId) {
        List<NutritionRecordDO> list = nutritionRecordService.getNutritionRecordListByUserId(userId);
        return success(NutritionRecordConvert.INSTANCE.convertList02(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得营养摄入记录分页")
    public CommonResult<PageResult<AppNutritionRecordRespVO>> getNutritionRecordPage(
            @Valid AppNutritionRecordPageReqVO pageVO) {
        PageResult<NutritionRecordDO> pageResult = nutritionRecordService.getNutritionRecordPage(pageVO);
        return success(NutritionRecordConvert.INSTANCE.convertPage02(pageResult));
    }
}