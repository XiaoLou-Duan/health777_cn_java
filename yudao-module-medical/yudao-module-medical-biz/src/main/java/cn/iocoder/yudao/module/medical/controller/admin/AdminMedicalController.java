package cn.iocoder.yudao.module.medical.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台 - 医疗")
@RestController
@RequestMapping("/medical")
public class AdminMedicalController {

    @GetMapping("/get")
    @Operation(summary = "获取医疗信息")
    public String getMedicalInfo() {
        return "医疗信息";
    }
}
