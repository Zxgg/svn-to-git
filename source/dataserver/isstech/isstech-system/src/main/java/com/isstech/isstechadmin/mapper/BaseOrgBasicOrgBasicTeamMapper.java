package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseOrgBasicOrgBasicTeam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 综治机构和综治队伍关联Mapper接口
 * 
 * @author yzp
 * @date 2021-01-29
 */
public interface BaseOrgBasicOrgBasicTeamMapper 
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
     * 删除综治机构和综治队伍关联
     * 
     * @param id 综治机构和综治队伍关联ID
     * @return 结果
     */
    public int deleteBaseOrgBasicOrgBasicTeamById(String id);

    /**
     * 批量删除综治机构和综治队伍关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseOrgBasicOrgBasicTeamByIds(String[] ids);

    public int deleteBaseOrgBasicOrgBasicTeamByTeamIds(String[] teamIds);


    /**
     * 批量软删除综治机构和综治队伍关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseOrgBasicOrgBasicTeamByIds(String[] ids);

    @Select("SELECT id FROM base_org_basic_org_basic_team bobobt where bobobt.basic_id = #{basicId} and bobobt.team_id = #{teamId} ")
    public String queryByBasicIdAndTeamId(@Param("basicId") String basicId, @Param("teamId")String teamId);

    /**
     * 根据 综治队伍ID 查询关联数据id
     * @param teamId
     * @return
     */
    @Select("SELECT id FROM base_org_basic_org_basic_team bobobt where bobobt.team_id = #{teamId} ")
    public String queryByTeamId(@Param("teamId")String teamId);
}
