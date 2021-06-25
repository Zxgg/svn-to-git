package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCaringPerson;
import com.isstech.isstechadmin.domain.vo.CaringPerson;

/**
 * 关爱人员信息记录 Service接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface IBaseCaringPersonService 
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
     * 根据（社区名称、关爱人员名称、关爱类型 ）查询关爱人员信息列表
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
     * 批量删除关爱人员信息记录 
     * 
     * @param ids 需要删除的关爱人员信息记录 ID
     * @return 结果
     */
    public int deleteBaseCaringPersonByIds(String[] ids);

    /**
     * 批量软删除关爱人员信息记录
     *
     * @param ids 需要删除的关爱人员信息记录 ID
     * @return 结果
     */
    public int updateBaseCaringPersonByIds(String[] ids);

    /**
     * 删除关爱人员信息记录 信息
     * 
     * @param id 关爱人员信息记录 ID
     * @return 结果
     */
    public int deleteBaseCaringPersonById(String id);


    public BaseCaringPerson getByPersonId(String personId);
}
