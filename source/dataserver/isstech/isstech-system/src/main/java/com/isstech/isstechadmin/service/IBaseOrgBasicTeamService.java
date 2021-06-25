package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseOrgBasicTeam;
import com.isstech.isstechadmin.domain.vo.BaseOrgBasicTeamVo;

/**
 * 综治队伍Service接口
 * 
 * @author yzp
 * @date 2021-01-29
 */
public interface IBaseOrgBasicTeamService 
{
    /**
     * 查询综治队伍
     * 
     * @param id 综治队伍ID
     * @return 综治队伍
     */
    public BaseOrgBasicTeamVo selectBaseOrgBasicTeamById(String id);

    /**
     * 查询综治队伍列表
     * 
     * @param baseOrgBasicTeamVo 综治队伍
     * @return 综治队伍集合
     */
    public List<BaseOrgBasicTeamVo> selectBaseOrgBasicTeamVoList(BaseOrgBasicTeamVo baseOrgBasicTeamVo);

    /**
     * 新增综治队伍
     * 
     * @param baseOrgBasicTeam 综治队伍
     * @return 结果
     */
    public int insertBaseOrgBasicTeam(BaseOrgBasicTeamVo baseOrgBasicTeam);

    /**
     * 修改综治队伍
     * 
     * @param baseOrgBasicTeam 综治队伍
     * @return 结果
     */
    public int updateBaseOrgBasicTeam(BaseOrgBasicTeamVo baseOrgBasicTeam);

    /**
     * 批量删除综治队伍
     * 
     * @param ids 需要删除的综治队伍ID
     * @return 结果
     */
    public int deleteBaseOrgBasicTeamByIds(String[] ids);

    /**
     * 删除综治队伍信息
     * 
     * @param id 综治队伍ID
     * @return 结果
     */
    public int deleteBaseOrgBasicTeamById(String id);

    /**
     * 批量软删除综治队伍
     *
     * @param ids 需要删除的综治队伍ID
     * @return 结果
     */
    public int updateBaseOrgBasicTeamByIds(String[] ids);
}
