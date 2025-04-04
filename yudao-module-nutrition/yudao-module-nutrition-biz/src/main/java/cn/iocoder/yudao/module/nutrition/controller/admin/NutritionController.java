package cn.iocoder.yudao.module.nutrition.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台 - 营养")
@RestController
@RequestMapping("/nutrition")
public class NutritionController {
    @GetMapping("/get")
    @Operation(summary = "获取营养信息")
    public String getNutritionInfo() {
        return "营养信息";
    }
}   
