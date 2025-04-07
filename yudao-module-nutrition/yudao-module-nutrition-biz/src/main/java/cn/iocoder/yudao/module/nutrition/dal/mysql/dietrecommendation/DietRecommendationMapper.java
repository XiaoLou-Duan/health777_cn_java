package cn.iocoder.yudao.module.nutrition.dal.mysql.dietrecommendation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietrecommendation.vo.DietRecommendationPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietrecommendation.vo.AppDietRecommendationPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietrecommendation.DietRecommendationDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DietRecommendationMapper extends BaseMapperX<DietRecommendationDO> {

    default PageResult<DietRecommendationDO> selectPage(DietRecommendationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DietRecommendationDO>()
                .eqIfPresent(DietRecommendationDO::getUserId, reqVO.getUserId())
                .eqIfPresent(DietRecommendationDO::getRecommendationDate, reqVO.getRecommendationDate())
                .eqIfPresent(DietRecommendationDO::getIsRead, reqVO.getIsRead())
                .orderByDesc(DietRecommendationDO::getCreateTime));
    }

    default PageResult<DietRecommendationDO> selectPage(AppDietRecommendationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DietRecommendationDO>()
                .eqIfPresent(DietRecommendationDO::getUserId, reqVO.getUserId())
                .eqIfPresent(DietRecommendationDO::getRecommendationDate, reqVO.getRecommendationDate())
                .eqIfPresent(DietRecommendationDO::getIsRead, reqVO.getIsRead())
                .orderByDesc(DietRecommendationDO::getCreateTime));
    }

    default DietRecommendationDO selectByUserIdAndRecommendationDate(Long userId, LocalDate recommendationDate) {
        return selectOne(DietRecommendationDO::getUserId, userId,
                DietRecommendationDO::getRecommendationDate, recommendationDate);
    }

    default List<DietRecommendationDO> selectListByUserId(Long userId) {
        return selectList(DietRecommendationDO::getUserId, userId);
    }

}