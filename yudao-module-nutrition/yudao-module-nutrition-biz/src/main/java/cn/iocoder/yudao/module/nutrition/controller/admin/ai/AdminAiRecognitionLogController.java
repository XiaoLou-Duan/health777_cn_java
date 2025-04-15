package cn.iocoder.yudao.module.nutrition.controller.admin.ai;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.ai.vo.AiRecognitionLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.ai.vo.AiRecognitionLogRespVO;
import cn.iocoder.yudao.module.nutrition.convert.ai.AiRecognitionLogConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.ai.AiRecognitionLogDO;
import cn.iocoder.yudao.module.nutrition.service.ai.AiRecognitionLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - AI识别日志")
@RestController
@RequestMapping("/nutrition/ai-recognition-log")
@Validated
public class AdminAiRecognitionLogController {

    @Resource
    private AiRecognitionLogService aiRecognitionLogService;

    @GetMapping("/get")
    @Operation(summary = "获得AI识别日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:ai-recognition-log:query')")
    public CommonResult<AiRecognitionLogRespVO> getAiRecognitionLog(@RequestParam("id") Long id) {
        AiRecognitionLogDO aiRecognitionLog = aiRecognitionLogService.getAiRecognitionLog(id);
        return success(AiRecognitionLogConvert.INSTANCE.convert(aiRecognitionLog));
    }

    @GetMapping("/page")
    @Operation(summary = "获得AI识别日志分页")
    @PreAuthorize("@ss.hasPermission('nutrition:ai-recognition-log:query')")
    public CommonResult<PageResult<AiRecognitionLogRespVO>> getAiRecognitionLogPage(@Valid AiRecognitionLogPageReqVO pageVO) {
        PageResult<AiRecognitionLogDO> pageResult = aiRecognitionLogService.getAiRecognitionLogPage(pageVO);
        return success(AiRecognitionLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/get-by-diet-log")
    @Operation(summary = "获得饮食日志的AI识别日志")
    @Parameter(name = "dietLogId", description = "饮食日志编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('nutrition:ai-recognition-log:query')")
    public CommonResult<AiRecognitionLogRespVO> getAiRecognitionLogByDietLogId(@RequestParam("dietLogId") Long dietLogId) {
        AiRecognitionLogDO aiRecognitionLog = aiRecognitionLogService.getAiRecognitionLogByDietLogId(dietLogId);
        return success(AiRecognitionLogConvert.INSTANCE.convert(aiRecognitionLog));
    }

}
