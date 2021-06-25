package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.TSpecialYoung;

/**
 * 重点青少年 Mapper接口
 * 
 * @author yzp
 * @date 2020-12-08
 */
public interface TSpecialYoungMapper 
{
    /**
     * 查询重点青少年 
     * 
     * @param id 重点青少年 ID
     * @return 重点青少年 
     */
    public TSpecialYoung selectTSpecialYoungById(String id);

    /**
     * 查询重点青少年 列表
     * 
     * @param tSpecialYoung 重点青少年 
     * @return 重点青少年 集合
     */
    public List<TSpecialYoung> selectTSpecialYoungList(TSpecialYoung tSpecialYoung);

    /**
     * 新增重点青少年 
     * 
     * @param tSpecialYoung 重点青少年 
     * @return 结果
     */
    public int insertTSpecialYoung(TSpecialYoung tSpecialYoung);

    /**
     * 修改重点青少年 
     * 
     * @param tSpecialYoung 重点青少年 
     * @return 结果
     */
    public int updateTSpecialYoung(TSpecialYoung tSpecialYoung);

    /**
     * 删除重点青少年 
     * 
     * @param id 重点青少年 ID
     * @return 结果
     */
    public int deleteTSpecialYoungById(String id);

    /**
     * 批量删除重点青少年 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTSpecialYoungByIds(String[] ids);
    /**
     * 批量软删除重点青少年
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateTSpecialYoungByIds(String[] ids);
}
