package cn.iocoder.yudao.module.member.controller.app.user;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.member.controller.app.user.vo.*;
import cn.iocoder.yudao.module.member.convert.user.MemberUserConvert;
import cn.iocoder.yudao.module.member.dal.dataobject.level.MemberLevelDO;
import cn.iocoder.yudao.module.member.dal.dataobject.user.MemberUserDO;
import cn.iocoder.yudao.module.member.service.level.MemberLevelService;
import cn.iocoder.yudao.module.member.service.user.MemberUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.FILE_IS_EMPTY;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 APP - 用户个人中心")
@RestController
@RequestMapping("/member/user")
@Validated
@Slf4j
public class AppMemberUserController {

    @Resource
    private MemberUserService userService;
    @Resource
    private MemberLevelService levelService;

    @GetMapping("/get")
    @Operation(summary = "获得基本信息")
    public CommonResult<AppMemberUserInfoRespVO> getUserInfo() {
        MemberUserDO user = userService.getUser(getLoginUserId());
        MemberLevelDO level = levelService.getLevel(user.getLevelId());
        return success(MemberUserConvert.INSTANCE.convert(user, level));
    }

    @PutMapping("/update")
    @Operation(summary = "修改基本信息")
    public CommonResult<Boolean> updateUser(@RequestBody @Valid AppMemberUserUpdateReqVO reqVO) {
        userService.updateUser(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-mobile")
    @Operation(summary = "修改用户手机")
    public CommonResult<Boolean> updateUserMobile(@RequestBody @Valid AppMemberUserUpdateMobileReqVO reqVO) {
        userService.updateUserMobile(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-mobile-by-weixin")
    @Operation(summary = "基于微信小程序的授权码，修改用户手机")
    public CommonResult<Boolean> updateUserMobileByWeixin(@RequestBody @Valid AppMemberUserUpdateMobileByWeixinReqVO reqVO) {
        userService.updateUserMobileByWeixin(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "修改用户密码", description = "用户修改密码时使用")
    public CommonResult<Boolean> updateUserPassword(@RequestBody @Valid AppMemberUserUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(getLoginUserId(), reqVO);
        return success(true);
    }

    @PutMapping("/reset-password")
    @Operation(summary = "重置密码", description = "用户忘记密码时使用")
    @PermitAll
    public CommonResult<Boolean> resetUserPassword(@RequestBody @Valid AppMemberUserResetPasswordReqVO reqVO) {
        userService.resetUserPassword(reqVO);
        return success(true);
    }

    @RequestMapping(value = "/update-avatar", method = { RequestMethod.POST, RequestMethod.PUT })
    @Operation(summary = "上传用户个人头像")
    public CommonResult<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw exception(FILE_IS_EMPTY);
        }
        String avatar = userService.updateUserAvatar(getLoginUserId(), file.getInputStream());
        return success(avatar);
    }

}