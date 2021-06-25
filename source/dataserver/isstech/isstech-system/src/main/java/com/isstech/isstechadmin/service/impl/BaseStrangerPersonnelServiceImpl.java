package com.isstech.isstechadmin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseStrangerPersonnelMapper;
import com.isstech.isstechadmin.domain.BaseStrangerPersonnel;
import com.isstech.isstechadmin.service.IBaseStrangerPersonnelService;

/**
 * 陌生人Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-15
 */
@Service
public class BaseStrangerPersonnelServiceImpl implements IBaseStrangerPersonnelService 
{
    @Autowired
    private BaseStrangerPersonnelMapper baseStrangerPersonnelMapper;

    /**
     * 查询陌生人
     * 
     * @param id 陌生人ID
     * @return 陌生人
     */
    @Override
    public BaseStrangerPersonnel selectBaseStrangerPersonnelById(String id)
    {
        return baseStrangerPersonnelMapper.selectBaseStrangerPersonnelById(id);
    }

    /**
     * 查询陌生人列表
     * 
     * @param baseStrangerPersonnel 陌生人
     * @return 陌生人
     */
    @Override
    public List<BaseStrangerPersonnel> selectBaseStrangerPersonnelList(BaseStrangerPersonnel baseStrangerPersonnel)
    {
        return baseStrangerPersonnelMapper.selectBaseStrangerPersonnelList(baseStrangerPersonnel);
    }

    /**
     * 新增陌生人
     * 
     * @param baseStrangerPersonnel 陌生人
     * @return 结果
     */
    @Override
    public int insertBaseStrangerPersonnel(BaseStrangerPersonnel baseStrangerPersonnel)
    {
        baseStrangerPersonnel.setDelFlag("0");
        return baseStrangerPersonnelMapper.insertBaseStrangerPersonnel(baseStrangerPersonnel);
    }

    /**
     * 修改陌生人
     * 
     * @param baseStrangerPersonnel 陌生人
     * @return 结果
     */
    @Override
    public int updateBaseStrangerPersonnel(BaseStrangerPersonnel baseStrangerPersonnel)
    {
        return baseStrangerPersonnelMapper.updateBaseStrangerPersonnel(baseStrangerPersonnel);
    }

    /**
     * 批量删除陌生人
     * 
     * @param ids 需要删除的陌生人ID
     * @return 结果
     */
    @Override
    public int deleteBaseStrangerPersonnelByIds(String[] ids)
    {
        return baseStrangerPersonnelMapper.deleteBaseStrangerPersonnelByIds(ids);
    }

    /**
     * 批量软删除陌生人
     *
     * @param ids 需要删除的陌生人ID
     * @return 结果
     */
    @Override
    public int updateBaseStrangerPersonnelByIds(String[] ids)
    {
        return baseStrangerPersonnelMapper.updateBaseStrangerPersonnelByIds(ids);
    }

    /**
     * 删除陌生人信息
     * 
     * @param id 陌生人ID
     * @return 结果
     */
    @Override
    public int deleteBaseStrangerPersonnelById(String id)
    {
        return baseStrangerPersonnelMapper.deleteBaseStrangerPersonnelById(id);
    }
}
