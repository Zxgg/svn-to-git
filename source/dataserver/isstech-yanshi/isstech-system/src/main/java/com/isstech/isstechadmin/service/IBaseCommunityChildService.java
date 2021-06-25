package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCommunityChild;

/**
 * 小区信息  Service接口
 * 
 * @author yzp
 * @date 2020-11-26
 */
public interface IBaseCommunityChildService 
{
    /**
     * 查询小区信息  
     * 
     * @param id 小区信息  ID
     * @return 小区信息  
     */
    public BaseCommunityChild selectBaseCommunityChildById(String id);

    /**
     * 查询小区信息  列表
     * 
     * @param baseCommunityChild 小区信息  
     * @return 小区信息  集合
     */
    public List<BaseCommunityChild> selectBaseCommunityChildList(BaseCommunityChild baseCommunityChild);

    /**
     * 新增小区信息  
     * 
     * @param baseCommunityChild 小区信息  
     * @return 结果
     */
    public int insertBaseCommunityChild(BaseCommunityChild baseCommunityChild);

    /**
     * 修改小区信息  
     * 
     * @param baseCommunityChild 小区信息  
     * @return 结果
     */
    public int updateBaseCommunityChild(BaseCommunityChild baseCommunityChild);

    /**
     * 批量删除小区信息  
     * 
     * @param ids 需要删除的小区信息  ID
     * @return 结果
     */
    public int deleteBaseCommunityChildByIds(String[] ids);

    /**
     * 批量软删除小区信息
     *
     * @param ids 需要删除的小区信息  ID
     * @return 结果
     */
    public int updateBaseCommunityChildByIds(String[] ids);

    /**
     * 删除小区信息  信息
     * 
     * @param id 小区信息  ID
     * @return 结果
     */
    public int deleteBaseCommunityChildById(String id);
}
