package cn.iocoder.yudao.module.exercise.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.annotation.security.PermitAll;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户 App - 测试")
@RestController
@RequestMapping("/exercise/test")
@Validated
public class AppExerciseTestController {

    @GetMapping("/get")
    @Operation(summary = "获得测试信息")
    @PermitAll
    public CommonResult<String> get() {
        return CommonResult.success("测试成功");
    }

}