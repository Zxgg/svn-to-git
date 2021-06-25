package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.BaseOrgBasic;
import com.isstech.isstechadmin.domain.vo.CommunityOrgBasicVo;

import java.util.List;

/**
 * 综治机构管理Mapper接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface BaseOrgBasicMapper 
{
    /**
     * 查询综治机构管理
     * 
     * @param id 综治机构管理ID
     * @return 综治机构管理
     */
    public CommunityOrgBasicVo selectBaseOrgBasicById(String id);

    /**
     * 查询综治机构管理列表
     * 
     * @param baseOrgBasic 综治机构管理
     * @return 综治机构管理集合
     */
    public List<CommunityOrgBasicVo> selectBaseOrgBasicList(CommunityOrgBasicVo baseOrgBasic);

    /**
     * 新增综治机构管理
     * 
     * @param baseOrgBasic 综治机构管理
     * @return 结果
     */
    public int insertBaseOrgBasic(BaseOrgBasic baseOrgBasic);

    /**
     * 修改综治机构管理
     * 
     * @param baseOrgBasic 综治机构管理
     * @return 结果
     */
    public int updateBaseOrgBasic(BaseOrgBasic baseOrgBasic);

    /**
     * 删除综治机构管理
     * 
     * @param id 综治机构管理ID
     * @return 结果
     */
    public int deleteBaseOrgBasicById(String id);

    /**
     * 批量删除综治机构管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseOrgBasicByIds(String[] ids);
    /**
     * 批量软删除综治机构管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseOrgBasicByIds(String[] ids);
}
