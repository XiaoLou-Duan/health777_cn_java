package cn.iocoder.yudao.module.nutrition.service.dietlog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogSaveReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietlog.DietLogDO;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * 饮食日志 Service 接口
 *
 * @author 芋道源码
 */
public interface DietLogService {

    /**
     * 创建饮食日志
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDietLog(@Valid DietLogSaveReqVO createReqVO);

    /**
     * 更新饮食日志
     *
     * @param updateReqVO 更新信息
     */
    void updateDietLog(@Valid DietLogSaveReqVO updateReqVO);

    /**
     * 删除饮食日志
     *
     * @param id 编号
     */
    void deleteDietLog(Long id);

    /**
     * 获得饮食日志
     *
     * @param id 编号
     * @return 饮食日志
     */
    DietLogDO getDietLog(Long id);

    /**
     * 获得饮食日志分页
     *
     * @param pageReqVO 分页查询
     * @return 饮食日志分页
     */
    PageResult<DietLogDO> getDietLogPage(DietLogPageReqVO pageReqVO);

    /**
     * 创建用户饮食日志（App）
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDietLog(Long userId, @Valid AppDietLogCreateReqVO createReqVO);

    /**
     * 更新用户饮食日志（App）
     *
     * @param userId 用户编号
     * @param updateReqVO 更新信息
     */
    void updateDietLog(Long userId, @Valid AppDietLogUpdateReqVO updateReqVO);

    /**
     * 获得用户饮食日志分页（App）
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     * @return 饮食日志分页
     */
    PageResult<DietLogDO> getDietLogPage(Long userId, AppDietLogPageReqVO pageReqVO);

    /**
     * 获得用户指定日期的饮食日志列表
     *
     * @param userId 用户编号
     * @param date 日期
     * @return 饮食日志列表
     */
    List<DietLogDO> getDietLogListByUserIdAndDate(Long userId, LocalDate date);

    /**
     * 上传食物照片
     *
     * @param photo 照片文件
     * @return 照片URL
     */
    String uploadFoodPhoto(MultipartFile photo);

}
