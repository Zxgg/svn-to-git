package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseOrgPublicOrgPublicTeam;

/**
 * 群防群治组织和队伍关联Service接口
 * 
 * @author yzp
 * @date 2021-01-29
 */
public interface IBaseOrgPublicOrgPublicTeamService 
{
    /**
     * 查询群防群治组织和队伍关联
     * 
     * @param id 群防群治组织和队伍关联ID
     * @return 群防群治组织和队伍关联
     */
    public BaseOrgPublicOrgPublicTeam selectBaseOrgPublicOrgPublicTeamById(String id);

    /**
     * 查询群防群治组织和队伍关联列表
     * 
     * @param baseOrgPublicOrgPublicTeam 群防群治组织和队伍关联
     * @return 群防群治组织和队伍关联集合
     */
    public List<BaseOrgPublicOrgPublicTeam> selectBaseOrgPublicOrgPublicTeamList(BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam);

    /**
     * 新增群防群治组织和队伍关联
     * 
     * @param baseOrgPublicOrgPublicTeam 群防群治组织和队伍关联
     * @return 结果
     */
    public int insertBaseOrgPublicOrgPublicTeam(BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam);

    /**
     * 修改群防群治组织和队伍关联
     * 
     * @param baseOrgPublicOrgPublicTeam 群防群治组织和队伍关联
     * @return 结果
     */
    public int updateBaseOrgPublicOrgPublicTeam(BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam);

    /**
     * 批量删除群防群治组织和队伍关联
     * 
     * @param ids 需要删除的群防群治组织和队伍关联ID
     * @return 结果
     */
    public int deleteBaseOrgPublicOrgPublicTeamByIds(String[] ids);

    /**
     * 删除群防群治组织和队伍关联信息
     * 
     * @param id 群防群治组织和队伍关联ID
     * @return 结果
     */
    public int deleteBaseOrgPublicOrgPublicTeamById(String id);

    /**
     * 批量软删除群防群治组织和队伍关联
     *
     * @param ids 需要删除的群防群治组织和队伍关联ID
     * @return 结果
     */
    public int updateBaseOrgPublicOrgPublicTeamByIds(String[] ids);
}
