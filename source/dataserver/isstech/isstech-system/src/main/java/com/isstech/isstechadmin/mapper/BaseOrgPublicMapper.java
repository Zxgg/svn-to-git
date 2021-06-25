package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseOrgPublic;
import com.isstech.isstechadmin.domain.vo.CommunityOrgPublicVo;

/**
 * 群防群治组织Mapper接口
 * 
 * @author yzp
 * @date 2020-11-27
 */
public interface BaseOrgPublicMapper 
{
    /**
     * 查询群防群治组织
     * 
     * @param id 群防群治组织ID
     * @return 群防群治组织
     */
    public CommunityOrgPublicVo selectBaseOrgPublicById(String id);

    /**
     * 查询群防群治组织列表
     * 
     * @param baseOrgPublic 群防群治组织
     * @return 群防群治组织集合
     */
    public List<CommunityOrgPublicVo> selectBaseOrgPublicList(CommunityOrgPublicVo baseOrgPublic);

    /**
     * 新增群防群治组织
     * 
     * @param baseOrgPublic 群防群治组织
     * @return 结果
     */
    public int insertBaseOrgPublic(BaseOrgPublic baseOrgPublic);

    /**
     * 修改群防群治组织
     * 
     * @param baseOrgPublic 群防群治组织
     * @return 结果
     */
    public int updateBaseOrgPublic(BaseOrgPublic baseOrgPublic);

    /**
     * 删除群防群治组织
     * 
     * @param id 群防群治组织ID
     * @return 结果
     */
    public int deleteBaseOrgPublicById(String id);

    /**
     * 批量删除群防群治组织
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseOrgPublicByIds(String[] ids);
    /**
     * 批量软删除群防群治组织
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseOrgPublicByIds(String[] ids);
}
