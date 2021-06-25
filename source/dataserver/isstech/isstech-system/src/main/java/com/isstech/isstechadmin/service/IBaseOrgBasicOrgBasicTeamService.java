package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseOrgBasicOrgBasicTeam;

/**
 * 综治机构和综治队伍关联Service接口
 * 
 * @author yzp
 * @date 2021-01-29
 */
public interface IBaseOrgBasicOrgBasicTeamService 
{
    /**
     * 查询综治机构和综治队伍关联
     * 
     * @param id 综治机构和综治队伍关联ID
     * @return 综治机构和综治队伍关联
     */
    public BaseOrgBasicOrgBasicTeam selectBaseOrgBasicOrgBasicTeamById(String id);

    /**
     * 查询综治机构和综治队伍关联列表
     * 
     * @param baseOrgBasicOrgBasicTeam 综治机构和综治队伍关联
     * @return 综治机构和综治队伍关联集合
     */
    public List<BaseOrgBasicOrgBasicTeam> selectBaseOrgBasicOrgBasicTeamList(BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam);

    /**
     * 新增综治机构和综治队伍关联
     * 
     * @param baseOrgBasicOrgBasicTeam 综治机构和综治队伍关联
     * @return 结果
     */
    public int insertBaseOrgBasicOrgBasicTeam(BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam);

    /**
     * 修改综治机构和综治队伍关联
     * 
     * @param baseOrgBasicOrgBasicTeam 综治机构和综治队伍关联
     * @return 结果
     */
    public int updateBaseOrgBasicOrgBasicTeam(BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam);

    /**
     * 批量删除综治机构和综治队伍关联
     * 
     * @param ids 需要删除的综治机构和综治队伍关联ID
     * @return 结果
     */
    public int deleteBaseOrgBasicOrgBasicTeamByIds(String[] ids);

    /**
     * 删除综治机构和综治队伍关联信息
     * 
     * @param id 综治机构和综治队伍关联ID
     * @return 结果
     */
    public int deleteBaseOrgBasicOrgBasicTeamById(String id);

    /**
     * 批量软删除综治机构和综治队伍关联
     *
     * @param ids 需要删除的综治机构和综治队伍关联ID
     * @return 结果
     */
    public int updateBaseOrgBasicOrgBasicTeamByIds(String[] ids);
}
