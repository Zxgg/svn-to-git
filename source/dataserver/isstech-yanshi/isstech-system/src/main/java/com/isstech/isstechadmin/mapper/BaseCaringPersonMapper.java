package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCaringPerson;
import com.isstech.isstechadmin.domain.vo.CaringPerson;

/**
 * 关爱人员信息记录 Mapper接口
 * 
 * @author yzp
 * @date 2020-11-26
 */
public interface BaseCaringPersonMapper 
{
    /**
     * 查询关爱人员信息记录 
     * 
     * @param id 关爱人员信息记录 ID
     * @return 关爱人员信息记录 
     */
    public BaseCaringPerson selectBaseCaringPersonById(String id);

    /**
     * 查询关爱人员信息记录 列表
     * 
     * @param baseCaringPerson 关爱人员信息记录 
     * @return 关爱人员信息记录 集合
     */
    public List<BaseCaringPerson> selectBaseCaringPersonList(BaseCaringPerson baseCaringPerson);

    /**
     * 查询关爱人员信息记录 列表
     *
     * @param caringPerson 关爱人员信息记录
     * @return 关爱人员信息记录 集合
     */
    public List<CaringPerson> selectBaseCaringAddPersonList(CaringPerson caringPerson);

    /**
     * 新增关爱人员信息记录 
     * 
     * @param baseCaringPerson 关爱人员信息记录 
     * @return 结果
     */
    public int insertBaseCaringPerson(BaseCaringPerson baseCaringPerson);

    /**
     * 修改关爱人员信息记录 
     * 
     * @param baseCaringPerson 关爱人员信息记录 
     * @return 结果
     */
    public int updateBaseCaringPerson(BaseCaringPerson baseCaringPerson);

    /**
     * 删除关爱人员信息记录 
     * 
     * @param id 关爱人员信息记录 ID
     * @return 结果
     */
    public int deleteBaseCaringPersonById(String id);

    /**
     * 批量删除关爱人员信息记录 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseCaringPersonByIds(String[] ids);

    /**
     * 批量软删除关爱人员信息记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseCaringPersonByIds(String[] ids);

    /**
     * 通过人员id查询关爱人员
     * @return 结果
     */
    public BaseCaringPerson getByPersonId(String personId);
}
