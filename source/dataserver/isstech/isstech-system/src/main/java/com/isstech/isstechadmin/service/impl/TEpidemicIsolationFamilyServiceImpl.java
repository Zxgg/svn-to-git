package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.TEpidemicIsolationFamilyMapper;
import com.isstech.isstechadmin.domain.TEpidemicIsolationFamily;
import com.isstech.isstechadmin.service.ITEpidemicIsolationFamilyService;

/**
 * 隔离家庭 Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-20
 */
@Service
public class TEpidemicIsolationFamilyServiceImpl implements ITEpidemicIsolationFamilyService 
{
    @Autowired
    private TEpidemicIsolationFamilyMapper tEpidemicIsolationFamilyMapper;

    /**
     * 查询隔离家庭 
     * 
     * @param id 隔离家庭 ID
     * @return 隔离家庭 
     */
    @Override
    public TEpidemicIsolationFamily selectTEpidemicIsolationFamilyById(String id)
    {
        return tEpidemicIsolationFamilyMapper.selectTEpidemicIsolationFamilyById(id);
    }

    /**
     * 查询隔离家庭 列表
     * 
     * @param tEpidemicIsolationFamily 隔离家庭 
     * @return 隔离家庭 
     */
    @Override
    public List<TEpidemicIsolationFamily> selectTEpidemicIsolationFamilyList(TEpidemicIsolationFamily tEpidemicIsolationFamily)
    {
        return tEpidemicIsolationFamilyMapper.selectTEpidemicIsolationFamilyList(tEpidemicIsolationFamily);
    }

    /**
     * 新增隔离家庭 
     * 
     * @param tEpidemicIsolationFamily 隔离家庭 
     * @return 结果
     */
    @Override
    public int insertTEpidemicIsolationFamily(TEpidemicIsolationFamily tEpidemicIsolationFamily)
    {
        tEpidemicIsolationFamily.setCreateBy(SecurityUtils.getUsername());
        tEpidemicIsolationFamily.setCreateTime(DateUtils.getNowDate());
        tEpidemicIsolationFamily.setDelFlag("0");
        return tEpidemicIsolationFamilyMapper.insertTEpidemicIsolationFamily(tEpidemicIsolationFamily);
    }

    /**
     * 修改隔离家庭 
     * 
     * @param tEpidemicIsolationFamily 隔离家庭 
     * @return 结果
     */
    @Override
    public int updateTEpidemicIsolationFamily(TEpidemicIsolationFamily tEpidemicIsolationFamily)
    {
        tEpidemicIsolationFamily.setUpdateBy(SecurityUtils.getUsername());
        tEpidemicIsolationFamily.setUpdateTime(DateUtils.getNowDate());
        return tEpidemicIsolationFamilyMapper.updateTEpidemicIsolationFamily(tEpidemicIsolationFamily);
    }

    /**
     * 批量删除隔离家庭 
     * 
     * @param ids 需要删除的隔离家庭 ID
     * @return 结果
     */
    @Override
    public int deleteTEpidemicIsolationFamilyByIds(String[] ids)
    {
        return tEpidemicIsolationFamilyMapper.deleteTEpidemicIsolationFamilyByIds(ids);
    }

    /**
     * 批量软删除隔离家庭 
     *
     * @param ids 需要删除的隔离家庭 ID
     * @return 结果
     */
    @Override
    public int updateTEpidemicIsolationFamilyByIds(String[] ids)
    {
        return tEpidemicIsolationFamilyMapper.updateTEpidemicIsolationFamilyByIds(ids);
    }

    @Override
    public List<TEpidemicIsolationFamily> queryTEpidemicIsolationFamilyList(TEpidemicIsolationFamily tEpidemicIsolationFamily) {
        return tEpidemicIsolationFamilyMapper.queryTEpidemicIsolationFamilyList(tEpidemicIsolationFamily);
    }

    /**
     * 删除隔离家庭 信息
     * 
     * @param id 隔离家庭 ID
     * @return 结果
     */
    @Override
    public int deleteTEpidemicIsolationFamilyById(String id)
    {
        return tEpidemicIsolationFamilyMapper.deleteTEpidemicIsolationFamilyById(id);
    }
}
