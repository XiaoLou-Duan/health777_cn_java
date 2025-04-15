package cn.iocoder.yudao.module.nutrition.service.dietlog;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogSaveReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.convert.dietlog.DietLogConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietlog.DietLogDO;
import cn.iocoder.yudao.module.nutrition.dal.mysql.dietlog.DietLogMapper;
import cn.iocoder.yudao.module.nutrition.service.ai.AiRecognitionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.nutrition.enums.ErrorCodeConstants.*;

/**
 * 饮食日志 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class DietLogServiceImpl implements DietLogService {

    @Resource
    private DietLogMapper dietLogMapper;

    @Resource
    private FileApi fileApi;

    @Resource
    private AiRecognitionLogService aiRecognitionLogService;

    @Override
    public Long createDietLog(DietLogSaveReqVO createReqVO) {
        // 插入
        DietLogDO dietLog = DietLogConvert.INSTANCE.convert(createReqVO);
        dietLogMapper.insert(dietLog);
        // 返回
        return dietLog.getId();
    }

    @Override
    public void updateDietLog(DietLogSaveReqVO updateReqVO) {
        // 校验存在
        validateDietLogExists(updateReqVO.getId());
        // 更新
        DietLogDO updateObj = DietLogConvert.INSTANCE.convert(updateReqVO);
        dietLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteDietLog(Long id) {
        // 校验存在
        validateDietLogExists(id);
        // 删除
        dietLogMapper.deleteById(id);
    }

    private void validateDietLogExists(Long id) {
        if (dietLogMapper.selectById(id) == null) {
            throw exception(DIET_LOG_NOT_EXISTS);
        }
    }

    @Override
    public DietLogDO getDietLog(Long id) {
        return dietLogMapper.selectById(id);
    }

    @Override
    public PageResult<DietLogDO> getDietLogPage(DietLogPageReqVO pageReqVO) {
        return dietLogMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDietLog(Long userId, AppDietLogCreateReqVO createReqVO) {
        // 上传照片
        String photoUrl = uploadFoodPhoto(createReqVO.getPhoto());

        // 插入饮食日志
        DietLogDO dietLog = DietLogConvert.INSTANCE.convert(createReqVO);
        dietLog.setUserId(userId);
        dietLog.setPhotoUrl(photoUrl);
        dietLogMapper.insert(dietLog);

        // 调用AI识别服务
        try {
            Map<String, Map<String, Object>> recognitionResult = aiRecognitionLogService.recognizeFood(dietLog.getId(), photoUrl);
            if (CollUtil.isNotEmpty(recognitionResult)) {
                // 提取识别出的食物名称
                String recognizedFoods = recognitionResult.keySet().stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.joining(","));
                
                // 计算总蛋白质和总热量
                BigDecimal totalProtein = BigDecimal.ZERO;
                BigDecimal totalCalories = BigDecimal.ZERO;
                
                for (Map<String, Object> nutritionInfo : recognitionResult.values()) {
                    if (nutritionInfo.containsKey("protein")) {
                        Object proteinObj = nutritionInfo.get("protein");
                        if (proteinObj instanceof Number) {
                            totalProtein = totalProtein.add(new BigDecimal(proteinObj.toString()));
                        }
                    }
                    
                    if (nutritionInfo.containsKey("calories")) {
                        Object caloriesObj = nutritionInfo.get("calories");
                        if (caloriesObj instanceof Number) {
                            totalCalories = totalCalories.add(new BigDecimal(caloriesObj.toString()));
                        }
                    }
                }
                
                // 更新饮食日志
                dietLog.setRecognizedFoods(recognizedFoods);
                dietLog.setEstimatedProtein(totalProtein);
                dietLog.setEstimatedCalories(totalCalories);
                dietLogMapper.updateById(dietLog);
            }
        } catch (Exception e) {
            log.error("[createDietLog][用户({}) 饮食日志({}) AI识别异常]", userId, dietLog.getId(), e);
            // 不影响主流程，继续执行
        }

        return dietLog.getId();
    }

    @Override
    public void updateDietLog(Long userId, AppDietLogUpdateReqVO updateReqVO) {
        // 校验存在，并且是自己的饮食日志
        DietLogDO dietLog = validateDietLogExists(userId, updateReqVO.getId());
        
        // 更新
        DietLogDO updateObj = DietLogConvert.INSTANCE.convert(updateReqVO);
        updateObj.setId(dietLog.getId());
        dietLogMapper.updateById(updateObj);
    }

    private DietLogDO validateDietLogExists(Long userId, Long id) {
        DietLogDO dietLog = dietLogMapper.selectById(id);
        if (dietLog == null) {
            throw exception(DIET_LOG_NOT_EXISTS);
        }
        if (!dietLog.getUserId().equals(userId)) {
            throw exception(DIET_LOG_NOT_SELF);
        }
        return dietLog;
    }

    @Override
    public PageResult<DietLogDO> getDietLogPage(Long userId, AppDietLogPageReqVO pageReqVO) {
        return dietLogMapper.selectPage(pageReqVO, userId);
    }

    @Override
    public List<DietLogDO> getDietLogListByUserIdAndDate(Long userId, LocalDate date) {
        return dietLogMapper.selectListByUserIdAndDate(userId, date);
    }

    @Override
    public String uploadFoodPhoto(MultipartFile photo) {
        try {
            // 创建临时文件
            String originalFilename = photo.getOriginalFilename();
            String extension = FileUtil.extName(originalFilename);
            String path = "nutrition/diet/" + StrUtil.uuid() + "." + extension;
            
            // 上传文件
            try (InputStream inputStream = photo.getInputStream()) {
                return fileApi.createFile(path, IOUtils.toByteArray(inputStream));
            }
        } catch (IOException e) {
            throw exception(DIET_LOG_UPLOAD_PHOTO_FAIL);
        }
    }
}
