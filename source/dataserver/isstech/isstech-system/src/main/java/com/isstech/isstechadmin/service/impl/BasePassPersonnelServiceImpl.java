package com.isstech.isstechadmin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BasePassPersonnel;
import com.isstech.isstechadmin.domain.vo.PassPersonnelVo;
import com.isstech.isstechadmin.mapper.BasePassPersonnelMapper;
import com.isstech.isstechadmin.service.IBasePassPersonnelService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 人员通行Service业务层处理
 *
 * @author yzp
 * @date 2020-12-12
 */
@Service
public class BasePassPersonnelServiceImpl implements IBasePassPersonnelService {
    @Autowired
    private BasePassPersonnelMapper basePassPersonnelMapper;
    @Autowired
    private DataAccessUtils dataAccessUtils;
    private static final Logger log = LoggerFactory.getLogger(BasePassPersonnelServiceImpl.class);

    /**
     * 查询人员通行
     *
     * @param faceId 人员通行ID
     * @return 人员通行
     */
    @Override
    public BasePassPersonnel selectBasePassPersonnelById(String faceId) {
        return basePassPersonnelMapper.selectBasePassPersonnelById(faceId);
    }

    /**
     * 查询人员通行列表
     *
     * @param personnelVo 人员通行
     * @return 人员通行
     */
    @Override
    public List<BasePassPersonnel> selectBasePassPersonnelList(PassPersonnelVo personnelVo) {
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
    public int insertBasePassPersonnel(BasePassPersonnel basePassPersonnel) {
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
    public int updateBasePassPersonnel(BasePassPersonnel basePassPersonnel) {
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
    public int deleteBasePassPersonnelByIds(String[] faceIds) {
        return basePassPersonnelMapper.deleteBasePassPersonnelByIds(faceIds);
    }

    /**
     * 批量软删除人员通行
     *
     * @param faceIds 需要删除的人员通行ID
     * @return 结果
     */
    @Override
    public int updateBasePassPersonnelByIds(String[] faceIds) {
        return basePassPersonnelMapper.updateBasePassPersonnelByIds(faceIds);
    }

    /**
     * 删除人员通行信息
     *
     * @param faceId 人员通行ID
     * @return 结果
     */
    @Override
    public int deleteBasePassPersonnelById(String faceId) {
        return basePassPersonnelMapper.deleteBasePassPersonnelById(faceId);
    }

    @Override
    public List<PassPersonnelVo> getCarePassList(PassPersonnelVo passPersonnelVo) {
        List<PassPersonnelVo> list = basePassPersonnelMapper.getCarePassList(passPersonnelVo);
        return list;
    }

    /**
     * 保存华为推送的通行数据
     *
     * @param jsonArray
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHuaWeiTransferData(JSONArray jsonArray) {

        if (CollectionUtils.isEmpty(jsonArray)) return;

        for (Object el : jsonArray) {
            basePassPersonnelMapper.insertBasePassPersonnel(parseToBassPassPersonal((JSONObject) el));
        }
        log.debug("operation complete");
    }

    private BasePassPersonnel parseToBassPassPersonal(JSONObject el) {
        BasePassPersonnel saveObject = new BasePassPersonnel();
        // 主要信息
        JSONObject commonInfo = el.getJSONObject("commonInfo");
        // 隐私信息
        JSONObject privateInfo = el.getJSONObject("privateInfo");
        // 隐私信息-属性特征
        JSONObject attributes = privateInfo.getJSONObject("attributes");

        saveObject.setId(commonInfo.getString("taskId"));
        saveObject.setFaceId(commonInfo.getString("faceId"));


        // 未取值: 同行人脸数，年龄上限，绰号，姿态分布，汉语口音代码，楼栋id，楼栋名称
        // 小区id，小区名称，房屋id，房屋名称，社区id，社区名称，尸体状况代码，作案特点代码
        // 作案手段，涉案人员专长代码，在押人员身份，在押人员特殊身份，看守所编码，在逃人员编号
        // 人脸出现时间，人脸消失时间，黑痣胎记，发色，证件号码，证件种类，出入境人员类别代码
        // 信息分类，受伤害程度，是否涉案人员，是否在押人员，是否驾驶员，是否涉外人员，是否涉恐人员
        // 是否可疑人，是否被害人，职业类别代码，左上角X坐标，左上角Y坐标，位置标记时间，成员类型代码
        // 姓名，国籍代码，籍贯省市县代码，其他特征，护照证件种类，居住地行政区划，右下角X坐标，右下角Y坐标
        // 疤痕酒窝，
        //
        // 数据库字段 - 华为提供字段
        // 痤疮色斑 - 脸部特征
        saveObject.setAcneStain(attributes.getString("facialFeature"));
        // 年龄下限 - 年龄下限
        saveObject.setAgeLowerLimit(attributes.getInteger("ageLower"));
        // 体表特殊标记 - 体表特征
        saveObject.setBodySpeciallMark(attributes.getString("phyFeature"));
        // 帽子颜色
        saveObject.setCapColor(attributes.getString("capcolor"));
        // 帽子款式
        saveObject.setCapStyle(attributes.getString("capStyle"));
        // 设备编码  - 摄像机id
        saveObject.setDeviceId(commonInfo.getString("cameraId"));
        // 民族
        saveObject.setEthicCode(attributes.getString("nation"));
        // 眉型
        saveObject.setEyebrowStyle(attributes.getString("eyebrowStyle"));
        // 脸型
        saveObject.setFaceStyle(attributes.getString("faceStyle"));
        // 脸部特征
        saveObject.setFacialFeature(attributes.getString("facialFeature"));
        // 性别
        saveObject.setGenderCode(attributes.getString("gender"));
        // 眼镜颜色
        saveObject.setGlassColor(attributes.getString("glassColor"));
        // 眼镜款式
        saveObject.setGlassStyle(attributes.getString("glassStyle"));
        // 发型 - 头发
        saveObject.setHairStyle(attributes.getString("hair"));
        // 嘴唇
        saveObject.setLipStyle(attributes.getString("lipStyle"));
        // 胡型
        saveObject.setMustacheStyle(attributes.getString("mustacheStyle"));
        // 鼻型
        saveObject.setNoseStyle(attributes.getString("noseStyle"));
        // 体貌特征
        saveObject.setPhysicalFeature(attributes.getString("phyFeature"));
        // 口罩颜色
        saveObject.setRespiratorColor(attributes.getString("respiratorColor"));
        // 相似度 - 与黑名单图片的相似度
        saveObject.setSimilaritydegree(attributes.getLong("scr"));
        // 肤色
        saveObject.setSkinColor(attributes.getString("skinColor"));
        // 来源标识 - 告警来源
        saveObject.setSourceId(commonInfo.getString("source"));
        saveObject.setDelFlag("0");

        return saveObject;
    }
}
