package com.isstech.isstechadmin.service.impl;

import java.util.List;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.vo.PassPersonnelVo;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BasePassPersonnelMapper;
import com.isstech.isstechadmin.domain.BasePassPersonnel;
import com.isstech.isstechadmin.service.IBasePassPersonnelService;

/**
 * 人员通行Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-12
 */
@Service
public class BasePassPersonnelServiceImpl implements IBasePassPersonnelService 
{
    @Autowired
    private BasePassPersonnelMapper basePassPersonnelMapper;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询人员通行
     * 
     * @param faceId 人员通行ID
     * @return 人员通行
     */
    @Override
    public BasePassPersonnel selectBasePassPersonnelById(String faceId)
    {
        return basePassPersonnelMapper.selectBasePassPersonnelById(faceId);
    }

    /**
     * 查询人员通行列表
     * 
     * @param personnelVo 人员通行
     * @return 人员通行
     */
    @Override
    public List<BasePassPersonnel> selectBasePassPersonnelList(PassPersonnelVo personnelVo)
    {
        String startMarkTime = DateUtils.timeStamp4Date2String(personnelVo.getStartMarkTime());
        personnelVo.setStartMarkTime(startMarkTime);
        String endMarkTime = DateUtils.timeStamp4Date2String(personnelVo.getEndMarkTime());
        personnelVo.setEndMarkTime(endMarkTime);
        return basePassPersonnelMapper.selectBasePassPersonnelList(personnelVo);
    }

    /**
     * 新增人员通行
     * 
     * @param basePassPersonnel 人员通行
     * @return 结果
     */
    @Override
    public int insertBasePassPersonnel(BasePassPersonnel basePassPersonnel)
    {
        basePassPersonnel.setCreateTime(DateUtils.getNowDate());
        basePassPersonnel.setFaceAppearTime(DateUtils.getNowDate());
//        basePassPersonnel.setCreateBy(SecurityUtils.getUsername());
        return basePassPersonnelMapper.insertBasePassPersonnel(basePassPersonnel);
    }

    /**
     * 修改人员通行
     * 
     * @param basePassPersonnel 人员通行
     * @return 结果
     */
    @Override
    public int updateBasePassPersonnel(BasePassPersonnel basePassPersonnel)
    {
        basePassPersonnel.setUpdateTime(DateUtils.getNowDate());
        basePassPersonnel.setUpdateBy(SecurityUtils.getUsername());
        return basePassPersonnelMapper.updateBasePassPersonnel(basePassPersonnel);
    }

    /**
     * 批量删除人员通行
     *
     * @param faceIds 需要删除的人员通行ID
     * @return 结果
     */
    @Override
    public int deleteBasePassPersonnelByIds(String[] faceIds)
    {
        return basePassPersonnelMapper.deleteBasePassPersonnelByIds(faceIds);
    }
    /**
     * 批量软删除人员通行
     *
     * @param faceIds 需要删除的人员通行ID
     * @return 结果
     */
    @Override
    public int updateBasePassPersonnelByIds(String[] faceIds)
    {
        return basePassPersonnelMapper.updateBasePassPersonnelByIds(faceIds);
    }

    /**
     * 删除人员通行信息
     *
     * @param faceId 人员通行ID
     * @return 结果
     */
    @Override
    public int deleteBasePassPersonnelById(String faceId)
    {
        return basePassPersonnelMapper.deleteBasePassPersonnelById(faceId);
    }

    @Override
    public List<PassPersonnelVo> getCarePassList(PassPersonnelVo passPersonnelVo) {
        List<PassPersonnelVo> list = basePassPersonnelMapper.getCarePassList(passPersonnelVo);
        return list;
    }
}
