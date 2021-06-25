package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseEntranceGuardApply;

/**
 *  门禁申请Service接口
 * 
 * @author yzp
 * @date 2021-02-19
 */
public interface IBaseEntranceGuardApplyService 
{
    /**
     * 查询 门禁申请
     * 
     * @param id  门禁申请ID
     * @return  门禁申请
     */
    public BaseEntranceGuardApply selectBaseEntranceGuardApplyById(String id);

    /**
     * 查询 门禁申请列表
     * 
     * @param baseEntranceGuardApply  门禁申请
     * @return  门禁申请集合
     */
    public List<BaseEntranceGuardApply> selectBaseEntranceGuardApplyList(BaseEntranceGuardApply baseEntranceGuardApply);

    /**
     * 新增 门禁申请
     * 
     * @param baseEntranceGuardApply  门禁申请
     * @return 结果
     */
    public int insertBaseEntranceGuardApply(BaseEntranceGuardApply baseEntranceGuardApply);

    /**
     * 修改 门禁申请
     * 
     * @param baseEntranceGuardApply  门禁申请
     * @return 结果
     */
    public int updateBaseEntranceGuardApply(BaseEntranceGuardApply baseEntranceGuardApply);

    /**
     * 批量删除 门禁申请
     * 
     * @param ids 需要删除的 门禁申请ID
     * @return 结果
     */
    public int deleteBaseEntranceGuardApplyByIds(String[] ids);

    /**
     * 删除 门禁申请信息
     * 
     * @param id  门禁申请ID
     * @return 结果
     */
    public int deleteBaseEntranceGuardApplyById(String id);

    /**
     * 批量软删除 门禁申请
     *
     * @param ids 需要删除的 门禁申请ID
     * @return 结果
     */
    public int updateBaseEntranceGuardApplyByIds(String[] ids);
}
