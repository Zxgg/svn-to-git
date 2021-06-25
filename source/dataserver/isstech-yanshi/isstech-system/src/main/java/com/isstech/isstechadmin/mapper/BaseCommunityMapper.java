package com.isstech.isstechadmin.mapper;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.domain.BaseCommunity;
import com.isstech.isstechadmin.domain.BaseDeptCommunity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description 社区信息Mapper接口
 * @Author yzp
 * @Date 2020/11/18 14:27
 */
public interface BaseCommunityMapper
{
    /**
     * 查询社区信息
     *
     * @param id 社区信息ID
     * @return 社区信息
     */
    public BaseCommunity selectBaseCommunityById(String id);

    /**
     * 查询社区信息列表
     *
     * @param baseCommunity 社区信息
     * @return 社区信息集合
     */
    public List<BaseCommunity> selectBaseCommunityList(BaseCommunity baseCommunity);

    /**
     * 查询社区信息列表
     * @return 社区信息集合
     */
    @Select("select bc.id, bc.name,(select group_concat(cast(d.dept_ip as char) separator ',')from base_dept_community d  where d.community_id = id) as deptIds from  base_community bc where bc.del_flag='0'")
    public List<BaseCommunity> selectBaseCommunityListAll();

    /**
     * 新增社区信息
     *
     * @param baseCommunity 社区信息
     * @return 结果
     */
    public int insertBaseCommunity(BaseCommunity baseCommunity);

    /**
     * 修改社区信息
     *
     * @param baseCommunity 社区信息
     * @return 结果
     */
    public int updateBaseCommunity(BaseCommunity baseCommunity);

    /**
     * 删除社区信息
     *
     * @param id 社区信息ID
     * @return 结果
     */
    public int deleteBaseCommunityById(String id);

    /**
     * 批量删除社区信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseCommunityByIds(String[] ids);

    /**
     * 批量软删除社区信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseCommunityByIds(String[] ids);

    public String selectUuid();

    /**
     * 新增社区与部门关联关系
     *
     * @param communityId 社区id
     * @param deptIdsArr 部门组织id
     * @return 结果
     */
    public void insertBaseDeptsCommunity(@Param("communityId") String communityId, @Param("deptIdsArr") String[] deptIdsArr);

    /**
     * 新增社区与部门关联关系
     *
     * @return 结果
     */
    public void insertBaseDeptCommunity(BaseDeptCommunity baseDeptCommunity);

    /**
     * 删除社区与部门关联关系
     *
     * @param id 社区id
     * @return 结果
     */
    public void deleteBaseDeptCommunityById(String id);

    /**
     * 批量删除社区与部门关联关系
     *
     * @param ids 社区id
     * @return 结果
     */
    public void deleteBaseDeptCommunityByIds(String[] ids);
}
