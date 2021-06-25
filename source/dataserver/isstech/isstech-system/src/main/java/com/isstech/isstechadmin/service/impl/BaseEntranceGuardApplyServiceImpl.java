package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseEntranceGuardApplyMapper;
import com.isstech.isstechadmin.domain.BaseEntranceGuardApply;
import com.isstech.isstechadmin.service.IBaseEntranceGuardApplyService;

/**
 *  门禁申请Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Service
public class BaseEntranceGuardApplyServiceImpl implements IBaseEntranceGuardApplyService 
{
    @Autowired
    private BaseEntranceGuardApplyMapper baseEntranceGuardApplyMapper;

    /**
     * 查询 门禁申请
     * 
     * @param id  门禁申请ID
     * @return  门禁申请
     */
    @Override
    public BaseEntranceGuardApply selectBaseEntranceGuardApplyById(String id)
    {
        return baseEntranceGuardApplyMapper.selectBaseEntranceGuardApplyById(id);
    }

    /**
     * 查询 门禁申请列表
     * 
     * @param baseEntranceGuardApply  门禁申请
     * @return  门禁申请
     */
    @Override
    public List<BaseEntranceGuardApply> selectBaseEntranceGuardApplyList(BaseEntranceGuardApply baseEntranceGuardApply)
    {
        return baseEntranceGuardApplyMapper.selectBaseEntranceGuardApplyList(baseEntranceGuardApply);
    }

    /**
     * 新增 门禁申请
     * 
     * @param baseEntranceGuardApply  门禁申请
     * @return 结果
     */
    @Override
    public int insertBaseEntranceGuardApply(BaseEntranceGuardApply baseEntranceGuardApply)
    {
        baseEntranceGuardApply.setId(baseEntranceGuardApplyMapper.selectUuid());
        baseEntranceGuardApply.setCreateBy(SecurityUtils.getUsername());
        baseEntranceGuardApply.setCreateTime(DateUtils.getNowDate());
        baseEntranceGuardApply.setDelFlag("0");
        return baseEntranceGuardApplyMapper.insertBaseEntranceGuardApply(baseEntranceGuardApply);
    }

    /**
     * 修改 门禁申请
     * 
     * @param baseEntranceGuardApply  门禁申请
     * @return 结果
     */
    @Override
    public int updateBaseEntranceGuardApply(BaseEntranceGuardApply baseEntranceGuardApply)
    {
        baseEntranceGuardApply.setUpdateBy(SecurityUtils.getUsername());
        baseEntranceGuardApply.setUpdateTime(DateUtils.getNowDate());
        return baseEntranceGuardApplyMapper.updateBaseEntranceGuardApply(baseEntranceGuardApply);
    }

    /**
     * 批量删除 门禁申请
     * 
     * @param ids 需要删除的 门禁申请ID
     * @return 结果
     */
    @Override
    public int deleteBaseEntranceGuardApplyByIds(String[] ids)
    {
        return baseEntranceGuardApplyMapper.deleteBaseEntranceGuardApplyByIds(ids);
    }

    /**
     * 批量软删除 门禁申请
     *
     * @param ids 需要删除的 门禁申请ID
     * @return 结果
     */
    @Override
    public int updateBaseEntranceGuardApplyByIds(String[] ids)
    {
        return baseEntranceGuardApplyMapper.updateBaseEntranceGuardApplyByIds(ids);
    }

    /**
     * 删除 门禁申请信息
     * 
     * @param id  门禁申请ID
     * @return 结果
     */
    @Override
    public int deleteBaseEntranceGuardApplyById(String id)
    {
        return baseEntranceGuardApplyMapper.deleteBaseEntranceGuardApplyById(id);
    }
}
