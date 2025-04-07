package cn.iocoder.yudao.module.nutrition.service.proteinsupplement;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.proteinsupplement.ProteinSupplementDO;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 蛋白质补充剂摄入记录 Service 接口
 */
public interface ProteinSupplementService {

    /**
     * 创建蛋白质补充剂摄入记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProteinSupplement(@Valid ProteinSupplementCreateReqVO createReqVO);

    /**
     * APP端创建蛋白质补充剂摄入记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProteinSupplement(@Valid AppProteinSupplementCreateReqVO createReqVO);

    /**
     * 更新蛋白质补充剂摄入记录
     *
     * @param updateReqVO 更新信息
     */
    void updateProteinSupplement(@Valid ProteinSupplementUpdateReqVO updateReqVO);

    /**
     * 删除蛋白质补充剂摄入记录
     *
     * @param id 编号
     */
    void deleteProteinSupplement(Long id);

    /**
     * 获得蛋白质补充剂摄入记录
     *
     * @param id 编号
     * @return 蛋白质补充剂摄入记录
     */
    ProteinSupplementDO getProteinSupplement(Long id);

    /**
     * 获得指定用户的蛋白质补充剂摄入记录列表
     *
     * @param userId 用户ID
     * @return 蛋白质补充剂摄入记录列表
     */
    List<ProteinSupplementDO> getProteinSupplementListByUserId(Long userId);

    /**
     * 获得指定用户在指定时间范围内的蛋白质补充剂摄入记录列表
     *
     * @param userId    用户ID
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 蛋白质补充剂摄入记录列表
     */
    List<ProteinSupplementDO> getProteinSupplementListByUserIdAndTime(Long userId, LocalDateTime startTime,
            LocalDateTime endTime);

    /**
     * 获得蛋白质补充剂摄入记录分页
     *
     * @param pageReqVO 分页查询
     * @return 蛋白质补充剂摄入记录分页
     */
    PageResult<ProteinSupplementDO> getProteinSupplementPage(ProteinSupplementPageReqVO pageReqVO);

    /**
     * 获得APP蛋白质补充剂摄入记录分页
     *
     * @param pageReqVO 分页查询
     * @return 蛋白质补充剂摄入记录分页
     */
    PageResult<ProteinSupplementDO> getProteinSupplementPage(AppProteinSupplementPageReqVO pageReqVO);

}