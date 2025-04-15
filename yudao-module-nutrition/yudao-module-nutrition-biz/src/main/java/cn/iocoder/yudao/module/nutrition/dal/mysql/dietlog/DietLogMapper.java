package cn.iocoder.yudao.module.nutrition.dal.mysql.dietlog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.nutrition.controller.admin.dietlog.vo.DietLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.dietlog.vo.AppDietLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.dietlog.DietLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 饮食日志 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DietLogMapper extends BaseMapperX<DietLogDO> {

    default PageResult<DietLogDO> selectPage(DietLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DietLogDO>()
                .eqIfPresent(DietLogDO::getUserId, reqVO.getUserId())
                .eqIfPresent(DietLogDO::getMealType, reqVO.getMealType())
                .betweenIfPresent(DietLogDO::getLogTime, reqVO.getLogTimeStart(), reqVO.getLogTimeEnd())
                .orderByDesc(DietLogDO::getId));
    }

    default PageResult<DietLogDO> selectPage(AppDietLogPageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DietLogDO>()
                .eq(DietLogDO::getUserId, userId)
                .eqIfPresent(DietLogDO::getMealType, reqVO.getMealType())
                .betweenIfPresent(DietLogDO::getLogTime, reqVO.getLogTimeStart(), reqVO.getLogTimeEnd())
                .orderByDesc(DietLogDO::getLogTime));
    }

    default List<DietLogDO> selectListByUserIdAndDate(Long userId, LocalDate date) {
        LocalDateTime startTime = date.atStartOfDay();
        LocalDateTime endTime = date.plusDays(1).atStartOfDay();
        return selectList(new LambdaQueryWrapperX<DietLogDO>()
                .eq(DietLogDO::getUserId, userId)
                .between(DietLogDO::getLogTime, startTime, endTime)
                .orderByAsc(DietLogDO::getLogTime));
    }

}
