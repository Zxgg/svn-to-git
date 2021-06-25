package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseStrangerPersonnel;

/**
 * 陌生人Mapper接口
 * 
 * @author yzp
 * @date 2021-01-15
 */
public interface BaseStrangerPersonnelMapper 
{
    /**
     * 查询陌生人
     * 
     * @param id 陌生人ID
     * @return 陌生人
     */
    public BaseStrangerPersonnel selectBaseStrangerPersonnelById(String id);

    /**
     * 查询陌生人列表
     * 
     * @param baseStrangerPersonnel 陌生人
     * @return 陌生人集合
     */
    public List<BaseStrangerPersonnel> selectBaseStrangerPersonnelList(BaseStrangerPersonnel baseStrangerPersonnel);

    /**
     * 新增陌生人
     * 
     * @param baseStrangerPersonnel 陌生人
     * @return 结果
     */
    public int insertBaseStrangerPersonnel(BaseStrangerPersonnel baseStrangerPersonnel);

    /**
     * 修改陌生人
     * 
     * @param baseStrangerPersonnel 陌生人
     * @return 结果
     */
    public int updateBaseStrangerPersonnel(BaseStrangerPersonnel baseStrangerPersonnel);

    /**
     * 删除陌生人
     * 
     * @param id 陌生人ID
     * @return 结果
     */
    public int deleteBaseStrangerPersonnelById(String id);

    /**
     * 批量删除陌生人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseStrangerPersonnelByIds(String[] ids);

    /**
     * 批量软删除陌生人
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseStrangerPersonnelByIds(String[] ids);
}
