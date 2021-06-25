package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseCommunityChildUserMapper;
import com.isstech.isstechadmin.domain.BaseCommunityChildUser;
import com.isstech.isstechadmin.service.IBaseCommunityChildUserService;

/**
 * 小区用户 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Service
public class BaseCommunityChildUserServiceImpl implements IBaseCommunityChildUserService 
{
    @Autowired
    private BaseCommunityChildUserMapper baseCommunityChildUserMapper;

    /**
     * 查询小区用户 
     * 
     * @param id 小区用户 ID
     * @return 小区用户 
     */
    @Override
    public BaseCommunityChildUser selectBaseCommunityChildUserById(String id)
    {
        return baseCommunityChildUserMapper.selectBaseCommunityChildUserById(id);
    }

    /**
     * 查询小区用户 列表
     * 
     * @param baseCommunityChildUser 小区用户 
     * @return 小区用户 
     */
    @Override
    public List<BaseCommunityChildUser> selectBaseCommunityChildUserList(BaseCommunityChildUser baseCommunityChildUser)
    {
        return baseCommunityChildUserMapper.selectBaseCommunityChildUserList(baseCommunityChildUser);
    }

    /**
     * 新增小区用户 
     * 
     * @param baseCommunityChildUser 小区用户 
     * @return 结果
     */
    @Override
    public int insertBaseCommunityChildUser(BaseCommunityChildUser baseCommunityChildUser)
    {
        baseCommunityChildUser.setId(baseCommunityChildUserMapper.selectUuid());
        baseCommunityChildUser.setCreateBy("admin");
        baseCommunityChildUser.setAuthentication("1");
        baseCommunityChildUser.setCreateTime(DateUtils.getNowDate());
        baseCommunityChildUser.setDelFlag("0");
        return baseCommunityChildUserMapper.insertBaseCommunityChildUser(baseCommunityChildUser);
    }

    /**
     * 修改小区用户 
     * 
     * @param baseCommunityChildUser 小区用户 
     * @return 结果
     */
    @Override
    public int updateBaseCommunityChildUser(BaseCommunityChildUser baseCommunityChildUser)
    {
        baseCommunityChildUser.setUpdateBy(SecurityUtils.getUsername());
        baseCommunityChildUser.setUpdateTime(DateUtils.getNowDate());
        return baseCommunityChildUserMapper.updateBaseCommunityChildUser(baseCommunityChildUser);
    }

    /**
     * 批量删除小区用户 
     * 
     * @param ids 需要删除的小区用户 ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityChildUserByIds(String[] ids)
    {
        return baseCommunityChildUserMapper.deleteBaseCommunityChildUserByIds(ids);
    }

    /**
     * 批量软删除小区用户 
     *
     * @param ids 需要删除的小区用户 ID
     * @return 结果
     */
    @Override
    public int updateBaseCommunityChildUserByIds(String[] ids)
    {
        return baseCommunityChildUserMapper.updateBaseCommunityChildUserByIds(ids);
    }

    /**
     * 删除小区用户 信息
     * 
     * @param id 小区用户 ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityChildUserById(String id)
    {
        return baseCommunityChildUserMapper.deleteBaseCommunityChildUserById(id);
    }
}
