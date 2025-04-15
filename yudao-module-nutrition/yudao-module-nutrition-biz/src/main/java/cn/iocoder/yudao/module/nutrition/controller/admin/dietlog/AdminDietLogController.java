package cn.iocoder.yudao.module.nutrition.controller.admin.dietlog;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogRespVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogSaveReqVO;
import cn.iocoder.yudao.module.nutrition.convert.dietlog.DietLogConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietlog.DietLogDO;
import cn.iocoder.yudao.module.nutrition.service.dietlog.DietLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 饮食日志")
@RestController
@RequestMapping("/nutrition/diet-log")
@Validated
public class AdminDietLogController {

    @Resource
    private DietLogService dietLogService;

    @PostMapping("/create")
    @Operation(summary = "创建饮食日志")
    @PreAuthorize("@ss.hasPermission('nutrition:diet-log:create')")
    public CommonResult<Long> createDietLog(@Valid @RequestBody DietLogSaveReqVO createReqVO) {
        return success(dietLogService.createDietLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新饮食日志")
    @PreAuthorize("@ss.hasPermission('nutrition:diet-log:update')")
    public CommonResult<Boolean> updateDietLog(@Valid @RequestBody DietLogSaveReqVO updateReqVO) {
        dietLogService.updateDietLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除饮食日志")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('nutrition:diet-log:delete')")
    public CommonResult<Boolean> deleteDietLog(@RequestParam("id") Long id) {
        dietLogService.deleteDietLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得饮食日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:diet-log:query')")
    public CommonResult<DietLogRespVO> getDietLog(@RequestParam("id") Long id) {
        DietLogDO dietLog = dietLogService.getDietLog(id);
        return success(DietLogConvert.INSTANCE.convert(dietLog));
    }

    @GetMapping("/page")
    @Operation(summary = "获得饮食日志分页")
    @PreAuthorize("@ss.hasPermission('nutrition:diet-log:query')")
    public CommonResult<PageResult<DietLogRespVO>> getDietLogPage(@Valid DietLogPageReqVO pageVO) {
        PageResult<DietLogDO> pageResult = dietLogService.getDietLogPage(pageVO);
        return success(DietLogConvert.INSTANCE.convertPage(pageResult));
    }

}
