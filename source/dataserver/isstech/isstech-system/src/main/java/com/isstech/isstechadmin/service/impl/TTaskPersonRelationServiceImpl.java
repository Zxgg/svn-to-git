package com.isstech.isstechadmin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.PageInfo;
import com.isstech.common.core.domain.Resp;
import com.isstech.common.core.redis.RedisCache;
import com.isstech.common.utils.http.HttpClientUtil;
import com.isstech.common.utils.http.HttpUtils;
import com.isstech.isstechadmin.domain.SysConfig;
import com.isstech.isstechadmin.mapper.BasePassAnalysisTaskMapper;
import org.apache.commons.codec.Charsets;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.TTaskPersonRelationMapper;
import com.isstech.isstechadmin.domain.TTaskPersonRelation;
import com.isstech.isstechadmin.service.ITTaskPersonRelationService;

import javax.annotation.PostConstruct;

/**
 * 人脸和人员对应关系任务 Service业务层处理
 *
 * @author yzp
 * @date 2020-12-17
 */
@Service
public class TTaskPersonRelationServiceImpl implements ITTaskPersonRelationService
{
    @Autowired
    private TTaskPersonRelationMapper tTaskPersonRelationMapper;
    @Autowired
    private BasePassAnalysisTaskMapper basePassAnalysisTaskMapper;
    @Autowired
    private RedisCache redisCache;

    /**
     * 查询人脸和人员对应关系任务
     *
     * @param peopleId 人脸和人员对应关系任务 ID
     * @return 人脸和人员对应关系任务
     */
    @Override
    public TTaskPersonRelation selectTTaskPersonRelationById(String peopleId)
    {
        return tTaskPersonRelationMapper.selectTTaskPersonRelationById(peopleId);
    }

    /**
     * 查询人脸和人员对应关系任务 列表
     *
     * @param tTaskPersonRelation 人脸和人员对应关系任务
     * @return 人脸和人员对应关系任务
     */
    @Override
    public List<TTaskPersonRelation> selectTTaskPersonRelationList(TTaskPersonRelation tTaskPersonRelation)
    {
        return tTaskPersonRelationMapper.selectTTaskPersonRelationList(tTaskPersonRelation);
    }

    /**
     * 新增人脸和人员对应关系任务
     *
     * @param tTaskPersonRelation 人脸和人员对应关系任务
     * @return 结果
     */
    @Override
    public int insertTTaskPersonRelation(TTaskPersonRelation tTaskPersonRelation)
    {
        return tTaskPersonRelationMapper.insertTTaskPersonRelation(tTaskPersonRelation);
    }

    /**
     * 修改人脸和人员对应关系任务
     *
     * @param tTaskPersonRelation 人脸和人员对应关系任务
     * @return 结果
     */
    @Override
    public int updateTTaskPersonRelation(TTaskPersonRelation tTaskPersonRelation)
    {
        return tTaskPersonRelationMapper.updateTTaskPersonRelation(tTaskPersonRelation);
    }

    /**
     * 批量删除人脸和人员对应关系任务
     *
     * @param peopleIds 需要删除的人脸和人员对应关系任务 ID
     * @return 结果
     */
    @Override
    public int deleteTTaskPersonRelationByIds(String[] peopleIds)
    {
        return tTaskPersonRelationMapper.deleteTTaskPersonRelationByIds(peopleIds);
    }

    /**
     * 批量软删除人脸和人员对应关系任务
     *
     * @param peopleIds 需要删除的人脸和人员对应关系任务 ID
     * @return 结果
     */
    @Override
    public int updateTTaskPersonRelationByIds(String[] peopleIds)
    {
        return tTaskPersonRelationMapper.updateTTaskPersonRelationByIds(peopleIds);
    }

    @Override
    public Map<String, String> queryPersonId(String faceId, String suspectId) {
        return tTaskPersonRelationMapper.queryPersonId(faceId, suspectId);
    }

    /**
     * 删除人脸和人员对应关系任务 信息
     *
     * @param peopleId 人脸和人员对应关系任务 ID
     * @return 结果
     */
    @Override
    public int deleteTTaskPersonRelationById(String peopleId)
    {
        return tTaskPersonRelationMapper.deleteTTaskPersonRelationById(peopleId);
    }


}
