package cn.iocoder.yudao.module.social.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台 - 社交")
@RestController
@RequestMapping("/social")
public class SocialController {
    @GetMapping("/get")
    @Operation(summary = "获取社交信息")    
    public String getSocialInfo() {
        return "社交信息";
    }
}   
