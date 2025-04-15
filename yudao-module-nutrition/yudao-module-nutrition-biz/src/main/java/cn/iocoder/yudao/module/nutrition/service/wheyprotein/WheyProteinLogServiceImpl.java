package cn.iocoder.yudao.module.nutrition.service.wheyprotein;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogSaveReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinReportRespVO;
import cn.iocoder.yudao.module.nutrition.convert.wheyprotein.WheyProteinLogConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.wheyprotein.WheyProteinLogDO;
import cn.iocoder.yudao.module.nutrition.dal.mysql.wheyprotein.WheyProteinLogMapper;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.nutrition.enums.ErrorCodeConstants.*;

/**
 * 乳清蛋白摄入记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class WheyProteinLogServiceImpl implements WheyProteinLogService {

    @Resource
    private WheyProteinLogMapper wheyProteinLogMapper;

    @Resource
    private FileApi fileApi;

    @Override
    public Long createWheyProteinLog(WheyProteinLogSaveReqVO createReqVO) {
        // 插入
        WheyProteinLogDO wheyProteinLog = WheyProteinLogConvert.INSTANCE.convert(createReqVO);
        wheyProteinLogMapper.insert(wheyProteinLog);
        // 返回
        return wheyProteinLog.getId();
    }

    @Override
    public void updateWheyProteinLog(WheyProteinLogSaveReqVO updateReqVO) {
        // 校验存在
        validateWheyProteinLogExists(updateReqVO.getId());
        // 更新
        WheyProteinLogDO updateObj = WheyProteinLogConvert.INSTANCE.convert(updateReqVO);
        wheyProteinLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteWheyProteinLog(Long id) {
        // 校验存在
        validateWheyProteinLogExists(id);
        // 删除
        wheyProteinLogMapper.deleteById(id);
    }

    private void validateWheyProteinLogExists(Long id) {
        if (wheyProteinLogMapper.selectById(id) == null) {
            throw exception(WHEY_PROTEIN_LOG_NOT_EXISTS);
        }
    }

    @Override
    public WheyProteinLogDO getWheyProteinLog(Long id) {
        return wheyProteinLogMapper.selectById(id);
    }

    @Override
    public PageResult<WheyProteinLogDO> getWheyProteinLogPage(WheyProteinLogPageReqVO pageReqVO) {
        return wheyProteinLogMapper.selectPage(pageReqVO);
    }

    @Override
    public Long createWheyProteinLog(Long userId, AppWheyProteinLogCreateReqVO createReqVO) {
        // 上传照片
        String photoUrl = uploadWheyProteinPhoto(createReqVO.getPhoto());

        // 插入乳清蛋白摄入记录
        WheyProteinLogDO wheyProteinLog = WheyProteinLogConvert.INSTANCE.convert(createReqVO);
        wheyProteinLog.setUserId(userId);
        wheyProteinLog.setConfirmationPhotoUrl(photoUrl);
        wheyProteinLogMapper.insert(wheyProteinLog);

        return wheyProteinLog.getId();
    }

    @Override
    public PageResult<WheyProteinLogDO> getWheyProteinLogPage(Long userId, AppWheyProteinLogPageReqVO pageReqVO) {
        return wheyProteinLogMapper.selectPage(pageReqVO, userId);
    }

    @Override
    public List<WheyProteinLogDO> getWheyProteinLogListByUserIdAndDate(Long userId, LocalDate date) {
        return wheyProteinLogMapper.selectListByUserIdAndDate(userId, date);
    }

    @Override
    public AppWheyProteinReportRespVO getWheyProteinReport(Long userId, LocalDate startDate, LocalDate endDate) {
        // 获取日期范围内的所有记录
        List<WheyProteinLogDO> logs = wheyProteinLogMapper.selectListByUserIdAndDateRange(userId, startDate, endDate);
        
        // 计算总天数
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        
        // 创建报告对象
        AppWheyProteinReportRespVO report = new AppWheyProteinReportRespVO();
        report.setStartDate(startDate);
        report.setEndDate(endDate);
        
        // 如果没有记录，返回空报告
        if (CollUtil.isEmpty(logs)) {
            report.setTotalIntakeCount(0);
            report.setTotalIntakeAmount(BigDecimal.ZERO);
            report.setAverageDailyIntakeCount(BigDecimal.ZERO);
            report.setAverageDailyIntakeAmount(BigDecimal.ZERO);
            report.setDailyIntakes(new ArrayList<>());
            return report;
        }
        
        // 计算总摄入次数和总摄入量
        int totalIntakeCount = logs.size();
        BigDecimal totalIntakeAmount = logs.stream()
                .map(WheyProteinLogDO::getAmountGrams)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // 计算日均摄入次数和日均摄入量
        BigDecimal averageDailyIntakeCount = new BigDecimal(totalIntakeCount)
                .divide(new BigDecimal(totalDays), 2, RoundingMode.HALF_UP);
        BigDecimal averageDailyIntakeAmount = totalIntakeAmount
                .divide(new BigDecimal(totalDays), 2, RoundingMode.HALF_UP);
        
        // 按日期分组统计
        Map<LocalDate, List<WheyProteinLogDO>> logsByDate = logs.stream()
                .collect(Collectors.groupingBy(log -> log.getIntakeTime().toLocalDate()));
        
        // 创建每日摄入详情列表
        List<AppWheyProteinReportRespVO.DailyIntake> dailyIntakes = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            AppWheyProteinReportRespVO.DailyIntake dailyIntake = new AppWheyProteinReportRespVO.DailyIntake();
            dailyIntake.setDate(date);
            
            List<WheyProteinLogDO> dailyLogs = logsByDate.getOrDefault(date, new ArrayList<>());
            dailyIntake.setIntakeCount(dailyLogs.size());
            
            BigDecimal dailyAmount = dailyLogs.stream()
                    .map(WheyProteinLogDO::getAmountGrams)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            dailyIntake.setIntakeAmount(dailyAmount);
            
            dailyIntakes.add(dailyIntake);
        }
        
        // 设置报告数据
        report.setTotalIntakeCount(totalIntakeCount);
        report.setTotalIntakeAmount(totalIntakeAmount);
        report.setAverageDailyIntakeCount(averageDailyIntakeCount);
        report.setAverageDailyIntakeAmount(averageDailyIntakeAmount);
        report.setDailyIntakes(dailyIntakes);
        
        return report;
    }

    @Override
    public String uploadWheyProteinPhoto(MultipartFile photo) {
        try {
            // 创建临时文件
            String originalFilename = photo.getOriginalFilename();
            String extension = FileUtil.extName(originalFilename);
            String path = "nutrition/wheyprotein/" + StrUtil.uuid() + "." + extension;
            
            // 上传文件

            try (InputStream inputStream = photo.getInputStream()) {
                return fileApi.createFile(path, IOUtils.toByteArray(inputStream));
            }
        } catch (IOException e) {
            throw exception(WHEY_PROTEIN_LOG_UPLOAD_PHOTO_FAIL);
        }
    }
}
