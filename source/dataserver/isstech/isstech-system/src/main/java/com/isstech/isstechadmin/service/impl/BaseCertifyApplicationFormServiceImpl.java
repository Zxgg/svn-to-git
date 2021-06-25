package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseCertifyApplicationFormMapper;
import com.isstech.isstechadmin.domain.BaseCertifyApplicationForm;
import com.isstech.isstechadmin.service.IBaseCertifyApplicationFormService;

/**
 * 证明办理申请单Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Service
public class BaseCertifyApplicationFormServiceImpl implements IBaseCertifyApplicationFormService 
{
    @Autowired
    private BaseCertifyApplicationFormMapper baseCertifyApplicationFormMapper;

    /**
     * 查询证明办理申请单
     * 
     * @param id 证明办理申请单ID
     * @return 证明办理申请单
     */
    @Override
    public BaseCertifyApplicationForm selectBaseCertifyApplicationFormById(String id)
    {
        return baseCertifyApplicationFormMapper.selectBaseCertifyApplicationFormById(id);
    }

    /**
     * 查询证明办理申请单列表
     * 
     * @param baseCertifyApplicationForm 证明办理申请单
     * @return 证明办理申请单
     */
    @Override
    public List<BaseCertifyApplicationForm> selectBaseCertifyApplicationFormList(BaseCertifyApplicationForm baseCertifyApplicationForm)
    {
        return baseCertifyApplicationFormMapper.selectBaseCertifyApplicationFormList(baseCertifyApplicationForm);
    }

    /**
     * 新增证明办理申请单
     * 
     * @param baseCertifyApplicationForm 证明办理申请单
     * @return 结果
     */
    @Override
    public int insertBaseCertifyApplicationForm(BaseCertifyApplicationForm baseCertifyApplicationForm)
    {
        baseCertifyApplicationForm.setId(baseCertifyApplicationFormMapper.selectUuid());
        baseCertifyApplicationForm.setCreateBy(SecurityUtils.getUsername());
        baseCertifyApplicationForm.setCreateTime(DateUtils.getNowDate());
        baseCertifyApplicationForm.setDelFlag("0");
        return baseCertifyApplicationFormMapper.insertBaseCertifyApplicationForm(baseCertifyApplicationForm);
    }

    /**
     * 修改证明办理申请单
     * 
     * @param baseCertifyApplicationForm 证明办理申请单
     * @return 结果
     */
    @Override
    public int updateBaseCertifyApplicationForm(BaseCertifyApplicationForm baseCertifyApplicationForm)
    {
        baseCertifyApplicationForm.setUpdateBy(SecurityUtils.getUsername());
        baseCertifyApplicationForm.setUpdateTime(DateUtils.getNowDate());
        return baseCertifyApplicationFormMapper.updateBaseCertifyApplicationForm(baseCertifyApplicationForm);
    }

    /**
     * 批量删除证明办理申请单
     * 
     * @param ids 需要删除的证明办理申请单ID
     * @return 结果
     */
    @Override
    public int deleteBaseCertifyApplicationFormByIds(String[] ids)
    {
        return baseCertifyApplicationFormMapper.deleteBaseCertifyApplicationFormByIds(ids);
    }

    /**
     * 批量软删除证明办理申请单
     *
     * @param ids 需要删除的证明办理申请单ID
     * @return 结果
     */
    @Override
    public int updateBaseCertifyApplicationFormByIds(String[] ids)
    {
        return baseCertifyApplicationFormMapper.updateBaseCertifyApplicationFormByIds(ids);
    }

    /**
     * 删除证明办理申请单信息
     * 
     * @param id 证明办理申请单ID
     * @return 结果
     */
    @Override
    public int deleteBaseCertifyApplicationFormById(String id)
    {
        return baseCertifyApplicationFormMapper.deleteBaseCertifyApplicationFormById(id);
    }
}
