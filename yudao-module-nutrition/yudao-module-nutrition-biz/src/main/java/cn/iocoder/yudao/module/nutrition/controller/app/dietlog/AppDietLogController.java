package cn.iocoder.yudao.module.nutrition.controller.app.dietlog;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogRespVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.convert.dietlog.DietLogConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietlog.DietLogDO;
import cn.iocoder.yudao.module.nutrition.service.dietlog.DietLogService;
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

@Tag(name = "用户 App - 饮食日志")
@RestController
@RequestMapping("/nutrition/diet-log")
@Validated
public class AppDietLogController {

    @Resource
    private DietLogService dietLogService;

    @PostMapping("/create")
    @Operation(summary = "创建饮食日志")
    public CommonResult<Long> createDietLog(@Valid AppDietLogCreateReqVO createReqVO) {
        return success(dietLogService.createDietLog(SecurityFrameworkUtils.getLoginUserId(), createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新饮食日志")
    public CommonResult<Boolean> updateDietLog(@Valid @RequestBody AppDietLogUpdateReqVO updateReqVO) {
        dietLogService.updateDietLog(SecurityFrameworkUtils.getLoginUserId(), updateReqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得饮食日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<AppDietLogRespVO> getDietLog(@RequestParam("id") Long id) {
        DietLogDO dietLog = dietLogService.getDietLog(id);
        return success(DietLogConvert.INSTANCE.convertToApp(dietLog));
    }

    @GetMapping("/page")
    @Operation(summary = "获得饮食日志分页")
    public CommonResult<PageResult<AppDietLogRespVO>> getDietLogPage(@Valid AppDietLogPageReqVO pageVO) {
        PageResult<DietLogDO> pageResult = dietLogService.getDietLogPage(SecurityFrameworkUtils.getLoginUserId(), pageVO);
        return success(DietLogConvert.INSTANCE.convertPageToApp(pageResult));
    }

    @GetMapping("/list-by-date")
    @Operation(summary = "获得指定日期的饮食日志列表")
    @Parameter(name = "date", description = "日期", required = true, example = "2023-01-01")
    public CommonResult<List<AppDietLogRespVO>> getDietLogListByDate(@RequestParam("date") @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY) LocalDate date) {
        List<DietLogDO> list = dietLogService.getDietLogListByUserIdAndDate(SecurityFrameworkUtils.getLoginUserId(), date);
        return success(DietLogConvert.INSTANCE.convertListToApp(list));
    }

}
