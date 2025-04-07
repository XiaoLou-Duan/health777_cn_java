package cn.iocoder.yudao.module.nutrition.service.dietrecommendation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietrecommendation.DietRecommendationDO;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

public interface DietRecommendationService {

    /**
     * 创建饮食建议
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDietRecommendation(@Valid DietRecommendationCreateReqVO createReqVO);

    /**
     * 更新饮食建议
     *
     * @param updateReqVO 更新信息
     */
    void updateDietRecommendation(@Valid DietRecommendationUpdateReqVO updateReqVO);

    /**
     * 删除饮食建议
     *
     * @param id 编号
     */
    void deleteDietRecommendation(Long id);

    /**
     * 获得饮食建议
     *
     * @param id 编号
     * @return 饮食建议
     */
    DietRecommendationDO getDietRecommendation(Long id);

    /**
     * 获得饮食建议列表
     *
     * @param ids 编号
     * @return 饮食建议列表
     */
    List<DietRecommendationDO> getDietRecommendationList(List<Long> ids);

    /**
     * 获得饮食建议分页
     *
     * @param pageReqVO 分页查询
     * @return 饮食建议分页
     */
    PageResult<DietRecommendationDO> getDietRecommendationPage(DietRecommendationPageReqVO pageReqVO);

    /**
     * 获得饮食建议分页
     *
     * @param pageReqVO 分页查询
     * @return 饮食建议分页
     */
    PageResult<DietRecommendationDO> getDietRecommendationPage(AppDietRecommendationPageReqVO pageReqVO);

    /**
     * 获得饮食建议
     *
     * @param userId             用户编号
     * @param recommendationDate 建议日期
     * @return 饮食建议
     */
    DietRecommendationDO getDietRecommendationByUserIdAndRecommendationDate(Long userId, LocalDate recommendationDate);

    /**
     * 获得饮食建议列表
     *
     * @param userId 用户编号
     * @return 饮食建议列表
     */
    List<DietRecommendationDO> getDietRecommendationListByUserId(Long userId);

    /**
     * 创建饮食建议
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDietRecommendation(@Valid AppDietRecommendationCreateReqVO createReqVO);

}