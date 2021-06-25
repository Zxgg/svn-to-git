package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCommunityChildUser;

/**
 * 小区用户 Mapper接口
 * 
 * @author yzp
 * @date 2021-02-19
 */
public interface BaseCommunityChildUserMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询小区用户 
     * 
     * @param id 小区用户 ID
     * @return 小区用户 
     */
    public BaseCommunityChildUser selectBaseCommunityChildUserById(String id);

    /**
     * 查询小区用户 列表
     * 
     * @param baseCommunityChildUser 小区用户 
     * @return 小区用户 集合
     */
    public List<BaseCommunityChildUser> selectBaseCommunityChildUserList(BaseCommunityChildUser baseCommunityChildUser);

    /**
     * 新增小区用户 
     * 
     * @param baseCommunityChildUser 小区用户 
     * @return 结果
     */
    public int insertBaseCommunityChildUser(BaseCommunityChildUser baseCommunityChildUser);

    /**
     * 修改小区用户 
     * 
     * @param baseCommunityChildUser 小区用户 
     * @return 结果
     */
    public int updateBaseCommunityChildUser(BaseCommunityChildUser baseCommunityChildUser);

    /**
     * 删除小区用户 
     * 
     * @param id 小区用户 ID
     * @return 结果
     */
    public int deleteBaseCommunityChildUserById(String id);

    /**
     * 批量删除小区用户 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseCommunityChildUserByIds(String[] ids);

    /**
     * 批量软删除小区用户 
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseCommunityChildUserByIds(String[] ids);
}
