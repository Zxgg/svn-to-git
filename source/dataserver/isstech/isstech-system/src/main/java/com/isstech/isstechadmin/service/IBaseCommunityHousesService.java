package com.isstech.isstechadmin.service;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.domain.BaseCommunityHouses;
import com.isstech.isstechadmin.domain.BaseVehicleInformation;
import com.isstech.isstechadmin.domain.query.QueryCommunityHouses;
import com.isstech.isstechadmin.domain.vo.CommunityHousesVo;
import com.isstech.isstechadmin.domain.vo.VehicleInformationVo;

/**
 * @Description 社区房屋信息Service接口
 * @Author yzp
 * @Date 2020/11/18 14:31
 */
public interface IBaseCommunityHousesService 
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
     *
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
    public String insertBaseCommunityHouses(BaseCommunityHouses baseCommunityHouses);

    /**
     * 修改社区房屋信息
     * 
     * @param baseCommunityHouses 社区房屋信息
     * @return 结果
     */
    public int updateBaseCommunityHouses(BaseCommunityHouses baseCommunityHouses);

    /**
     * 批量删除社区房屋信息
     * 
     * @param ids 需要删除的社区房屋信息ID
     * @return 结果
     */
    public int deleteBaseCommunityHousesByIds(String[] ids);
    /**
     * 批量软删除社区房屋信息
     *
     * @param ids 需要删除的社区房屋信息ID
     * @return 结果
     */
    public int updateBaseCommunityHousesByIds(String[] ids);

    /**
     * 删除社区房屋信息信息
     * 
     * @param id 社区房屋信息ID
     * @return 结果
     */
    public int deleteBaseCommunityHousesById(String id);

    /**
     * 根据车辆信息查询所关联的房屋信息
     * @param information
     * @return 房屋信息
     * @author qishuwei
     * @date 2020-12-22
     */
    public List<BaseCommunityHouses> getByVehicleInfo(VehicleInformationVo information);

    /**
     * 根据（房主姓名、房间号）查询房屋信息列表：
     *  返回字段包括：户主基本信息、房屋所有信息
     */
    public List<Map<String,Object>> queryHouseAndPersonList(QueryCommunityHouses queryCommunityHouses);
}
