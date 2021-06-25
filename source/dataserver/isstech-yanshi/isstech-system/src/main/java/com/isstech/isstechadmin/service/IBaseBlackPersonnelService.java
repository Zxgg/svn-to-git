package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseBlackPersonnel;

/**
 * 人员黑名单Service接口
 * 
 * @author yzp
 * @date 2020-12-22
 */
public interface IBaseBlackPersonnelService 
{
    /**
     * 查询人员黑名单
     * 
     * @param id 人员黑名单ID
     * @return 人员黑名单
     */
    public BaseBlackPersonnel selectBaseBlackPersonnelById(String id);

    /**
     * 查询人员黑名单列表
     * 
     * @param baseBlackPersonnel 人员黑名单
     * @return 人员黑名单集合
     */
    public List<BaseBlackPersonnel> selectBaseBlackPersonnelList(BaseBlackPersonnel baseBlackPersonnel);

    /**
     * 新增人员黑名单
     * 
     * @param baseBlackPersonnel 人员黑名单
     * @return 结果
     */
    public int insertBaseBlackPersonnel(BaseBlackPersonnel baseBlackPersonnel);

    /**
     * 修改人员黑名单
     * 
     * @param baseBlackPersonnel 人员黑名单
     * @return 结果
     */
    public int updateBaseBlackPersonnel(BaseBlackPersonnel baseBlackPersonnel);

    /**
     * 批量删除人员黑名单
     * 
     * @param ids 需要删除的人员黑名单ID
     * @return 结果
     */
    public int deleteBaseBlackPersonnelByIds(String[] ids);

    /**
     * 删除人员黑名单信息
     * 
     * @param id 人员黑名单ID
     * @return 结果
     */
    public int deleteBaseBlackPersonnelById(String id);

    /**
     * 批量软删除人员黑名单
     *
     * @param ids 需要删除的人员黑名单ID
     * @return 结果
     */
    public int updateBaseBlackPersonnelByIds(String[] ids);
}
