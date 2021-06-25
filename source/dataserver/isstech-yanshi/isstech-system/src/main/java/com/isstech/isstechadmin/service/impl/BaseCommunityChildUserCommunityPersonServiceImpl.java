package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseCommunityChildUserCommunityPersonMapper;
import com.isstech.isstechadmin.domain.BaseCommunityChildUserCommunityPerson;
import com.isstech.isstechadmin.service.IBaseCommunityChildUserCommunityPersonService;

/**
 * 小区用户和小区人员房屋关联 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Service
public class BaseCommunityChildUserCommunityPersonServiceImpl implements IBaseCommunityChildUserCommunityPersonService 
{
    @Autowired
    private BaseCommunityChildUserCommunityPersonMapper baseCommunityChildUserCommunityPersonMapper;

    /**
     * 查询小区用户和小区人员房屋关联 
     * 
     * @param id 小区用户和小区人员房屋关联 ID
     * @return 小区用户和小区人员房屋关联 
     */
    @Override
    public BaseCommunityChildUserCommunityPerson selectBaseCommunityChildUserCommunityPersonById(String id)
    {
        return baseCommunityChildUserCommunityPersonMapper.selectBaseCommunityChildUserCommunityPersonById(id);
    }

    /**
     * 查询小区用户和小区人员房屋关联 列表
     * 
     * @param baseCommunityChildUserCommunityPerson 小区用户和小区人员房屋关联 
     * @return 小区用户和小区人员房屋关联 
     */
    @Override
    public List<BaseCommunityChildUserCommunityPerson> selectBaseCommunityChildUserCommunityPersonList(BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson)
    {
        return baseCommunityChildUserCommunityPersonMapper.selectBaseCommunityChildUserCommunityPersonList(baseCommunityChildUserCommunityPerson);
    }

    /**
     * 新增小区用户和小区人员房屋关联 
     * 
     * @param baseCommunityChildUserCommunityPerson 小区用户和小区人员房屋关联 
     * @return 结果
     */
    @Override
    public int insertBaseCommunityChildUserCommunityPerson(BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson)
    {
        baseCommunityChildUserCommunityPerson.setId(baseCommunityChildUserCommunityPersonMapper.selectUuid());
        baseCommunityChildUserCommunityPerson.setCreateBy(SecurityUtils.getUsername());
        baseCommunityChildUserCommunityPerson.setCreateTime(DateUtils.getNowDate());
        baseCommunityChildUserCommunityPerson.setDelFlag("0");
        return baseCommunityChildUserCommunityPersonMapper.insertBaseCommunityChildUserCommunityPerson(baseCommunityChildUserCommunityPerson);
    }

    /**
     * 修改小区用户和小区人员房屋关联 
     * 
     * @param baseCommunityChildUserCommunityPerson 小区用户和小区人员房屋关联 
     * @return 结果
     */
    @Override
    public int updateBaseCommunityChildUserCommunityPerson(BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson)
    {
        baseCommunityChildUserCommunityPerson.setUpdateBy(SecurityUtils.getUsername());
        baseCommunityChildUserCommunityPerson.setUpdateTime(DateUtils.getNowDate());
        return baseCommunityChildUserCommunityPersonMapper.updateBaseCommunityChildUserCommunityPerson(baseCommunityChildUserCommunityPerson);
    }

    /**
     * 批量删除小区用户和小区人员房屋关联 
     * 
     * @param ids 需要删除的小区用户和小区人员房屋关联 ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityChildUserCommunityPersonByIds(String[] ids)
    {
        return baseCommunityChildUserCommunityPersonMapper.deleteBaseCommunityChildUserCommunityPersonByIds(ids);
    }

    /**
     * 批量软删除小区用户和小区人员房屋关联 
     *
     * @param ids 需要删除的小区用户和小区人员房屋关联 ID
     * @return 结果
     */
    @Override
    public int updateBaseCommunityChildUserCommunityPersonByIds(String[] ids)
    {
        return baseCommunityChildUserCommunityPersonMapper.updateBaseCommunityChildUserCommunityPersonByIds(ids);
    }

    /**
     * 删除小区用户和小区人员房屋关联 信息
     * 
     * @param id 小区用户和小区人员房屋关联 ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityChildUserCommunityPersonById(String id)
    {
        return baseCommunityChildUserCommunityPersonMapper.deleteBaseCommunityChildUserCommunityPersonById(id);
    }
}
