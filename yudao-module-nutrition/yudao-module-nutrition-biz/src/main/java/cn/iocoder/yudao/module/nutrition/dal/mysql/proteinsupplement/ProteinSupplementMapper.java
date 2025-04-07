package cn.iocoder.yudao.module.nutrition.dal.mysql.proteinsupplement;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.proteinsupplement.ProteinSupplementDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 蛋白质补充剂摄入记录 Mapper
 */
@Mapper
public interface ProteinSupplementMapper extends BaseMapperX<ProteinSupplementDO> {

    default PageResult<ProteinSupplementDO> selectPage(ProteinSupplementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProteinSupplementDO>()
                .eqIfPresent(ProteinSupplementDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(ProteinSupplementDO::getSupplementTime, reqVO.getSupplementTime())
                .likeIfPresent(ProteinSupplementDO::getSupplementType, reqVO.getSupplementType())
                .orderByDesc(ProteinSupplementDO::getId));
    }

    default PageResult<ProteinSupplementDO> selectPage(AppProteinSupplementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProteinSupplementDO>()
                .eqIfPresent(ProteinSupplementDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(ProteinSupplementDO::getSupplementTime, reqVO.getSupplementTime())
                .likeIfPresent(ProteinSupplementDO::getSupplementType, reqVO.getSupplementType())
                .orderByDesc(ProteinSupplementDO::getId));
    }

    default List<ProteinSupplementDO> selectListByUserId(Long userId) {
        return selectList(new LambdaQueryWrapperX<ProteinSupplementDO>()
                .eq(ProteinSupplementDO::getUserId, userId)
                .orderByDesc(ProteinSupplementDO::getSupplementTime));
    }

    default List<ProteinSupplementDO> selectListByUserIdAndTime(Long userId, LocalDateTime startTime,
            LocalDateTime endTime) {
        return selectList(new LambdaQueryWrapperX<ProteinSupplementDO>()
                .eq(ProteinSupplementDO::getUserId, userId)
                .between(ProteinSupplementDO::getSupplementTime, startTime, endTime)
                .orderByDesc(ProteinSupplementDO::getSupplementTime));
    }
}