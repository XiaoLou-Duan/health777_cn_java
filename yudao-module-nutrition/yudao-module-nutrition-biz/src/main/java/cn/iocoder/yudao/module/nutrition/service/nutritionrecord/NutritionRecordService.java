package cn.iocoder.yudao.module.nutrition.service.nutritionrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.nutritionrecord.vo.AppNutritionRecordPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.nutritionrecord.NutritionRecordDO;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * 营养摄入记录 Service 接口
 */
public interface NutritionRecordService {

    /**
     * 创建营养摄入记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createNutritionRecord(@Valid NutritionRecordCreateReqVO createReqVO);

    /**
     * 更新营养摄入记录
     *
     * @param updateReqVO 更新信息
     */
    void updateNutritionRecord(@Valid NutritionRecordUpdateReqVO updateReqVO);

    /**
     * 删除营养摄入记录
     *
     * @param id 编号
     */
    void deleteNutritionRecord(Long id);

    /**
     * 获得营养摄入记录
     *
     * @param id 编号
     * @return 营养摄入记录
     */
    NutritionRecordDO getNutritionRecord(Long id);

    /**
     * 根据用户ID和记录日期获取营养摄入记录
     *
     * @param userId     用户ID
     * @param recordDate 记录日期
     * @return 营养摄入记录
     */
    NutritionRecordDO getNutritionRecordByUserIdAndDate(Long userId, LocalDate recordDate);

    /**
     * 获得指定用户的营养摄入记录列表
     *
     * @param userId 用户ID
     * @return 营养摄入记录列表
     */
    List<NutritionRecordDO> getNutritionRecordListByUserId(Long userId);

    /**
     * 获得营养摄入记录分页
     *
     * @param pageReqVO 分页查询
     * @return 营养摄入记录分页
     */
    PageResult<NutritionRecordDO> getNutritionRecordPage(NutritionRecordPageReqVO pageReqVO);

    /**
     * 获得APP营养摄入记录分页
     *
     * @param pageReqVO 分页查询
     * @return 营养摄入记录分页
     */
    PageResult<NutritionRecordDO> getNutritionRecordPage(AppNutritionRecordPageReqVO pageReqVO);

}