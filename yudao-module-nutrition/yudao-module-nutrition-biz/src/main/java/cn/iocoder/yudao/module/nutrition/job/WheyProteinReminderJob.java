package cn.iocoder.yudao.module.nutrition.job;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.member.api.user.MemberUserApi;
import cn.iocoder.yudao.module.member.api.user.dto.MemberUserRespDTO;
import cn.iocoder.yudao.module.system.api.notify.NotifyMessageSendApi;
import cn.iocoder.yudao.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 乳清蛋白提醒 Job
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class WheyProteinReminderJob implements JobHandler {

    @Resource
    private MemberUserApi memberUserApi;

    @Resource
    private NotifyMessageSendApi notifyMessageSendApi;

    @Override
    public String execute(String param) throws Exception {
        // 获取当前时间
        LocalTime currentTime = LocalDateTime.now().toLocalTime();
        
        // 判断当前时间是否为早上8点或下午5:30
        boolean isMorningReminder = currentTime.getHour() == 8 && currentTime.getMinute() < 15;
        boolean isAfternoonReminder = currentTime.getHour() == 17 && currentTime.getMinute() >= 30 && currentTime.getMinute() < 45;
        
        if (!isMorningReminder && !isAfternoonReminder) {
            return "当前时间不是提醒时间，跳过执行";
        }
        
        // 获取所有用户
        List<MemberUserRespDTO> users = memberUserApi.getUserListByNickname("");
        if (users == null || users.isEmpty()) {
            log.info("[execute][没有需要提醒的用户]");
            return "没有需要提醒的用户";
        }
        
        // 发送提醒
        String title = isMorningReminder ? "早上乳清蛋白摄入提醒" : "下午乳清蛋白摄入提醒";
        String content = isMorningReminder 
                ? "早上好！请记得摄入15克乳清蛋白，并上传照片记录。"
                : "下午好！请记得摄入15克乳清蛋白，并上传照片记录。";
        
        int successCount = 0;
        for (MemberUserRespDTO user : users) {
            try {
                // 构建通知参数
                Map<String, Object> templateParams = new HashMap<>();
                templateParams.put("title", title);
                templateParams.put("content", content);
                
                // 发送通知给会员用户
                notifyMessageSendApi.sendSingleMessageToMember(new NotifySendSingleToUserReqDTO()
                        .setUserId(user.getId())
                        .setTemplateCode("whey-protein-reminder") // 模板编码，需要在系统中配置
                        .setTemplateParams(templateParams));
                log.debug("[execute][给用户({}) 发送乳清蛋白提醒成功]", user.getId());
                
                successCount++;
            } catch (Exception e) {
                log.error("[execute][给用户({}) 发送乳清蛋白提醒失败]", user.getId(), e);
            }
        }
        
        return String.format("发送乳清蛋白提醒成功，时间：%s，成功发送 %d 人", 
                isMorningReminder ? "早上8点" : "下午5:30", successCount);
    }

}
