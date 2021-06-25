package com.isstech.isstechadmin.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isstech.isstechadmin.domain.BasePassPersonnel;
import com.isstech.isstechadmin.domain.vo.PassPersonnelVo;

import java.util.List;

/**
 * 人员通行Service接口
 *
 * @author yzp
 * @date 2020-12-12
 */
public interface IBasePassPersonnelService {
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
     * @param personnelVo 人员通行
     * @return 人员通行集合
     */
    public List<BasePassPersonnel> selectBasePassPersonnelList(PassPersonnelVo personnelVo);

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
     * 批量删除人员通行
     *
     * @param faceIds 需要删除的人员通行ID
     * @return 结果
     */
    public int deleteBasePassPersonnelByIds(String[] faceIds);

    /**
     * 批量软删除人员通行
     *
     * @param faceIds 需要删除的人员通行ID
     * @return 结果
     */
    public int updateBasePassPersonnelByIds(String[] faceIds);

    /**
     * 删除人员通行信息
     *
     * @param faceId 人员通行ID
     * @return 结果
     */
    public int deleteBasePassPersonnelById(String faceId);

    public List<PassPersonnelVo> getCarePassList(PassPersonnelVo passPersonnelVo);

    /**
     * 保存华为推送的通行数据
     *
     * @param jsonArray
     */
    void saveHuaWeiTransferData(JSONArray jsonArray);
}
