package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 人员通行对象 base_pass_personnel
 *
 * @author yzp
 * @date 2020-12-18
 */
@Data
public class BasePassPersonnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    private String faceId;

    /** 同行人脸数 */
    private String accompanyNumber;

    /** 痤疮色斑 */
    private String acneStain;

    /** 年龄下限 */
    private Integer ageLowerLimit;

    /** 年龄上限 */
    private Integer ageUpLimit;

    /** 绰号 */
    private String alias;

    /** 姿态分布 */
    private Integer attitude;

    /** 体表特殊标记 */
    private String bodySpeciallMark;

    /** 帽子颜色 */
    private String capColor;

    /** 帽子款式 */
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
    private String communityName;

    /** 尸体状况代码 */
    private String corpseConditionCode;

    /** 作案特点代码 */
    private String crimeCharacterCode;

    /** 作案手段 */
    private String crimeMethod;

    /** 涉案人员专长代码 */
    private String criminalInvolvedSpecilisationCode;

    /** 在押人员身份 */
    private String detaineesIdentity;

    /** 在押人员特殊身份 */
    private String detaineesSpecialIdentity;

    /** 看守所编码 */
    @Excel(name = "看守所编码")
    private String detentionHouseCode;

    /** 设备编码 */
    private String deviceId;

    /** 在逃人员编号 */
    private String escapedCriminalNumber;

    /** 民族代码 */
    private String ethicCode;

    /** 眉型 */
    @Excel(name = "眉型")
    private String eyebrowStyle;

    /** 人脸出现时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "人脸出现时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date faceAppearTime;

    /** 人脸消失时间 */
    private Date faceDisAppearTime;

    /** 脸型 */
    private String faceStyle;

    /** 脸部特征 */
    private String facialFeature;

    /** 黑痣胎记 */
    @Excel(name = "黑痣胎记")
    private String freckleBirthmark;

    /** 性别代码 */
    @Excel(name = "性别代码")
    private String genderCode;

    /** 眼镜颜色 */
    private String glassColor;

    /** 眼镜款式 */
    @Excel(name = "眼镜款式")
    private String glassStyle;

    /** 发色 */
    private String hairColor;

    /** 发型 */
    @Excel(name = "发型")
    private String hairStyle;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idnumber;

    /** 证件种类 */
    private String idType;

    /** 出入境人员类别代码 */
    private String immigrantTypeCode;

    /** 信息分类 */
    private String infoKind;

    /** 受伤害程度 */
    private String injuredDegree;

    /** 是否涉案人员 */
    private Integer isCriminalInvolved;

    /** 是否在押人员 */
    private Integer isDetainees;

    /** 是否驾驶员 */
    private String isDriver;

    /** 是否涉外人员 */
    private Integer isForeigner;

    /** 是否涉恐人员 */
    private Integer isSuspectedTerrorist;

    /** 是否可疑人 */
    private Integer isSuspiciousPerson;

    /** 是否被害人 */
    private Integer isVictim;

    /** 职业类别代码 */
    private String jobCategory;

    /** 左上角X坐标 */
    private String leftTopX;

    /** 左上角Y坐标 */
    private String leftTopY;

    /** 嘴唇 */
    @Excel(name = "嘴唇")
    private String lipStyle;

    /** 位置标记时间 */
    private Date locationMarkTime;

    /** 成员类型代码 */
    private String memberTypeCode;

    /** 胡型 */
    @Excel(name = "胡型")
    private String mustacheStyle;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 国籍代码 */
    private String nationalityCode;

    /** 籍贯省市县代码 */
    private String nativeCityCode;

    /** 鼻型 */
    private String noseStyle;

    /** 其他特征 */
    private String otherFeature;

    /** 护照证件种类 */
    private String passportType;

    /** 体貌特征 */
    @Excel(name = "体貌特征")
    private String physicalFeature;

    /** 居住地行政区划 */
    private String residenceAdminDivision;

    /** 口罩颜色 */
    private String respiratorColor;

    /** 右下角X坐标 */
    private String rightBtmX;

    /** 右下角Y坐标 */
    private String rightBtmY;

    /** 疤痕酒窝 */
    private String scarDimple;

    /** 人脸相似度 */
    private Long similaritydegree;

    /** 肤色 */
    private String skinColor;

    /** 来源标识 */
    private String sourceId;

    /** 涉恐人员编号 */
    private String suspectedTerroristNumber;

    /** 曾用名 */
    private String usedName;

    /** 被害人种类 */
    private String victimType;

    /** 皱纹眼袋 */
    private String wrinklePouch;

    /** 对应的人员表里的人员ID */
    private String personId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 来源表 */
    @Excel(name = "来源表")
    private String sourceTableName;

    /** 是否已经审核确认,(0否1是,默认0)*/
    private String checked;

    /** 照片列表，多张照片以逗号隔开，默认显示第一张 */
    private String pics;

    /** 标记位置 */
    @Excel(name = "标记位置")
    private String locationMarkAddr;

    @Excel(name = "关爱人员类型 00-代表非关爱人员 关爱类型参考关爱人员类型字典")
    private String careType;
}
