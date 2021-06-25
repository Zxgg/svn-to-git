package com.isstech.isstechadmin.service;

import com.isstech.isstechadmin.domain.BaseCommunity;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.domain.vo.TreeVo;

import java.util.List;

/**
 * @Description 社区信息Service接口
 * @Author yzp
 * @Date 2020/11/18 14:30
 */
public interface IBaseCommunityService 
{
    /**
     * 查询社区信息
     * 
     * @param id 社区信息ID
     * @return 社区信息
     */
    public BaseCommunity selectBaseCommunityById(String id);

    /**
     * 查询社区信息列表
     * 
     * @param baseCommunity 社区信息
     * @return 社区信息集合
     */
    public List<BaseCommunity> selectBaseCommunityList(BaseCommunity baseCommunity);

    /**
     * 查询社区菜单列表
     *
     * @return 社区信息集合
     */
    public List<TreeVo> selectBaseCommunityListAll();

    /**
     * 新增社区信息
     * 
     * @param baseCommunity 社区信息
     * @return 结果
     */
    public int insertBaseCommunity(BaseCommunity baseCommunity);

    /**
     * 修改社区信息
     * 
     * @param baseCommunity 社区信息
     * @return 结果
     */
    public int updateBaseCommunity(BaseCommunity baseCommunity);

    /**
     * 批量删除社区信息
     * 
     * @param ids 需要删除的社区信息ID
     * @return 结果
     */
    public int deleteBaseCommunityByIds(String[] ids);
    /**
     * 批量软删除社区信息
     *
     * @param ids 需要删除的社区信息ID
     * @return 结果
     */
    public int updateBaseCommunityByIds(String[] ids);

    /**
     * 删除社区信息信息
     * 
     * @param id 社区信息ID
     * @return 结果
     */
    public int deleteBaseCommunityById(String id);


    /**
     * 通过社区id查询 社区->小区->楼栋->住户 (树形结构)
     * @param id 社区信息id
     * @return 结果
     * @author qishuwei
     * @date 2020-12-07
     */
    public TreeVo selectTreeInfo(QueryVo queryVo);
}
