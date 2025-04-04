package cn.iocoder.yudao.module.nutrition.controller.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户 App - 营养")
@RestController
@RequestMapping("/nutrition")
public class NutritionController {
    @GetMapping("/get")
    @Operation(summary = "获取营养信息")
    @PermitAll
    public String getNutritionInfo() {
        return "营养信息";
    }
}   
