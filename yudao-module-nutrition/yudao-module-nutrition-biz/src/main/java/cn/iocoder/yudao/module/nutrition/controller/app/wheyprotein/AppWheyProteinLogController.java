package cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogRespVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinReportRespVO;
import cn.iocoder.yudao.module.nutrition.convert.wheyprotein.WheyProteinLogConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.wheyprotein.WheyProteinLogDO;
import cn.iocoder.yudao.module.nutrition.service.wheyprotein.WheyProteinLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

@Tag(name = "用户 App - 乳清蛋白摄入记录")
@RestController
@RequestMapping("/nutrition/whey-protein-log")
@Validated
public class AppWheyProteinLogController {

    @Resource
    private WheyProteinLogService wheyProteinLogService;

    @PostMapping("/create")
    @Operation(summary = "创建乳清蛋白摄入记录")
    public CommonResult<Long> createWheyProteinLog(@Valid AppWheyProteinLogCreateReqVO createReqVO) {
        return success(wheyProteinLogService.createWheyProteinLog(SecurityFrameworkUtils.getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得乳清蛋白摄入记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<AppWheyProteinLogRespVO> getWheyProteinLog(@RequestParam("id") Long id) {
        WheyProteinLogDO wheyProteinLog = wheyProteinLogService.getWheyProteinLog(id);
        return success(WheyProteinLogConvert.INSTANCE.convertToApp(wheyProteinLog));
    }

    @GetMapping("/page")
    @Operation(summary = "获得乳清蛋白摄入记录分页")
    public CommonResult<PageResult<AppWheyProteinLogRespVO>> getWheyProteinLogPage(@Valid AppWheyProteinLogPageReqVO pageVO) {
        PageResult<WheyProteinLogDO> pageResult = wheyProteinLogService.getWheyProteinLogPage(SecurityFrameworkUtils.getLoginUserId(), pageVO);
        return success(WheyProteinLogConvert.INSTANCE.convertPageToApp(pageResult));
    }

    @GetMapping("/list-by-date")
    @Operation(summary = "获得指定日期的乳清蛋白摄入记录列表")
    @Parameter(name = "date", description = "日期", required = true, example = "2023-01-01")
    public CommonResult<List<AppWheyProteinLogRespVO>> getWheyProteinLogListByDate(@RequestParam("date") @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY) LocalDate date) {
        List<WheyProteinLogDO> list = wheyProteinLogService.getWheyProteinLogListByUserIdAndDate(SecurityFrameworkUtils.getLoginUserId(), date);
        return success(WheyProteinLogConvert.INSTANCE.convertListToApp(list));
    }

    @GetMapping("/report")
    @Operation(summary = "获得乳清蛋白摄入报告")
    @Parameter(name = "startDate", description = "开始日期", required = true, example = "2023-01-01")
    @Parameter(name = "endDate", description = "结束日期", required = true, example = "2023-01-07")
    public CommonResult<AppWheyProteinReportRespVO> getWheyProteinReport(
            @RequestParam("startDate") @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY) LocalDate endDate) {
        return success(wheyProteinLogService.getWheyProteinReport(SecurityFrameworkUtils.getLoginUserId(), startDate, endDate));
    }

}
