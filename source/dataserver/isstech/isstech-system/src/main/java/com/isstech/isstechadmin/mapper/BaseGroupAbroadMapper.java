package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseGroupAbroad;
import com.isstech.isstechadmin.domain.vo.CommunityGroupAbroadVo;

/**
 * 境内外非政府组织Mapper接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface BaseGroupAbroadMapper 
{
    /**
     * 查询境内外非政府组织
     * 
     * @param id 境内外非政府组织ID
     * @return 境内外非政府组织
     */
    public CommunityGroupAbroadVo selectBaseGroupAbroadById(String id);

    /**
     * 查询境内外非政府组织列表
     * 
     * @param baseGroupAbroad 境内外非政府组织
     * @return 境内外非政府组织集合
     */
    public List<CommunityGroupAbroadVo> selectBaseGroupAbroadList(CommunityGroupAbroadVo baseGroupAbroad);

    /**
     * 新增境内外非政府组织
     * 
     * @param baseGroupAbroad 境内外非政府组织
     * @return 结果
     */
    public int insertBaseGroupAbroad(BaseGroupAbroad baseGroupAbroad);

    /**
     * 修改境内外非政府组织
     * 
     * @param baseGroupAbroad 境内外非政府组织
     * @return 结果
     */
    public int updateBaseGroupAbroad(BaseGroupAbroad baseGroupAbroad);

    /**
     * 删除境内外非政府组织
     * 
     * @param id 境内外非政府组织ID
     * @return 结果
     */
    public int deleteBaseGroupAbroadById(String id);

    /**
     * 批量删除境内外非政府组织
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseGroupAbroadByIds(String[] ids);
    /**
     * 批量软删除境内外非政府组织
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseGroupAbroadByIds(String[] ids);
}
