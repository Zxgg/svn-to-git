package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCertifyApplicationForm;

/**
 * 证明办理申请单Mapper接口
 * 
 * @author yzp
 * @date 2021-02-19
 */
public interface BaseCertifyApplicationFormMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询证明办理申请单
     * 
     * @param id 证明办理申请单ID
     * @return 证明办理申请单
     */
    public BaseCertifyApplicationForm selectBaseCertifyApplicationFormById(String id);

    /**
     * 查询证明办理申请单列表
     * 
     * @param baseCertifyApplicationForm 证明办理申请单
     * @return 证明办理申请单集合
     */
    public List<BaseCertifyApplicationForm> selectBaseCertifyApplicationFormList(BaseCertifyApplicationForm baseCertifyApplicationForm);

    /**
     * 新增证明办理申请单
     * 
     * @param baseCertifyApplicationForm 证明办理申请单
     * @return 结果
     */
    public int insertBaseCertifyApplicationForm(BaseCertifyApplicationForm baseCertifyApplicationForm);

    /**
     * 修改证明办理申请单
     * 
     * @param baseCertifyApplicationForm 证明办理申请单
     * @return 结果
     */
    public int updateBaseCertifyApplicationForm(BaseCertifyApplicationForm baseCertifyApplicationForm);

    /**
     * 删除证明办理申请单
     * 
     * @param id 证明办理申请单ID
     * @return 结果
     */
    public int deleteBaseCertifyApplicationFormById(String id);

    /**
     * 批量删除证明办理申请单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseCertifyApplicationFormByIds(String[] ids);

    /**
     * 批量软删除证明办理申请单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseCertifyApplicationFormByIds(String[] ids);
}
