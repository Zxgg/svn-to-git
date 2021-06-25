package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BasePassPersonnel;
import com.isstech.isstechadmin.domain.vo.PassPersonnelVo;

/**
 * 人员通行Mapper接口
 * 
 * @author yzp
 * @date 2020-12-12
 */
public interface BasePassPersonnelMapper 
{
    /**
     * 查询人员通行
     * 
     * @param faceId 人员通行ID
     * @return 人员通行
     */
    public BasePassPersonnel selectBasePassPersonnelById(String faceId);

    /**
     * 查询人员通行列表
     * 
     * @param passPersonnelVo 人员通行
     * @return 人员通行集合
     */
    public List<BasePassPersonnel> selectBasePassPersonnelList(PassPersonnelVo passPersonnelVo);

    /**
     * 新增人员通行
     * 
     * @param basePassPersonnel 人员通行
     * @return 结果
     */
    public int insertBasePassPersonnel(BasePassPersonnel basePassPersonnel);

    /**
     * 修改人员通行
     * 
     * @param basePassPersonnel 人员通行
     * @return 结果
     */
    public int updateBasePassPersonnel(BasePassPersonnel basePassPersonnel);

    /**
     * 删除人员通行
     * 
     * @param faceId 人员通行ID
     * @return 结果
     */
    public int deleteBasePassPersonnelById(String faceId);

    /**
     * 批量删除人员通行
     * 
     * @param faceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasePassPersonnelByIds(String[] faceIds);
    /**
     * 批量软删除人员通行
     *
     * @param faceIds 需要删除的数据ID
     * @return 结果
     */
    public int updateBasePassPersonnelByIds(String[] faceIds);

    /**
     * 查询关爱人员的通行记录
     * @param passPersonnelVo
     * @return 结果
     * @author qishuwei
     * @date 2021-02-08
     */
    public List<PassPersonnelVo> getCarePassList(PassPersonnelVo passPersonnelVo);
}
