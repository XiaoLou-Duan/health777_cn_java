package cn.iocoder.yudao.module.nutrition.dal.mysql.wheyprotein;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.nutrition.controller.admin.wheyprotein.vo.WheyProteinLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.wheyprotein.vo.AppWheyProteinLogPageReqVO;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.wheyprotein.WheyProteinLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 乳清蛋白摄入记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface WheyProteinLogMapper extends BaseMapperX<WheyProteinLogDO> {

    default PageResult<WheyProteinLogDO> selectPage(WheyProteinLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WheyProteinLogDO>()
                .eqIfPresent(WheyProteinLogDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(WheyProteinLogDO::getIntakeTime, reqVO.getIntakeTimeStart(), reqVO.getIntakeTimeEnd())
                .orderByDesc(WheyProteinLogDO::getId));
    }

    default PageResult<WheyProteinLogDO> selectPage(AppWheyProteinLogPageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WheyProteinLogDO>()
                .eq(WheyProteinLogDO::getUserId, userId)
                .betweenIfPresent(WheyProteinLogDO::getIntakeTime, reqVO.getIntakeTimeStart(), reqVO.getIntakeTimeEnd())
                .orderByDesc(WheyProteinLogDO::getIntakeTime));
    }

    default List<WheyProteinLogDO> selectListByUserIdAndDate(Long userId, LocalDate date) {
        LocalDateTime startTime = date.atStartOfDay();
        LocalDateTime endTime = date.plusDays(1).atStartOfDay();
        return selectList(new LambdaQueryWrapperX<WheyProteinLogDO>()
                .eq(WheyProteinLogDO::getUserId, userId)
                .between(WheyProteinLogDO::getIntakeTime, startTime, endTime)
                .orderByAsc(WheyProteinLogDO::getIntakeTime));
    }

    default List<WheyProteinLogDO> selectListByUserIdAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startTime = startDate.atStartOfDay();
        LocalDateTime endTime = endDate.plusDays(1).atStartOfDay();
        return selectList(new LambdaQueryWrapperX<WheyProteinLogDO>()
                .eq(WheyProteinLogDO::getUserId, userId)
                .between(WheyProteinLogDO::getIntakeTime, startTime, endTime)
                .orderByAsc(WheyProteinLogDO::getIntakeTime));
    }

}
