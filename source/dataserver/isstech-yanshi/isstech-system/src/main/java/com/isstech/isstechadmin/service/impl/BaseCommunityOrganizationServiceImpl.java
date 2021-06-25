package com.isstech.isstechadmin.service.impl;

import java.util.Arrays;
import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseCommunityOrganizationMapper;
import com.isstech.isstechadmin.domain.BaseCommunityOrganization;
import com.isstech.isstechadmin.service.IBaseCommunityOrganizationService;

/**
 * 社区与组织关联Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-29
 */
@Service
public class BaseCommunityOrganizationServiceImpl implements IBaseCommunityOrganizationService 
{
    @Autowired
    private BaseCommunityOrganizationMapper baseCommunityOrganizationMapper;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询社区与组织关联
     * 
     * @param id 社区与组织关联ID
     * @return 社区与组织关联
     */
    @Override
    public BaseCommunityOrganization selectBaseCommunityOrganizationById(String id)
    {
        return baseCommunityOrganizationMapper.selectBaseCommunityOrganizationById(id);
    }

    /**
     * 查询社区与组织关联列表
     * 
     * @param baseCommunityOrganization 社区与组织关联
     * @return 社区与组织关联
     */
    @Override
    public List<BaseCommunityOrganization> selectBaseCommunityOrganizationList(BaseCommunityOrganization baseCommunityOrganization)
    {
        baseCommunityOrganization.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseCommunityOrganization.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return baseCommunityOrganizationMapper.selectBaseCommunityOrganizationList(baseCommunityOrganization);
    }

    /**
     * 新增社区与组织关联
     * 
     * @param baseCommunityOrganization 社区与组织关联
     * @return 结果
     */
    @Override
    public int insertBaseCommunityOrganization(BaseCommunityOrganization baseCommunityOrganization)
    {
        baseCommunityOrganization.setCreateBy(SecurityUtils.getUsername());
        baseCommunityOrganization.setCreateTime(DateUtils.getNowDate());
        baseCommunityOrganization.setDelFlag("0");
        return baseCommunityOrganizationMapper.insertBaseCommunityOrganization(baseCommunityOrganization);
    }

    /**
     * 修改社区与组织关联
     * 
     * @param baseCommunityOrganization 社区与组织关联
     * @return 结果
     */
    @Override
    public int updateBaseCommunityOrganization(BaseCommunityOrganization baseCommunityOrganization)
    {
        baseCommunityOrganization.setUpdateBy(SecurityUtils.getUsername());
        baseCommunityOrganization.setUpdateTime(DateUtils.getNowDate());
        return baseCommunityOrganizationMapper.updateBaseCommunityOrganization(baseCommunityOrganization);
    }

    /**
     * 批量删除社区与组织关联
     * 
     * @param ids 需要删除的社区与组织关联ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityOrganizationByIds(String[] ids)
    {
        return baseCommunityOrganizationMapper.deleteBaseCommunityOrganizationByIds(ids);
    }

    /**
     * 批量软删除社区与组织关联
     *
     * @param ids 需要删除的社区与组织关联ID
     * @return 结果
     */
    @Override
    public int updateBaseCommunityOrganizationByIds(String[] ids)
    {
        return baseCommunityOrganizationMapper.updateBaseCommunityOrganizationByIds(ids);
    }

    /**
     * 删除社区与组织关联信息
     * 
     * @param id 社区与组织关联ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityOrganizationById(String id)
    {
        return baseCommunityOrganizationMapper.deleteBaseCommunityOrganizationById(id);
    }

    /**
     * 新增社区与组织关联,并筛选是否有已经存在的关联关系
     *
     * @param vo 社区与组织关联
     * @param type 组织机构类型
     * @return 结果
     */
    @Override
    public int saveBaseCommunityOrganization(QueryVo vo,String orgId, String type) {
        //根据社区id和小区id查询是否有已存在的绑定组织
        BaseCommunityOrganization queryBaseCommunityOrganization = new BaseCommunityOrganization();
//        queryBaseCommunityOrganization.setCommunityId(vo.getCommunityId());
//        queryBaseCommunityOrganization.setCommunityChildId(vo.getCommunityChildId());
        queryBaseCommunityOrganization.setSocialOrganizationId(orgId);
        queryBaseCommunityOrganization.setType(type);
        List<BaseCommunityOrganization> baseCommunityOrganizations = this.selectBaseCommunityOrganizationList(queryBaseCommunityOrganization);
        //当存在绑定组织时，判断社区房屋id是否一致
        if (baseCommunityOrganizations!=null&&baseCommunityOrganizations.size()>0){
            BaseCommunityOrganization baseCommunityOrganization = baseCommunityOrganizations.get(0);
            if(checkHousesId(baseCommunityOrganization.getCommunityHousesId(),vo.getCommunityHousesId())){
                return 0;
            }
            //社区房屋id不一致，则修改之前关联关系del_flag为1
            baseCommunityOrganization.setDelFlag("1");
            this.updateBaseCommunityOrganization(baseCommunityOrganization);
        }
        //将新关联关系插入关联关系表
        BaseCommunityOrganization baseCommunityOrganization1 = new BaseCommunityOrganization();
        baseCommunityOrganization1.setCommunityId(vo.getCommunityId());
        baseCommunityOrganization1.setCommunityChildId(vo.getCommunityChildId());
        baseCommunityOrganization1.setCommunityHousesId(vo.getCommunityHousesId());
        baseCommunityOrganization1.setType(type);
        baseCommunityOrganization1.setSocialOrganizationId(orgId);
        return this.insertBaseCommunityOrganization(baseCommunityOrganization1);
    }

    /**
     * 判断社区房屋id是否一致
     *
     * @param beforeHousesId 现存社区房屋ids
     * @param afterHousesId 新保存社区房屋ids
     * @return 结果
     */
    public boolean checkHousesId(String beforeHousesId,String afterHousesId){
        if(StringUtils.isEmpty(beforeHousesId)||StringUtils.isEmpty(afterHousesId)){
           return false;
        }
        String[] beforeArr = beforeHousesId.split(",");
        String[] afterArr = afterHousesId.split(",");
        Arrays.sort(beforeArr);
        Arrays.sort(afterArr);
        if (!Arrays.equals(beforeArr, afterArr)){
            return false;
        }
        return true;
    }

}
