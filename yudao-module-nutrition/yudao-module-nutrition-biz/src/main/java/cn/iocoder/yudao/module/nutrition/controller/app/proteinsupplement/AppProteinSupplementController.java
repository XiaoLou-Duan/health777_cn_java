package cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementRespVO;
import cn.iocoder.yudao.module.nutrition.convert.proteinsupplement.ProteinSupplementConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.proteinsupplement.ProteinSupplementDO;
import cn.iocoder.yudao.module.nutrition.service.proteinsupplement.ProteinSupplementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Tag(name = "用户 APP - 蛋白质补充剂摄入记录")
@RestController
@RequestMapping("/nutrition/protein-supplement")
@Validated
public class AppProteinSupplementController {

    @Resource
    private ProteinSupplementService proteinSupplementService;

    @PostMapping("/create")
    @Operation(summary = "创建蛋白质补充剂摄入记录")
    public CommonResult<Long> createProteinSupplement(@Valid @RequestBody AppProteinSupplementCreateReqVO createReqVO) {
        return success(proteinSupplementService.createProteinSupplement(createReqVO));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除蛋白质补充剂摄入记录")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteProteinSupplement(@RequestParam("id") Long id) {
        proteinSupplementService.deleteProteinSupplement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得蛋白质补充剂摄入记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<AppProteinSupplementRespVO> getProteinSupplement(@RequestParam("id") Long id) {
        ProteinSupplementDO proteinSupplement = proteinSupplementService.getProteinSupplement(id);
        return success(ProteinSupplementConvert.INSTANCE.convert02(proteinSupplement));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用户的蛋白质补充剂摄入记录列表")
    @Parameter(name = "userId", description = "用户编号", required = true, example = "1024")
    public CommonResult<List<AppProteinSupplementRespVO>> getProteinSupplementList(
            @RequestParam("userId") Long userId) {
        List<ProteinSupplementDO> list = proteinSupplementService.getProteinSupplementListByUserId(userId);
        return success(ProteinSupplementConvert.INSTANCE.convertList02(list));
    }

    @GetMapping("/list-by-time")
    @Operation(summary = "获得用户在指定时间范围内的蛋白质补充剂摄入记录列表")
    public CommonResult<List<AppProteinSupplementRespVO>> getProteinSupplementListByTime(
            @RequestParam("userId") Long userId,
            @RequestParam("startTime") @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND) LocalDateTime endTime) {
        List<ProteinSupplementDO> list = proteinSupplementService.getProteinSupplementListByUserIdAndTime(userId,
                startTime, endTime);
        return success(ProteinSupplementConvert.INSTANCE.convertList02(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得蛋白质补充剂摄入记录分页")
    public CommonResult<PageResult<AppProteinSupplementRespVO>> getProteinSupplementPage(
            @Valid AppProteinSupplementPageReqVO pageVO) {
        PageResult<ProteinSupplementDO> pageResult = proteinSupplementService.getProteinSupplementPage(pageVO);
        return success(ProteinSupplementConvert.INSTANCE.convertPage02(pageResult));
    }

}