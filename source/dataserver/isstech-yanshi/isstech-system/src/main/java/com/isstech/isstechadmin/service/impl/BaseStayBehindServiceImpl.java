package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.domain.BaseStayBehind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseStayBehindMapper;
import com.isstech.isstechadmin.service.IBaseStayBehindService;

/**
 * 留守人员基本信息Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-15
 */
@Service
public class BaseStayBehindServiceImpl implements IBaseStayBehindService 
{
    @Autowired
    private BaseStayBehindMapper baseStayBehindMapper;

    /**
     * 查询留守人员基本信息
     * 
     * @param id 留守人员基本信息ID
     * @return 留守人员基本信息
     */
    @Override
    public BaseStayBehind selectBaseStayBehindById(String id)
    {
        return baseStayBehindMapper.selectBaseStayBehindById(id);
    }

    /**
     * 查询留守人员基本信息列表
     * 
     * @param baseStayBehind 留守人员基本信息
     * @return 留守人员基本信息
     */
    @Override
    public List<BaseStayBehind> selectBaseStayBehindList(BaseStayBehind baseStayBehind)
    {
        return baseStayBehindMapper.selectBaseStayBehindList(baseStayBehind);
    }

    /**
     * 新增留守人员基本信息
     * 
     * @param baseStayBehind 留守人员基本信息
     * @return 结果
     */
    @Override
    public int insertBaseStayBehind(BaseStayBehind baseStayBehind)
    {
        baseStayBehind.setCreateTime(DateUtils.getNowDate());
        baseStayBehind.setCreateBy(SecurityUtils.getUsername());
        baseStayBehind.setDelFlag("0");
        return baseStayBehindMapper.insertBaseStayBehind(baseStayBehind);
    }

    /**
     * 修改留守人员基本信息
     * 
     * @param baseStayBehind 留守人员基本信息
     * @return 结果
     */
    @Override
    public int updateBaseStayBehind(BaseStayBehind baseStayBehind)
    {
        baseStayBehind.setUpdateBy(SecurityUtils.getUsername());
        baseStayBehind.setUpdateTime(DateUtils.getNowDate());
        return baseStayBehindMapper.updateBaseStayBehind(baseStayBehind);
    }

    /**
     * 批量删除留守人员基本信息
     * 
     * @param ids 需要删除的留守人员基本信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseStayBehindByIds(String[] ids)
    {
        return baseStayBehindMapper.deleteBaseStayBehindByIds(ids);
    }

    /**
     * 批量软删除留守人员基本信息
     *
     * @param ids 需要删除的留守人员基本信息ID
     * @return 结果
     */
    @Override
    public int updateBaseStayBehindByIds(String[] ids)
    {
        return baseStayBehindMapper.updateBaseStayBehindByIds(ids);
    }


    /**
     * 删除留守人员基本信息信息
     * 
     * @param id 留守人员基本信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseStayBehindById(String id)
    {
        return baseStayBehindMapper.deleteBaseStayBehindById(id);
    }


    @Override
    public BaseStayBehind getByPersonId(String personId) {
        return baseStayBehindMapper.getByPersonId(personId);
    }
}
