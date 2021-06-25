package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCommunityChildUserCommunityPerson;

/**
 * 小区用户和小区人员房屋关联 Service接口
 * 
 * @author yzp
 * @date 2021-02-19
 */
public interface IBaseCommunityChildUserCommunityPersonService 
{
    /**
     * 查询小区用户和小区人员房屋关联 
     * 
     * @param id 小区用户和小区人员房屋关联 ID
     * @return 小区用户和小区人员房屋关联 
     */
    public BaseCommunityChildUserCommunityPerson selectBaseCommunityChildUserCommunityPersonById(String id);

    /**
     * 查询小区用户和小区人员房屋关联 列表
     * 
     * @param baseCommunityChildUserCommunityPerson 小区用户和小区人员房屋关联 
     * @return 小区用户和小区人员房屋关联 集合
     */
    public List<BaseCommunityChildUserCommunityPerson> selectBaseCommunityChildUserCommunityPersonList(BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson);

    /**
     * 新增小区用户和小区人员房屋关联 
     * 
     * @param baseCommunityChildUserCommunityPerson 小区用户和小区人员房屋关联 
     * @return 结果
     */
    public int insertBaseCommunityChildUserCommunityPerson(BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson);

    /**
     * 修改小区用户和小区人员房屋关联 
     * 
     * @param baseCommunityChildUserCommunityPerson 小区用户和小区人员房屋关联 
     * @return 结果
     */
    public int updateBaseCommunityChildUserCommunityPerson(BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson);

    /**
     * 批量删除小区用户和小区人员房屋关联 
     * 
     * @param ids 需要删除的小区用户和小区人员房屋关联 ID
     * @return 结果
     */
    public int deleteBaseCommunityChildUserCommunityPersonByIds(String[] ids);

    /**
     * 删除小区用户和小区人员房屋关联 信息
     * 
     * @param id 小区用户和小区人员房屋关联 ID
     * @return 结果
     */
    public int deleteBaseCommunityChildUserCommunityPersonById(String id);

    /**
     * 批量软删除小区用户和小区人员房屋关联 
     *
     * @param ids 需要删除的小区用户和小区人员房屋关联 ID
     * @return 结果
     */
    public int updateBaseCommunityChildUserCommunityPersonByIds(String[] ids);
}
