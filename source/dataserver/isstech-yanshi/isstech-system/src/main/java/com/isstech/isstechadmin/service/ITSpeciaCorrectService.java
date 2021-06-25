package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.TSpeciaCorrect;

/**
 * 社区矫正人员信息 Service接口
 * 
 * @author yzp
 * @date 2021-02-22
 */
public interface ITSpeciaCorrectService 
{
    /**
     * 查询社区矫正人员信息 
     * 
     * @param id 社区矫正人员信息 ID
     * @return 社区矫正人员信息 
     */
    public TSpeciaCorrect selectTSpeciaCorrectById(String id);

    /**
     * 查询社区矫正人员信息 列表
     * 
     * @param tSpeciaCorrect 社区矫正人员信息 
     * @return 社区矫正人员信息 集合
     */
    public List<TSpeciaCorrect> selectTSpeciaCorrectList(TSpeciaCorrect tSpeciaCorrect);

    /**
     * 新增社区矫正人员信息 
     * 
     * @param tSpeciaCorrect 社区矫正人员信息 
     * @return 结果
     */
    public int insertTSpeciaCorrect(TSpeciaCorrect tSpeciaCorrect);

    /**
     * 修改社区矫正人员信息 
     * 
     * @param tSpeciaCorrect 社区矫正人员信息 
     * @return 结果
     */
    public int updateTSpeciaCorrect(TSpeciaCorrect tSpeciaCorrect);

    /**
     * 批量删除社区矫正人员信息 
     * 
     * @param ids 需要删除的社区矫正人员信息 ID
     * @return 结果
     */
    public int deleteTSpeciaCorrectByIds(String[] ids);

    /**
     * 删除社区矫正人员信息 信息
     * 
     * @param id 社区矫正人员信息 ID
     * @return 结果
     */
    public int deleteTSpeciaCorrectById(String id);

    /**
     * 批量软删除社区矫正人员信息 
     *
     * @param ids 需要删除的社区矫正人员信息 ID
     * @return 结果
     */
    public int updateTSpeciaCorrectByIds(String[] ids);
}
