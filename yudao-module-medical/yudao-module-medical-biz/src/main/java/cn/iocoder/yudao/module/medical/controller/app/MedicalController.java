package cn.iocoder.yudao.module.medical.controller.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@Tag(name = "用户 App - 医疗")
@RestController
@RequestMapping("/medical")
public class MedicalController {

    @GetMapping("/get")
    @Operation(summary = "获取医疗信息")
    @PermitAll
    public String getMedicalInfo() {
        return "医疗信息";
    }
}
