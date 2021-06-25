package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCommunityBuilding;

/**
 * @Description 社区楼栋Mapper接口
 * @Author yzp
 * @Date 2020/11/18 14:26
 */
public interface BaseCommunityBuildingMapper 
{
    /**
     * 查询UUID
     *
     * @return uuid
     */
    public String selectUuid();

    /**
     * 查询社区楼栋
     * 
     * @param id 社区楼栋ID
     * @return 社区楼栋
     */
    public BaseCommunityBuilding selectBaseCommunityBuildingById(String id);

    /**
     * 查询社区楼栋列表
     * 
     * @param baseCommunityBuilding 社区楼栋
     * @return 社区楼栋集合
     */
    public List<BaseCommunityBuilding> selectBaseCommunityBuildingList(BaseCommunityBuilding baseCommunityBuilding);

    /**
     * 新增社区楼栋
     * 
     * @param baseCommunityBuilding 社区楼栋
     * @return 结果
     */
    public int insertBaseCommunityBuilding(BaseCommunityBuilding baseCommunityBuilding);

    /**
     * 修改社区楼栋
     * 
     * @param baseCommunityBuilding 社区楼栋
     * @return 结果
     */
    public int updateBaseCommunityBuilding(BaseCommunityBuilding baseCommunityBuilding);

    /**
     * 删除社区楼栋
     * 
     * @param id 社区楼栋ID
     * @return 结果
     */
    public int deleteBaseCommunityBuildingById(String id);

    /**
     * 批量删除社区楼栋
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseCommunityBuildingByIds(String[] ids);

    /**
     * 批量软删除社区楼栋
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseCommunityBuildingByIds(String[] ids);
}
