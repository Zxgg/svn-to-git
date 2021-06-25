package com.isstech.isstechadmin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.*;
import com.isstech.isstechadmin.domain.vo.CommunityHousesVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.domain.vo.TreeVo;
import com.isstech.isstechadmin.mapper.BaseCommunityBuildingMapper;
import com.isstech.isstechadmin.mapper.BaseCommunityChildMapper;
import com.isstech.isstechadmin.mapper.BaseCommunityHousesMapper;
import com.isstech.isstechadmin.mapper.BaseCommunityMapper;
import com.isstech.isstechadmin.service.IBaseCommunityService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import com.isstech.isstechadmin.utils.GetSqlUtils;
import com.isstech.isstechadmin.utils.mapper.DataSynchronizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 社区信息Service业务层处理
 * @Author yzp
 * @Date 2020/11/18 14:32
 */
@Service
public class BaseCommunityServiceImpl implements IBaseCommunityService
{
    @Autowired
    private BaseCommunityMapper baseCommunityMapper;
    @Autowired
    private BaseCommunityChildMapper baseCommunityChildMapper;
    @Autowired
    private BaseCommunityBuildingMapper baseCommunityBuildingMapper;
    @Autowired
    private BaseCommunityHousesMapper baseCommunityHousesMapper;
    @Autowired
    private DataAccessUtils dataAccessUtils;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;
    @Autowired
    private GetSqlUtils getSqlUtils;
    @Autowired
    private DataSynchronizationMapper dataSynchronizationMapper;

    /**
     * 查询社区信息
     * 
     * @param id 社区信息ID
     * @return 社区信息
     */
    @Override
    public BaseCommunity selectBaseCommunityById(String id)
    {
        return baseCommunityMapper.selectBaseCommunityById(id);
    }

    /**
     * 查询社区信息列表
     * 
     * @param baseCommunity 社区信息
     * @return 社区信息
     */
    @Override
    public List<BaseCommunity> selectBaseCommunityList(BaseCommunity baseCommunity)
    {
        return baseCommunityMapper.selectBaseCommunityList(baseCommunity);
    }

    /**
     * 查询社区菜单列表
     *
     * @return 社区信息
     */
    @Override
    public List<TreeVo> selectBaseCommunityListAll()
    {
        List<TreeVo> treeVos = new ArrayList<>();
        List<BaseCommunity> baseCommunities = baseCommunityMapper.selectBaseCommunityListAll();
        baseCommunities.stream().forEach( item -> {
            String id = item.getId();
            if(dataAccessUtils.isAllDataAccess()||dataAccessUtils.getCurrUserDataAccessDeptToList().stream().filter(dept -> id.equals(dept.get("communityId"))).count()>0){
                TreeVo treeVo = new TreeVo();
                treeVo.setId(id);
                treeVo.setName(item.getName());
                treeVo.setParentId("");
                treeVo.setType("base_community");
                treeVo.setDeptIds(item.getDeptIds());
                List<BaseCommunityChild> baseCommunityChildren = baseCommunityChildMapper.selectBaseCommunityChildByCommunityId(id);
                List<TreeVo> children = new ArrayList<>();
                baseCommunityChildren.stream().forEach( i -> {
                    if(dataAccessUtils.isAllDataAccess()||dataAccessUtils.getCurrUserDataAccessDeptToList().stream().filter(child -> i.getId().equals(child.get("communityChildId"))).count()>0){
                        TreeVo treeVoChild = new TreeVo();
                        treeVoChild.setId(i.getId());
                        treeVoChild.setName(i.getName());
                        treeVoChild.setParentId(i.getCommunityId());
                        treeVoChild.setType("base_community_child");
                        children.add(treeVoChild);
                        treeVo.setChildren(children);
                    }
                });
                treeVos.add(treeVo);
            }
        });

        return treeVos;
    }

    /**
     * 新增社区信息
     * 
     * @param baseCommunity 社区信息
     * @return 结果
     */
    @Override
    public int insertBaseCommunity(BaseCommunity baseCommunity)
    {
        baseCommunity.setCreateTime(DateUtils.getNowDate());
        baseCommunity.setCreateBy(SecurityUtils.getUsername());
        String uuid = baseCommunityMapper.selectUuid();
        baseCommunity.setId(uuid);
        int row = baseCommunityMapper.insertBaseCommunity(baseCommunity);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(baseCommunity,"insertBaseCommunity"));
        checkExist(baseCommunity);
        return row;
    }


    /**
     * 修改社区信息
     * 
     * @param baseCommunity 社区信息
     * @return 结果
     */
    @Override
    public int updateBaseCommunity(BaseCommunity baseCommunity)
    {
        baseCommunity.setUpdateTime(DateUtils.getNowDate());
        baseCommunity.setUpdateBy(SecurityUtils.getUsername());
        int row = baseCommunityMapper.updateBaseCommunity(baseCommunity);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(baseCommunity,"updateBaseCommunity"));
        checkExist(baseCommunity);
        return row;
    }

    /**
     * 批量删除社区信息
     * 
     * @param ids 需要删除的社区信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityByIds(String[] ids)
    {
        int row = baseCommunityMapper.deleteBaseCommunityByIds(ids);
        baseCommunityMapper.deleteBaseDeptCommunityByIds(ids);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(ids,"deleteBaseDeptCommunityByIds"));
        return row;
    }

    /**
     * 批量软删除社区信息
     *
     * @param ids 需要删除的社区信息ID
     * @return 结果
     */
    @Override
    public int updateBaseCommunityByIds(String[] ids)
    {
        int row = baseCommunityMapper.updateBaseCommunityByIds(ids);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(ids,"updateBaseCommunityByIds"));
        return row;
    }

    /**
     * 删除社区信息信息
     * 
     * @param id 社区信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityById(String id)
    {
        int row = baseCommunityMapper.deleteBaseCommunityById(id);
        baseCommunityMapper.deleteBaseDeptCommunityById(id);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(id,"deleteBaseDeptCommunityById"));
        return row;
    }

    @Override
    public TreeVo selectTreeInfo(QueryVo queryVo) {
        String communityId = queryVo.getCommunityId();
        String communityChildId = queryVo.getCommunityChildId();
        //根据社区id查询出社区，小区，楼栋，住户
        //社区
        BaseCommunity baseCommunity = baseCommunityMapper.selectBaseCommunityById(communityId);
        //小区
        List<BaseCommunityChild> childrenList = new ArrayList<>();


        if(StringUtils.isNotEmpty(communityChildId)){
            BaseCommunityChild communityChild = baseCommunityChildMapper.selectBaseCommunityChildById(communityChildId);
            childrenList.add(communityChild);
        }else {
            BaseCommunityChild communityChild = new BaseCommunityChild();
            communityChild.setCommunityId(communityId);
            communityChild.setDelFlag("0");
            childrenList = baseCommunityChildMapper.selectBaseCommunityChildList(communityChild);
        }
        //楼栋
        BaseCommunityBuilding building = new BaseCommunityBuilding();
        building.setCommunityId(communityId);
        building.setCommunityChildId(communityChildId);
        building.setDelFlag("0");
        List<BaseCommunityBuilding> buildingList = baseCommunityBuildingMapper.selectBaseCommunityBuildingList(building);
        //住户
        CommunityHousesVo houses = new CommunityHousesVo();
        houses.setCommunityId(communityId);
        houses.setCommunityChildId(communityChildId);
        houses.setDelFlag("0");
        List<BaseCommunityHouses> housesList = baseCommunityHousesMapper.selectBaseCommunityHousesList(houses);

        //整合社区->小区数据
        TreeVo community = new TreeVo();
        community.setId(communityId);
        community.setName(baseCommunity.getName());
        community.setType("base_community");
        List<TreeVo> childTree = new ArrayList<>();
        community.setChildren(childTree);
        childrenList.forEach(child -> {
            TreeVo childVo = new TreeVo();
            childVo.setName(child.getName());
            childVo.setParentId(communityId);
            childVo.setId(child.getId());
            childVo.setType("base_community_child");
            childTree.add(childVo);
        });
        community.setChildren(childTree);

        //整合小区->楼栋数据
        childTree.forEach(treeVo -> {
            List<TreeVo> buildTree = new ArrayList<>();
            String voId = treeVo.getId();
            buildingList.forEach(item ->{
                String childId = item.getCommunityChildId();
                if(StringUtils.equals(voId,childId)){
                    TreeVo buildVo = new TreeVo();
                    buildVo.setId(item.getId());
                    buildVo.setParentId(voId);
                    buildVo.setType("base_community_building");
                    buildVo.setName(item.getName());
                    buildTree.add(buildVo);
                }
            });
            treeVo.setChildren(buildTree);
        });

        //整合楼栋->住户数据
        childTree.forEach(treeVo -> {
            List<TreeVo> childList = treeVo.getChildren();
            childList.forEach(child ->{
                List<TreeVo> buildList = child.getChildren();
                buildList.forEach(build ->{
                    String buildId = build.getId();
                    List<TreeVo> houseTree = new ArrayList<>();
                    housesList.forEach(item ->{
                       String serialNumber = item.getSerialNumber();
                        if(StringUtils.equals(buildId,serialNumber)){
                            TreeVo houseVo = new TreeVo();
                            houseVo.setId(item.getId());
                            houseVo.setParentId(buildId);
                            houseVo.setType("base_community_houses");
                            houseVo.setName(item.getName());
                            houseTree.add(houseVo);
                        }
                   });
                    build.setChildren(houseTree);
                });
            });
        });
        return community;
    }

    private void checkExist(BaseCommunity baseCommunity){
        baseCommunityMapper.deleteBaseDeptCommunityById(baseCommunity.getId());
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(baseCommunity.getId(),"deleteBaseDeptCommunityById"));
        String deptIds = baseCommunity.getDeptIds();
        if(StringUtils.isEmpty(deptIds)){
            return;
        }
        String[] deptIdsArr = deptIds.split(",");
        for (String deptId : deptIdsArr) {
            BaseDeptCommunity baseDeptCommunity = new BaseDeptCommunity();
            baseDeptCommunity.setCommunityId(baseCommunity.getId());
            baseDeptCommunity.setDeptId(deptId);
            baseCommunityMapper.insertBaseDeptCommunity(baseDeptCommunity);
            dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(baseDeptCommunity,"insertBaseDeptCommunity"));
        }
//        baseCommunityMapper.insertBaseDeptCommunity(baseCommunity.getId(),deptIdsArr);
    }

}
