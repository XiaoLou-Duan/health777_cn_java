package cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementRespVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.convert.proteinsupplement.ProteinSupplementConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.proteinsupplement.ProteinSupplementDO;
import cn.iocoder.yudao.module.nutrition.service.proteinsupplement.ProteinSupplementService;
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

@Tag(name = "管理后台 - 蛋白质补充剂摄入记录")
@RestController
@RequestMapping("/nutrition/protein-supplement")
@Validated
public class ProteinSupplementController {

    @Resource
    private ProteinSupplementService proteinSupplementService;

    @PostMapping("/create")
    @Operation(summary = "创建蛋白质补充剂摄入记录")
    @PreAuthorize("@ss.hasPermission('nutrition:protein-supplement:create')")
    public CommonResult<Long> createProteinSupplement(@Valid @RequestBody ProteinSupplementCreateReqVO createReqVO) {
        return success(proteinSupplementService.createProteinSupplement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新蛋白质补充剂摄入记录")
    @PreAuthorize("@ss.hasPermission('nutrition:protein-supplement:update')")
    public CommonResult<Boolean> updateProteinSupplement(@Valid @RequestBody ProteinSupplementUpdateReqVO updateReqVO) {
        proteinSupplementService.updateProteinSupplement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除蛋白质补充剂摄入记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('nutrition:protein-supplement:delete')")
    public CommonResult<Boolean> deleteProteinSupplement(@RequestParam("id") Long id) {
        proteinSupplementService.deleteProteinSupplement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得蛋白质补充剂摄入记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:protein-supplement:query')")
    public CommonResult<ProteinSupplementRespVO> getProteinSupplement(@RequestParam("id") Long id) {
        ProteinSupplementDO proteinSupplement = proteinSupplementService.getProteinSupplement(id);
        return success(ProteinSupplementConvert.INSTANCE.convert(proteinSupplement));
    }

    @GetMapping("/list")
    @Operation(summary = "获得蛋白质补充剂摄入记录列表")
    @Parameter(name = "userId", description = "用户编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:protein-supplement:query')")
    public CommonResult<List<ProteinSupplementRespVO>> getProteinSupplementList(@RequestParam("userId") Long userId) {
        List<ProteinSupplementDO> list = proteinSupplementService.getProteinSupplementListByUserId(userId);
        return success(ProteinSupplementConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得蛋白质补充剂摄入记录分页")
    @PreAuthorize("@ss.hasPermission('nutrition:protein-supplement:query')")
    public CommonResult<PageResult<ProteinSupplementRespVO>> getProteinSupplementPage(
            @Valid ProteinSupplementPageReqVO pageVO) {
        PageResult<ProteinSupplementDO> pageResult = proteinSupplementService.getProteinSupplementPage(pageVO);
        return success(ProteinSupplementConvert.INSTANCE.convertPage(pageResult));
    }

}