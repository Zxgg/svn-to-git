package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.BaseSocialOrganization;
import com.isstech.isstechadmin.domain.vo.CommunitySocialOrganizationVo;

import java.util.List;

/**
 * 社会组织信息Mapper接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface BaseSocialOrganizationMapper 
{
    /**
     * 查询社会组织信息
     * 
     * @param id 社会组织信息ID
     * @return 社会组织信息
     */
    public CommunitySocialOrganizationVo selectBaseSocialOrganizationById(String id);

    /**
     * 查询社会组织信息列表
     * 
     * @param baseSocialOrganization 社会组织信息
     * @return 社会组织信息集合
     */
    public List<CommunitySocialOrganizationVo> selectBaseSocialOrganizationList(CommunitySocialOrganizationVo baseSocialOrganization);

    /**
     * 新增社会组织信息
     * 
     * @param baseSocialOrganization 社会组织信息
     * @return 结果
     */
    public int insertBaseSocialOrganization(BaseSocialOrganization baseSocialOrganization);

    /**
     * 修改社会组织信息
     * 
     * @param baseSocialOrganization 社会组织信息
     * @return 结果
     */
    public int updateBaseSocialOrganization(BaseSocialOrganization baseSocialOrganization);

    /**
     * 删除社会组织信息
     * 
     * @param id 社会组织信息ID
     * @return 结果
     */
    public int deleteBaseSocialOrganizationById(String id);

    /**
     * 批量删除社会组织信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseSocialOrganizationByIds(String[] ids);
    /**
     * 批量软删除社会组织信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseSocialOrganizationByIds(String[] ids);
}
