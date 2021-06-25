package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseOrgPublicTeam;
import com.isstech.isstechadmin.domain.vo.BaseOrgPublicTeamVo;

/**
 * 群防群治队伍Service接口
 * 
 * @author yzp
 * @date 2021-01-29
 */
public interface IBaseOrgPublicTeamService 
{
    /**
     * 查询群防群治队伍
     * 
     * @param id 群防群治队伍ID
     * @return 群防群治队伍
     */
    public BaseOrgPublicTeam selectBaseOrgPublicTeamById(String id);

    /**
     * 查询群防群治队伍列表
     * 
     * @param baseOrgPublicTeam 群防群治队伍
     * @return 群防群治队伍集合
     */
    public List<BaseOrgPublicTeam> selectBaseOrgPublicTeamList(BaseOrgPublicTeam baseOrgPublicTeam);

    /**
     * 新增群防群治队伍
     * 
     * @param baseOrgPublicTeamVo 群防群治队伍
     * @return 结果
     */
    public int insertBaseOrgPublicTeam(BaseOrgPublicTeamVo baseOrgPublicTeamVo);

    /**
     * 修改群防群治队伍
     * 
     * @param baseOrgPublicTeamVo 群防群治队伍
     * @return 结果
     */
    public int updateBaseOrgPublicTeam(BaseOrgPublicTeamVo baseOrgPublicTeamVo);

    /**
     * 批量删除群防群治队伍
     * 
     * @param ids 需要删除的群防群治队伍ID
     * @return 结果
     */
    public int deleteBaseOrgPublicTeamByIds(String[] ids);

    /**
     * 删除群防群治队伍信息
     * 
     * @param id 群防群治队伍ID
     * @return 结果
     */
    public int deleteBaseOrgPublicTeamById(String id);

    /**
     * 批量软删除群防群治队伍
     *
     * @param ids 需要删除的群防群治队伍ID
     * @return 结果
     */
    public int updateBaseOrgPublicTeamByIds(String[] ids);
}
