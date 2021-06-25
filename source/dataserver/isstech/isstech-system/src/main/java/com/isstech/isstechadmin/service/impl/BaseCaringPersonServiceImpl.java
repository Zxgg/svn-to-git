package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.vo.CaringPerson;
import com.isstech.isstechadmin.service.IBaseCaringPersonService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseCaringPersonMapper;
import com.isstech.isstechadmin.domain.BaseCaringPerson;

/**
 * 关爱人员信息记录 Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseCaringPersonServiceImpl implements IBaseCaringPersonService
{
    @Autowired
    private BaseCaringPersonMapper baseCaringPersonMapper;

    /**
     * 查询关爱人员信息记录 
     * 
     * @param id 关爱人员信息记录 ID
     * @return 关爱人员信息记录 
     */
    @Override
    public BaseCaringPerson selectBaseCaringPersonById(String id)
    {
        return baseCaringPersonMapper.selectBaseCaringPersonById(id);
    }

    /**
     * 查询关爱人员信息记录 列表
     * 
     * @param baseCaringPerson 关爱人员信息记录 
     * @return 关爱人员信息记录 
     */
    @Override
    public List<BaseCaringPerson> selectBaseCaringPersonList(BaseCaringPerson baseCaringPerson)
    {
        return baseCaringPersonMapper.selectBaseCaringPersonList(baseCaringPerson);
    }

    /**
     * 查询关爱人员信息记录 列表 -- 添加人员相关字段
     *
     * @param caringPerson 关爱人员信息记录
     * @return 关爱人员信息记录
     */
    @Override
    public List<CaringPerson> selectBaseCaringAddPersonList(CaringPerson caringPerson)
    {
        return baseCaringPersonMapper.selectBaseCaringAddPersonList(caringPerson);
    }

    /**
     * 新增关爱人员信息记录 
     * 
     * @param baseCaringPerson 关爱人员信息记录 
     * @return 结果
     */
    @Override
    public int insertBaseCaringPerson(BaseCaringPerson baseCaringPerson)
    {
        baseCaringPerson.setCreateTime(DateUtils.getNowDate());
        baseCaringPerson.setCreateBy(SecurityUtils.getUsername());
        return baseCaringPersonMapper.insertBaseCaringPerson(baseCaringPerson);
    }

    /**
     * 修改关爱人员信息记录 
     * 
     * @param baseCaringPerson 关爱人员信息记录 
     * @return 结果
     */
    @Override
    public int updateBaseCaringPerson(BaseCaringPerson baseCaringPerson)
    {
        baseCaringPerson.setUpdateTime(DateUtils.getNowDate());
        baseCaringPerson.setUpdateBy(SecurityUtils.getUsername());
        return baseCaringPersonMapper.updateBaseCaringPerson(baseCaringPerson);
    }

    /**
     * 批量删除关爱人员信息记录 
     * 
     * @param ids 需要删除的关爱人员信息记录 ID
     * @return 结果
     */
    @Override
    public int deleteBaseCaringPersonByIds(String[] ids)
    {
        return baseCaringPersonMapper.deleteBaseCaringPersonByIds(ids);
    }

    /**
     * 批量软删除关爱人员信息记录
     *
     * @param ids 需要删除的关爱人员信息记录 ID
     * @return 结果
     */
    @Override
    public int updateBaseCaringPersonByIds(String[] ids)
    {
        return baseCaringPersonMapper.updateBaseCaringPersonByIds(ids);
    }

    /**
     * 删除关爱人员信息记录 信息
     * 
     * @param id 关爱人员信息记录 ID
     * @return 结果
     */
    @Override
    public int deleteBaseCaringPersonById(String id)
    {
        return baseCaringPersonMapper.deleteBaseCaringPersonById(id);
    }

    @Override
    public BaseCaringPerson getByPersonId(String personId) {

        return baseCaringPersonMapper.getByPersonId(personId);
    }
}
