package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.TSpeciaStray;

/**
 * 闲散流浪人员信息 Service接口
 * 
 * @author yzp
 * @date 2020-12-08
 */
public interface ITSpeciaStrayService 
{
    /**
     * 查询闲散流浪人员信息 
     * 
     * @param id 闲散流浪人员信息 ID
     * @return 闲散流浪人员信息 
     */
    public TSpeciaStray selectTSpeciaStrayById(String id);

    /**
     * 查询闲散流浪人员信息 列表
     * 
     * @param tSpeciaStray 闲散流浪人员信息 
     * @return 闲散流浪人员信息 集合
     */
    public List<TSpeciaStray> selectTSpeciaStrayList(TSpeciaStray tSpeciaStray);

    /**
     * 新增闲散流浪人员信息 
     * 
     * @param tSpeciaStray 闲散流浪人员信息 
     * @return 结果
     */
    public int insertTSpeciaStray(TSpeciaStray tSpeciaStray);

    /**
     * 修改闲散流浪人员信息 
     * 
     * @param tSpeciaStray 闲散流浪人员信息 
     * @return 结果
     */
    public int updateTSpeciaStray(TSpeciaStray tSpeciaStray);

    /**
     * 批量删除闲散流浪人员信息 
     * 
     * @param ids 需要删除的闲散流浪人员信息 ID
     * @return 结果
     */
    public int deleteTSpeciaStrayByIds(String[] ids);
    /**
     * 批量软删除闲散流浪人员信息
     *
     * @param ids 需要删除的闲散流浪人员信息 ID
     * @return 结果
     */
    public int updateTSpeciaStrayByIds(String[] ids);

    /**
     * 删除闲散流浪人员信息 信息
     * 
     * @param id 闲散流浪人员信息 ID
     * @return 结果
     */
    public int deleteTSpeciaStrayById(String id);
}
