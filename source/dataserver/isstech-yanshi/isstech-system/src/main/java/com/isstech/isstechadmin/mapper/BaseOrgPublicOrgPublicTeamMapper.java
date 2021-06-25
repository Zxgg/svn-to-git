package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseOrgPublicOrgPublicTeam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 群防群治组织和队伍关联Mapper接口
 * 
 * @author yzp
 * @date 2021-01-29
 */
public interface BaseOrgPublicOrgPublicTeamMapper 
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
     * 删除群防群治组织和队伍关联
     * 
     * @param id 群防群治组织和队伍关联ID
     * @return 结果
     */
    public int deleteBaseOrgPublicOrgPublicTeamById(String id);

    /**
     * 批量删除群防群治组织和队伍关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseOrgPublicOrgPublicTeamByIds(String[] ids);

    /**
     * 批量软删除群防群治组织和队伍关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseOrgPublicOrgPublicTeamByIds(String[] ids);

    public int deleteBaseOrgPublicOrgPublicTeamByTeamIds(String[] ids);

    /**
     * 根据 群防队伍ID 查询关联数据id
     * @param publicteamId
     * @return
     */
    @Select("SELECT id FROM base_org_public_org_public_team bobobt where bobobt.publicteam_id = #{publicteamId} ")
    public String queryByTeamId(@Param("publicteamId")String publicteamId);
}
