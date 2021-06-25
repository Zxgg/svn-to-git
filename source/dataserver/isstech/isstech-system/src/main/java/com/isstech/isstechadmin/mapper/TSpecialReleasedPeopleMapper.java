package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.TSpecialReleasedPeople;

/**
 * 刑满释放人员 Mapper接口
 * 
 * @author yzp
 * @date 2021-02-22
 */
public interface TSpecialReleasedPeopleMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询刑满释放人员 
     * 
     * @param id 刑满释放人员 ID
     * @return 刑满释放人员 
     */
    public TSpecialReleasedPeople selectTSpecialReleasedPeopleById(String id);

    /**
     * 查询刑满释放人员 列表
     * 
     * @param tSpecialReleasedPeople 刑满释放人员 
     * @return 刑满释放人员 集合
     */
    public List<TSpecialReleasedPeople> selectTSpecialReleasedPeopleList(TSpecialReleasedPeople tSpecialReleasedPeople);

    /**
     * 新增刑满释放人员 
     * 
     * @param tSpecialReleasedPeople 刑满释放人员 
     * @return 结果
     */
    public int insertTSpecialReleasedPeople(TSpecialReleasedPeople tSpecialReleasedPeople);

    /**
     * 修改刑满释放人员 
     * 
     * @param tSpecialReleasedPeople 刑满释放人员 
     * @return 结果
     */
    public int updateTSpecialReleasedPeople(TSpecialReleasedPeople tSpecialReleasedPeople);

    /**
     * 删除刑满释放人员 
     * 
     * @param id 刑满释放人员 ID
     * @return 结果
     */
    public int deleteTSpecialReleasedPeopleById(String id);

    /**
     * 批量删除刑满释放人员 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTSpecialReleasedPeopleByIds(String[] ids);

    /**
     * 批量软删除刑满释放人员 
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateTSpecialReleasedPeopleByIds(String[] ids);

    /**
     * 通过personId批量软删除
     * @param ids
     * @return 结果
     */
    public int updateTSpecialReleasedPeopleByPersonIds(String[] ids);
}
