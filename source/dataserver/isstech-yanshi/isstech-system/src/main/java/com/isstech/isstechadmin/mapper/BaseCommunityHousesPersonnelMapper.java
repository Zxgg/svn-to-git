package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCommunityHousesPersonnel;

/**
 * 社区房屋和人员关联Mapper接口
 * 
 * @author yzp
 * @date 2020-11-30
 */
public interface BaseCommunityHousesPersonnelMapper 
{
    /**
     * 查询UUID
     *
     * @return uuid
     */
    public String selectUuid();

    /**
     * 查询社区房屋和人员关联
     * 
     * @param id 社区房屋和人员关联ID
     * @return 社区房屋和人员关联
     */
    public BaseCommunityHousesPersonnel selectBaseCommunityHousesPersonnelById(String id);

    /**
     * 查询社区房屋和人员关联列表
     * 
     * @param baseCommunityHousesPersonnel 社区房屋和人员关联
     * @return 社区房屋和人员关联集合
     */
    public List<BaseCommunityHousesPersonnel> selectBaseCommunityHousesPersonnelList(BaseCommunityHousesPersonnel baseCommunityHousesPersonnel);

    /**
     * 新增社区房屋和人员关联
     * 
     * @param baseCommunityHousesPersonnel 社区房屋和人员关联
     * @return 结果
     */
    public int insertBaseCommunityHousesPersonnel(BaseCommunityHousesPersonnel baseCommunityHousesPersonnel);

    /**
     * 修改社区房屋和人员关联
     * 
     * @param baseCommunityHousesPersonnel 社区房屋和人员关联
     * @return 结果
     */
    public int updateBaseCommunityHousesPersonnel(BaseCommunityHousesPersonnel baseCommunityHousesPersonnel);

    /**
     * 删除社区房屋和人员关联
     * 
     * @param id 社区房屋和人员关联ID
     * @return 结果
     */
    public int deleteBaseCommunityHousesPersonnelById(String id);

    /**
     * 批量删除社区房屋和人员关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseCommunityHousesPersonnelByIds(String[] ids);

    /**
     * 批量软删除社区房屋和人员关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseCommunityHousesPersonnelByIds(String[] ids);

    public BaseCommunityHousesPersonnel queryByHousesIdAndPerId(BaseCommunityHousesPersonnel baseCommunityHousesPersonnel);
}
