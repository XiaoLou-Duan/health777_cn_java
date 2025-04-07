package cn.iocoder.yudao.module.nutrition.service.proteinsupplement.impl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementPageReqVO;
import cn.iocoder.yudao.module.nutrition.controller.admin.proteinsupplement.vo.ProteinSupplementUpdateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementCreateReqVO;
import cn.iocoder.yudao.module.nutrition.controller.app.proteinsupplement.vo.AppProteinSupplementPageReqVO;
import cn.iocoder.yudao.module.nutrition.convert.proteinsupplement.ProteinSupplementConvert;
import cn.iocoder.yudao.module.nutrition.dal.dataobject.proteinsupplement.ProteinSupplementDO;
import cn.iocoder.yudao.module.nutrition.dal.mysql.proteinsupplement.ProteinSupplementMapper;
import cn.iocoder.yudao.module.nutrition.service.proteinsupplement.ProteinSupplementService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.nutrition.enums.ErrorCodeConstants.PROTEIN_SUPPLEMENT_NOT_EXISTS;

/**
 * 蛋白质补充剂摄入记录 Service 实现类
 */
@Service
@Validated
public class ProteinSupplementServiceImpl implements ProteinSupplementService {

    @Resource
    private ProteinSupplementMapper proteinSupplementMapper;

    @Override
    public Long createProteinSupplement(ProteinSupplementCreateReqVO createReqVO) {
        // 插入
        ProteinSupplementDO proteinSupplement = ProteinSupplementConvert.INSTANCE.convert(createReqVO);
        proteinSupplementMapper.insert(proteinSupplement);
        // 返回
        return proteinSupplement.getId();
    }

    @Override
    public Long createProteinSupplement(AppProteinSupplementCreateReqVO createReqVO) {
        // 插入
        ProteinSupplementDO proteinSupplement = ProteinSupplementConvert.INSTANCE.convert(createReqVO);
        proteinSupplementMapper.insert(proteinSupplement);
        // 返回
        return proteinSupplement.getId();
    }

    @Override
    public void updateProteinSupplement(ProteinSupplementUpdateReqVO updateReqVO) {
        // 校验存在
        validateProteinSupplementExists(updateReqVO.getId());
        // 更新
        ProteinSupplementDO updateObj = ProteinSupplementConvert.INSTANCE.convert(updateReqVO);
        proteinSupplementMapper.updateById(updateObj);
    }

    @Override
    public void deleteProteinSupplement(Long id) {
        // 校验存在
        validateProteinSupplementExists(id);
        // 删除
        proteinSupplementMapper.deleteById(id);
    }

    private void validateProteinSupplementExists(Long id) {
        if (proteinSupplementMapper.selectById(id) == null) {
            throw exception(PROTEIN_SUPPLEMENT_NOT_EXISTS);
        }
    }

    @Override
    public ProteinSupplementDO getProteinSupplement(Long id) {
        return proteinSupplementMapper.selectById(id);
    }

    @Override
    public List<ProteinSupplementDO> getProteinSupplementListByUserId(Long userId) {
        return proteinSupplementMapper.selectListByUserId(userId);
    }

    @Override
    public List<ProteinSupplementDO> getProteinSupplementListByUserIdAndTime(Long userId, LocalDateTime startTime,
            LocalDateTime endTime) {
        return proteinSupplementMapper.selectListByUserIdAndTime(userId, startTime, endTime);
    }

    @Override
    public PageResult<ProteinSupplementDO> getProteinSupplementPage(ProteinSupplementPageReqVO pageReqVO) {
        return proteinSupplementMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ProteinSupplementDO> getProteinSupplementPage(AppProteinSupplementPageReqVO pageReqVO) {
        return proteinSupplementMapper.selectPage(pageReqVO);
    }
}