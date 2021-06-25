package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCommunityChild;
import com.isstech.isstechadmin.domain.BaseDeptCommunityChild;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 小区信息  Mapper接口
 * 
 * @author yzp
 * @date 2020-11-26
 */
public interface BaseCommunityChildMapper 
{
    /**
     * 查询小区信息  
     * 
     * @param id 小区信息ID
     * @return 小区信息  
     */
    public BaseCommunityChild selectBaseCommunityChildById(String id);

    /**
     * 查询小区信息
     *
     * @param communityId 社区ID
     * @return 小区信息
     */
    @Select("select bcc.id, bcc.community_id communityId, bcc.name,(select d.dept_ip from base_dept_community_child d where d.community_child_id = bcc.id) as deptId from  base_community_child bcc where bcc.del_flag='0' and bcc.community_id = #{communityId} order by create_time ")
    public List<BaseCommunityChild> selectBaseCommunityChildByCommunityId(String communityId);

    /**
     * 查询小区信息  列表
     * 
     * @param baseCommunityChild 小区信息  
     * @return 小区信息  集合
     */
    public List<BaseCommunityChild> selectBaseCommunityChildList(BaseCommunityChild baseCommunityChild);

    /**
     * 新增小区信息  
     * 
     * @param baseCommunityChild 小区信息  
     * @return 结果
     */
    public int insertBaseCommunityChild(BaseCommunityChild baseCommunityChild);

    /**
     * 修改小区信息  
     * 
     * @param baseCommunityChild 小区信息  
     * @return 结果
     */
    public int updateBaseCommunityChild(BaseCommunityChild baseCommunityChild);

    /**
     * 删除小区信息  
     * 
     * @param id 小区信息  ID
     * @return 结果
     */
    public int deleteBaseCommunityChildById(String id);

    /**
     * 批量删除小区信息  
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseCommunityChildByIds(String[] ids);

    /**
     * 批量软删除小区信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseCommunityChildByIds(String[] ids);

    public String selectUuid();

    /**
     * 删除小区与部门关联关系
     *
     * @param id 小区id
     * @return 结果
     */
    public void deleteBaseDeptCommunityChildById(String id);

    /**
     * 新增小区与部门关联关系
     *
     * @param id 小区id
     * @param communityId 社区id
     * @param deptId 部门组织id
     * @return 结果
     */
    public void insertBaseDeptCommunityChilds(@Param("id") String id, @Param("communityId")String communityId,@Param("deptId") String deptId);

    /**
     * 新增小区与部门关联关系
     *
     * @return 结果
     */
    public void insertBaseDeptCommunityChild(BaseDeptCommunityChild baseDeptCommunityChild);

    /**
     * 批量删除小区与部门关联关系
     *
     * @param ids 小区id
     * @return 结果
     */
    public void deleteBaseDeptCommunityChildByIds(String[] ids);
}
