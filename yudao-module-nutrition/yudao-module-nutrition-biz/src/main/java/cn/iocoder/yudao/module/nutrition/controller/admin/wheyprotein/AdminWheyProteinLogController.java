package cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogRespVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogSaveReqVO;
import cn.iocoder.yudao.module.nutrition.convert.wheyprotein.WheyProteinLogConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.wheyprotein.WheyProteinLogDO;
import cn.iocoder.yudao.module.nutrition.service.wheyprotein.WheyProteinLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 乳清蛋白摄入记录")
@RestController
@RequestMapping("/nutrition/whey-protein-log")
@Validated
public class AdminWheyProteinLogController {

    @Resource
    private WheyProteinLogService wheyProteinLogService;

    @PostMapping("/create")
    @Operation(summary = "创建乳清蛋白摄入记录")
    @PreAuthorize("@ss.hasPermission('nutrition:whey-protein-log:create')")
    public CommonResult<Long> createWheyProteinLog(@Valid @RequestBody WheyProteinLogSaveReqVO createReqVO) {
        return success(wheyProteinLogService.createWheyProteinLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新乳清蛋白摄入记录")
    @PreAuthorize("@ss.hasPermission('nutrition:whey-protein-log:update')")
    public CommonResult<Boolean> updateWheyProteinLog(@Valid @RequestBody WheyProteinLogSaveReqVO updateReqVO) {
        wheyProteinLogService.updateWheyProteinLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除乳清蛋白摄入记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('nutrition:whey-protein-log:delete')")
    public CommonResult<Boolean> deleteWheyProteinLog(@RequestParam("id") Long id) {
        wheyProteinLogService.deleteWheyProteinLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得乳清蛋白摄入记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:whey-protein-log:query')")
    public CommonResult<WheyProteinLogRespVO> getWheyProteinLog(@RequestParam("id") Long id) {
        WheyProteinLogDO wheyProteinLog = wheyProteinLogService.getWheyProteinLog(id);
        return success(WheyProteinLogConvert.INSTANCE.convert(wheyProteinLog));
    }

    @GetMapping("/page")
    @Operation(summary = "获得乳清蛋白摄入记录分页")
    @PreAuthorize("@ss.hasPermission('nutrition:whey-protein-log:query')")
    public CommonResult<PageResult<WheyProteinLogRespVO>> getWheyProteinLogPage(@Valid WheyProteinLogPageReqVO pageVO) {
        PageResult<WheyProteinLogDO> pageResult = wheyProteinLogService.getWheyProteinLogPage(pageVO);
        return success(WheyProteinLogConvert.INSTANCE.convertPage(pageResult));
    }

}
