package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 告警人员对象 base_alarm_personnel
 * 
 * @author yzp
 * @date 2021-04-27
 */
@Data
public class BaseAlarmPersonnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参数主键 */
    private String id;

    /** 人脸ID */
    @Excel(name = "人脸ID")
    private String faceId;

    /** 同行人脸数 */
    @Excel(name = "同行人脸数")
    private String accompanyNumber;

    /** 痤疮色斑 */
    @Excel(name = "痤疮色斑")
    private String acneStain;

    /** 年龄下限 */
    @Excel(name = "年龄下限")
    private Long ageLowerLimit;

    /** 年龄上限 */
    @Excel(name = "年龄上限")
    private Long ageUpLimit;

    /** 绰号 */
    @Excel(name = "绰号")
    private String alias;

    /** 姿态分布 */
    @Excel(name = "姿态分布")
    private Long attitude;

    /** 体表特殊标记 */
    @Excel(name = "体表特殊标记")
    private String bodySpeciallMark;

    /** 帽子颜色 */
    @Excel(name = "帽子颜色")
    private String capColor;

    /** 帽子款式 */
    @Excel(name = "帽子款式")
    private String capStyle;

    /** 汉语口音代码 */
    @Excel(name = "汉语口音代码")
    private String chineseAccentCode;

    /** 楼栋id */
    @Excel(name = "楼栋id")
    private String communityBuildingId;

    /** 楼栋名称 */
    @Excel(name = "楼栋名称")
    private String communityBuildingName;

    /** 小区id */
    @Excel(name = "小区id")
    private String communityChildId;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private String communityChildName;

    /** 房屋id */
    @Excel(name = "房屋id")
    private String communityHousesId;

    /** 房屋名称 */
    @Excel(name = "房屋名称")
    private String communityHousesName;

    /** 社区id */
    @Excel(name = "社区id")
    private String communityId;

    /** 社区名称 */
    @Excel(name = "社区名称")
    private String communityName;

    /** 尸体状况代码 */
    @Excel(name = "尸体状况代码")
    private String corpseConditionCode;

    /** 作案特点代码 */
    @Excel(name = "作案特点代码")
    private String crimeCharacterCode;

    /** 作案手段 */
    @Excel(name = "作案手段")
    private String crimeMethod;

    /** 涉案人员专长代码 */
    @Excel(name = "涉案人员专长代码")
    private String criminalInvolvedSpecilisationCode;

    /** 在押人员身份 */
    @Excel(name = "在押人员身份")
    private String detaineesIdentity;

    /** 在押人员特殊身份 */
    @Excel(name = "在押人员特殊身份")
    private String detaineesSpecialIdentity;

    /** 看守所编码 */
    @Excel(name = "看守所编码")
    private String detentionHouseCode;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceId;

    /** 在逃人员编号 */
    @Excel(name = "在逃人员编号")
    private String escapedCriminalNumber;

    /** 民族代码 */
    @Excel(name = "民族代码")
    private String ethicCode;

    /** 眉型 */
    @Excel(name = "眉型")
    private String eyebrowStyle;

    /** 人脸出现时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "人脸出现时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date faceAppearTime;

    /** 人脸消失时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "人脸消失时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date faceDisAppearTime;

    /** 脸型 */
    @Excel(name = "脸型")
    private String faceStyle;

    /** 脸部特征 */
    @Excel(name = "脸部特征")
    private String facialFeature;

    /** 黑痣胎记 */
    @Excel(name = "黑痣胎记")
    private String freckleBirthmark;

    /** 性别代码 */
    @Excel(name = "性别代码")
    private String genderCode;

    /** 眼镜颜色 */
    @Excel(name = "眼镜颜色")
    private String glassColor;

    /** 眼镜款式 */
    @Excel(name = "眼镜款式")
    private String glassStyle;

    /** 发色 */
    @Excel(name = "发色")
    private String hairColor;

    /** 发型 */
    @Excel(name = "发型")
    private String hairStyle;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idnumber;

    /** 证件种类 */
    @Excel(name = "证件种类")
    private String idType;

    /** 出入境人员类别代码 */
    @Excel(name = "出入境人员类别代码")
    private String immigrantTypeCode;

    /** 信息分类 */
    @Excel(name = "信息分类")
    private String infoKind;

    /** 受伤害程度 */
    @Excel(name = "受伤害程度")
    private String injuredDegree;

    /** 是否涉案人员 */
    @Excel(name = "是否涉案人员")
    private Long isCriminalInvolved;

    /** 是否在押人员 */
    @Excel(name = "是否在押人员")
    private Long isDetainees;

    /** 是否驾驶员 */
    @Excel(name = "是否驾驶员")
    private String isDriver;

    /** 是否涉外人员 */
    @Excel(name = "是否涉外人员")
    private Long isForeigner;

    /** 是否涉恐人员 */
    @Excel(name = "是否涉恐人员")
    private Long isSuspectedTerrorist;

    /** 是否可疑人 */
    @Excel(name = "是否可疑人")
    private Long isSuspiciousPerson;

    /** 是否被害人 */
    @Excel(name = "是否被害人")
    private Long isVictim;

    /** 职业类别代码 */
    @Excel(name = "职业类别代码")
    private String jobCategory;

    /** 左上角X坐标 */
    @Excel(name = "左上角X坐标")
    private String leftTopX;

    /** 左上角Y坐标 */
    @Excel(name = "左上角Y坐标")
    private String leftTopY;

    /** 嘴唇 */
    @Excel(name = "嘴唇")
    private String lipStyle;

    /** 位置标记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "位置标记时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date locationMarkTime;

    /** 成员类型代码 */
    @Excel(name = "成员类型代码")
    private String memberTypeCode;

    /** 胡型 */
    @Excel(name = "胡型")
    private String mustacheStyle;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 国籍代码 */
    @Excel(name = "国籍代码")
    private String nationalityCode;

    /** 籍贯省市县代码 */
    @Excel(name = "籍贯省市县代码")
    private String nativeCityCode;

    /** 鼻型 */
    @Excel(name = "鼻型")
    private String noseStyle;

    /** 其他特征 */
    @Excel(name = "其他特征")
    private String otherFeature;

    /** 护照证件种类 */
    @Excel(name = "护照证件种类")
    private String passportType;

    /** 体貌特征 */
    @Excel(name = "体貌特征")
    private String physicalFeature;

    /** 居住地行政区划 */
    @Excel(name = "居住地行政区划")
    private String residenceAdminDivision;

    /** 口罩颜色 */
    @Excel(name = "口罩颜色")
    private String respiratorColor;

    /** 右下角X坐标 */
    @Excel(name = "右下角X坐标")
    private String rightBtmX;

    /** 右下角Y坐标 */
    @Excel(name = "右下角Y坐标")
    private String rightBtmY;

    /** 疤痕酒窝 */
    @Excel(name = "疤痕酒窝")
    private String scarDimple;

    /** 人脸相似度 */
    @Excel(name = "人脸相似度")
    private Double similaritydegree;

    /** 肤色 */
    @Excel(name = "肤色")
    private String skinColor;

    /** 来源标识 */
    @Excel(name = "来源标识")
    private String sourceId;

    /** 涉恐人员编号 */
    @Excel(name = "涉恐人员编号")
    private String suspectedTerroristNumber;

    /** 曾用名 */
    @Excel(name = "曾用名")
    private String usedName;

    /** 被害人种类 */
    @Excel(name = "被害人种类")
    private String victimType;

    /** 皱纹眼袋 */
    @Excel(name = "皱纹眼袋")
    private String wrinklePouch;

    /** 对应的人员表里的人员ID */
    @Excel(name = "对应的人员表里的人员ID")
    private String personId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 来源表 */
    @Excel(name = "来源表")
    private String sourceTableName;

    /** 是否已经审核确认,(0否1是,默认0) */
    @Excel(name = "是否已经审核确认,(0否1是,默认0)")
    private String checked;

    /** 照片列表，多张照片以逗号隔开，默认显示第一张 */
    @Excel(name = "照片列表，多张照片以逗号隔开，默认显示第一张")
    private String pics;

    /** 标记位置 */
    @Excel(name = "标记位置")
    private String locationMarkAddr;



}
