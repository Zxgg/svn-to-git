package com.isstech.isstechadmin.mapper;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.domain.BaseCommunityHouses;
import com.isstech.isstechadmin.domain.query.QueryCommunityHouses;
import com.isstech.isstechadmin.domain.vo.CommunityHousesVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 社区房屋信息Mapper接口
 * @Author yzp
 * @Date 2020/11/18 14:30
 */
public interface BaseCommunityHousesMapper 
{
    /**
     * 查询社区房屋信息
     * 
     * @param id 社区房屋信息ID
     * @return 社区房屋信息
     */
    public BaseCommunityHouses selectBaseCommunityHousesById(String id);

    /**
     * 查询社区房屋信息列表
     * 
     * @param communityHousesVo 社区房屋信息
     * @return 社区房屋信息集合
     */
    public List<BaseCommunityHouses> selectBaseCommunityHousesList(CommunityHousesVo communityHousesVo);

    /**
     * 根据（人员ID、是否历史-delFlag）查询所有关联的房屋
     * @param communityHousesVo 社区房屋补充信息
     * @return 社区房屋信息集合
     */
    public List<CommunityHousesVo> queryCommunityHousesVoList(CommunityHousesVo communityHousesVo);

    /**
     * 新增社区房屋信息
     * 
     * @param baseCommunityHouses 社区房屋信息
     * @return 结果
     */
    public int insertBaseCommunityHouses(BaseCommunityHouses baseCommunityHouses);

    /**
     * 修改社区房屋信息
     * 
     * @param baseCommunityHouses 社区房屋信息
     * @return 结果
     */
    public int updateBaseCommunityHouses(BaseCommunityHouses baseCommunityHouses);

    /**
     * 删除社区房屋信息
     * 
     * @param id 社区房屋信息ID
     * @return 结果
     */
    public int deleteBaseCommunityHousesById(String id);

    /**
     * 批量删除社区房屋信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseCommunityHousesByIds(String[] ids);
    /**
     * 批量软删除社区房屋信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseCommunityHousesByIds(String[] ids);

    public String selectUuid();

    public List<BaseCommunityHouses> selectByIdList(@Param("list") List<String> idList);

    public List<Map<String,Object>> queryHouseAndPersonList(QueryCommunityHouses queryCommunityHouses);
}
