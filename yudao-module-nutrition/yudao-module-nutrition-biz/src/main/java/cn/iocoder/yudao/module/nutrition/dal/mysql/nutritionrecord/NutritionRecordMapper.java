package cn.iocoder.yudao.module.nutrition.dal.mysql.nutritionrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.nutrition.controller.admin.nutritionrecord.vo.NutritionRecordPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.nutritionrecord.vo.AppNutritionRecordPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.nutritionrecord.NutritionRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * 营养摄入记录 Mapper
 */
@Mapper
public interface NutritionRecordMapper extends BaseMapperX<NutritionRecordDO> {

    default PageResult<NutritionRecordDO> selectPage(NutritionRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NutritionRecordDO>()
                .eqIfPresent(NutritionRecordDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(NutritionRecordDO::getRecordDate, reqVO.getRecordDate())
                .geIfPresent(NutritionRecordDO::getAchievementRate, reqVO.getMinAchievementRate())
                .leIfPresent(NutritionRecordDO::getAchievementRate, reqVO.getMaxAchievementRate())
                .orderByDesc(NutritionRecordDO::getId));
    }

    default PageResult<NutritionRecordDO> selectPage(AppNutritionRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NutritionRecordDO>()
                .eqIfPresent(NutritionRecordDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(NutritionRecordDO::getRecordDate, reqVO.getRecordDate())
                .orderByDesc(NutritionRecordDO::getId));
    }

    default NutritionRecordDO selectByUserIdAndRecordDate(Long userId, LocalDate recordDate) {
        return selectOne(new LambdaQueryWrapperX<NutritionRecordDO>()
                .eq(NutritionRecordDO::getUserId, userId)
                .eq(NutritionRecordDO::getRecordDate, recordDate));
    }

    default List<NutritionRecordDO> selectListByUserId(Long userId) {
        return selectList(new LambdaQueryWrapperX<NutritionRecordDO>()
                .eq(NutritionRecordDO::getUserId, userId)
                .orderByDesc(NutritionRecordDO::getRecordDate));
    }
}