package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseBlackPersonnelMapper;
import com.isstech.isstechadmin.domain.BaseBlackPersonnel;
import com.isstech.isstechadmin.service.IBaseBlackPersonnelService;

/**
 * 人员黑名单Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-22
 */
@Service
public class BaseBlackPersonnelServiceImpl implements IBaseBlackPersonnelService 
{
    @Autowired
    private BaseBlackPersonnelMapper baseBlackPersonnelMapper;

    /**
     * 查询人员黑名单
     * 
     * @param id 人员黑名单ID
     * @return 人员黑名单
     */
    @Override
    public BaseBlackPersonnel selectBaseBlackPersonnelById(String id)
    {
        return baseBlackPersonnelMapper.selectBaseBlackPersonnelById(id);
    }

    /**
     * 查询人员黑名单列表
     * 
     * @param baseBlackPersonnel 人员黑名单
     * @return 人员黑名单
     */
    @Override
    public List<BaseBlackPersonnel> selectBaseBlackPersonnelList(BaseBlackPersonnel baseBlackPersonnel)
    {
        return baseBlackPersonnelMapper.selectBaseBlackPersonnelList(baseBlackPersonnel);
    }

    /**
     * 新增人员黑名单
     * 
     * @param baseBlackPersonnel 人员黑名单
     * @return 结果
     */
    @Override
    public int insertBaseBlackPersonnel(BaseBlackPersonnel baseBlackPersonnel)
    {
        baseBlackPersonnel.setCreateBy(SecurityUtils.getUsername());
        baseBlackPersonnel.setCreateTime(DateUtils.getNowDate());
        return baseBlackPersonnelMapper.insertBaseBlackPersonnel(baseBlackPersonnel);
    }

    /**
     * 修改人员黑名单
     * 
     * @param baseBlackPersonnel 人员黑名单
     * @return 结果
     */
    @Override
    public int updateBaseBlackPersonnel(BaseBlackPersonnel baseBlackPersonnel)
    {
        baseBlackPersonnel.setUpdateBy(SecurityUtils.getUsername());
        baseBlackPersonnel.setUpdateTime(DateUtils.getNowDate());
        return baseBlackPersonnelMapper.updateBaseBlackPersonnel(baseBlackPersonnel);
    }

    /**
     * 批量删除人员黑名单
     * 
     * @param ids 需要删除的人员黑名单ID
     * @return 结果
     */
    @Override
    public int deleteBaseBlackPersonnelByIds(String[] ids)
    {
        return baseBlackPersonnelMapper.deleteBaseBlackPersonnelByIds(ids);
    }

    /**
     * 批量软删除人员黑名单
     *
     * @param ids 需要删除的人员黑名单ID
     * @return 结果
     */
    @Override
    public int updateBaseBlackPersonnelByIds(String[] ids)
    {
        return baseBlackPersonnelMapper.updateBaseBlackPersonnelByIds(ids);
    }

    /**
     * 删除人员黑名单信息
     * 
     * @param id 人员黑名单ID
     * @return 结果
     */
    @Override
    public int deleteBaseBlackPersonnelById(String id)
    {
        return baseBlackPersonnelMapper.deleteBaseBlackPersonnelById(id);
    }
}
