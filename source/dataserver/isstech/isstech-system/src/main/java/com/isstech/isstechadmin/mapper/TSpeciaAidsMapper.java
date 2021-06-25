package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.TSpeciaAids;

/**
 * 艾滋病人 Mapper接口
 * 
 * @author yzp
 * @date 2021-02-22
 */
public interface TSpeciaAidsMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询艾滋病人 
     * 
     * @param id 艾滋病人 ID
     * @return 艾滋病人 
     */
    public TSpeciaAids selectTSpeciaAidsById(String id);

    /**
     * 查询艾滋病人 列表
     * 
     * @param tSpeciaAids 艾滋病人 
     * @return 艾滋病人 集合
     */
    public List<TSpeciaAids> selectTSpeciaAidsList(TSpeciaAids tSpeciaAids);

    /**
     * 新增艾滋病人 
     * 
     * @param tSpeciaAids 艾滋病人 
     * @return 结果
     */
    public int insertTSpeciaAids(TSpeciaAids tSpeciaAids);

    /**
     * 修改艾滋病人 
     * 
     * @param tSpeciaAids 艾滋病人 
     * @return 结果
     */
    public int updateTSpeciaAids(TSpeciaAids tSpeciaAids);

    /**
     * 删除艾滋病人 
     * 
     * @param id 艾滋病人 ID
     * @return 结果
     */
    public int deleteTSpeciaAidsById(String id);

    /**
     * 批量删除艾滋病人 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTSpeciaAidsByIds(String[] ids);
    /**
     * 批量软删除艾滋病人
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateTSpeciaAidsByIds(String[] ids);


    public int updateTSpeciaAidsByPersonIds(String[] ids);
}
