package cn.iocoder.yudao.module.nutrition.service.wheyprotein;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogSaveReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinReportRespVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.wheyprotein.WheyProteinLogDO;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * 乳清蛋白摄入记录 Service 接口
 *
 * @author 芋道源码
 */
public interface WheyProteinLogService {

    /**
     * 创建乳清蛋白摄入记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWheyProteinLog(@Valid WheyProteinLogSaveReqVO createReqVO);

    /**
     * 更新乳清蛋白摄入记录
     *
     * @param updateReqVO 更新信息
     */
    void updateWheyProteinLog(@Valid WheyProteinLogSaveReqVO updateReqVO);

    /**
     * 删除乳清蛋白摄入记录
     *
     * @param id 编号
     */
    void deleteWheyProteinLog(Long id);

    /**
     * 获得乳清蛋白摄入记录
     *
     * @param id 编号
     * @return 乳清蛋白摄入记录
     */
    WheyProteinLogDO getWheyProteinLog(Long id);

    /**
     * 获得乳清蛋白摄入记录分页
     *
     * @param pageReqVO 分页查询
     * @return 乳清蛋白摄入记录分页
     */
    PageResult<WheyProteinLogDO> getWheyProteinLogPage(WheyProteinLogPageReqVO pageReqVO);

    /**
     * 创建用户乳清蛋白摄入记录（App）
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWheyProteinLog(Long userId, @Valid AppWheyProteinLogCreateReqVO createReqVO);

    /**
     * 获得用户乳清蛋白摄入记录分页（App）
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     * @return 乳清蛋白摄入记录分页
     */
    PageResult<WheyProteinLogDO> getWheyProteinLogPage(Long userId, AppWheyProteinLogPageReqVO pageReqVO);

    /**
     * 获得用户指定日期的乳清蛋白摄入记录列表
     *
     * @param userId 用户编号
     * @param date 日期
     * @return 乳清蛋白摄入记录列表
     */
    List<WheyProteinLogDO> getWheyProteinLogListByUserIdAndDate(Long userId, LocalDate date);

    /**
     * 获得用户乳清蛋白摄入报告
     *
     * @param userId 用户编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 乳清蛋白摄入报告
     */
    AppWheyProteinReportRespVO getWheyProteinReport(Long userId, LocalDate startDate, LocalDate endDate);

    /**
     * 上传乳清蛋白照片
     *
     * @param photo 照片文件
     * @return 照片URL
     */
    String uploadWheyProteinPhoto(MultipartFile photo);

}
