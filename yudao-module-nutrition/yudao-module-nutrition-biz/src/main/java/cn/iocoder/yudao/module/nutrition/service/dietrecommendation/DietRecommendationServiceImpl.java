package cn.iocoder.yudao.module.nutrition.service.dietrecommendation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationPageReqVO;
import cn.iocoder.yudao.module.nutrition.convert.dietrecommendation.DietRecommendationConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietrecommendation.DietRecommendationDO;
import cn.iocoder.yudao.module.nutrition.dal.mysql.dietrecommendation.DietRecommendationMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.nutrition.enums.ErrorCodeConstants.DIET_RECOMMENDATION_NOT_EXISTS;

@Service
@Validated
public class DietRecommendationServiceImpl implements DietRecommendationService {

    @Resource
    private DietRecommendationMapper dietRecommendationMapper;

    @Override
    public Long createDietRecommendation(DietRecommendationCreateReqVO createReqVO) {
        // 插入
        DietRecommendationDO dietRecommendation = DietRecommendationConvert.INSTANCE.convert(createReqVO);
        dietRecommendationMapper.insert(dietRecommendation);
        // 返回
        return dietRecommendation.getId();
    }

    @Override
    public void updateDietRecommendation(DietRecommendationUpdateReqVO updateReqVO) {
        // 校验存在
        validateDietRecommendationExists(updateReqVO.getId());
        // 更新
        DietRecommendationDO updateObj = DietRecommendationConvert.INSTANCE.convert(updateReqVO);
        dietRecommendationMapper.updateById(updateObj);
    }

    @Override
    public void deleteDietRecommendation(Long id) {
        // 校验存在
        validateDietRecommendationExists(id);
        // 删除
        dietRecommendationMapper.deleteById(id);
    }

    private void validateDietRecommendationExists(Long id) {
        if (dietRecommendationMapper.selectById(id) == null) {
            throw exception(DIET_RECOMMENDATION_NOT_EXISTS);
        }
    }

    @Override
    public DietRecommendationDO getDietRecommendation(Long id) {
        return dietRecommendationMapper.selectById(id);
    }

    @Override
    public List<DietRecommendationDO> getDietRecommendationList(List<Long> ids) {
        return dietRecommendationMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DietRecommendationDO> getDietRecommendationPage(DietRecommendationPageReqVO pageReqVO) {
        return dietRecommendationMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<DietRecommendationDO> getDietRecommendationPage(AppDietRecommendationPageReqVO pageReqVO) {
        return dietRecommendationMapper.selectPage(pageReqVO);
    }

    @Override
    public DietRecommendationDO getDietRecommendationByUserIdAndRecommendationDate(Long userId,
            LocalDate recommendationDate) {
        return dietRecommendationMapper.selectByUserIdAndRecommendationDate(userId, recommendationDate);
    }

    @Override
    public List<DietRecommendationDO> getDietRecommendationListByUserId(Long userId) {
        return dietRecommendationMapper.selectListByUserId(userId);
    }

    @Override
    public Long createDietRecommendation(AppDietRecommendationCreateReqVO createReqVO) {
        // 插入
        DietRecommendationDO dietRecommendation = DietRecommendationConvert.INSTANCE.convert(createReqVO);
        dietRecommendationMapper.insert(dietRecommendation);
        // 返回
        return dietRecommendation.getId();
    }

}