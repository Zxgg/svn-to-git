package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 陌生人对象 base_stranger_personnel
 * 
 * @author yzp
 * @date 2021-01-15
 */
@Data
public class BaseStrangerPersonnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 人脸id */
    @Excel(name = "人脸id")
    private String faceId;

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

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceId;

    /** 人脸出现时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "人脸出现时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date faceAppearTime;

    /** 人脸消失时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "人脸消失时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date faceDisAppearTime;

    /** 脸型 */
    @Excel(name = "脸型")
    private String faceStyle;

    /** 脸部特征 */
    @Excel(name = "脸部特征")
    private String facialFeature;

    /** 性别（-1不限，0未知，1男，2女） */
    @Excel(name = "性别", readConverterExp = "-=1不限，0未知，1男，2女")
    private String gender;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idnumber;

    /** 证件种类 */
    @Excel(name = "证件种类")
    private String idType;

    /** 表情 */
    @Excel(name = "表情")
    private String expression;

    /** 位置标记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "位置标记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date locationMarkTime;

    /** 标记位置 */
    @Excel(name = "标记位置")
    private String locationMarkAddr;

    /** 年龄值 */
    @Excel(name = "年龄值")
    private String agevalue;

    /** 眼镜 */
    @Excel(name = "眼镜")
    private String eyeglass;

    /** 口罩 */
    @Excel(name = "口罩")
    private String mouthmask;

    /** 年龄段（-1不限，0未知，1儿童，2少年，3青年，4中年，5老年） */
    @Excel(name = "年龄段", readConverterExp = "-=1不限，0未知，1儿童，2少年，3青年，4中年，5老年")
    private String age;

    /** 来源标识 */
    @Excel(name = "来源标识")
    private String sourceId;

    /** 对应的人员表里的人员ID */
    @Excel(name = "对应的人员表里的人员ID")
    private String personId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 来源表 */
    @Excel(name = "来源表")
    private String sourceTableName;

    /** 照片列表，多张照片以逗号隔开，默认显示第一张 */
    @Excel(name = "照片列表，多张照片以逗号隔开，默认显示第一张")
    private String pics;

    /** 眉型 */
    @Excel(name = "眉型")
    private String eyebrowStyle;

    /** 黑痣胎记 */
    @Excel(name = "黑痣胎记")
    private String freckleBirthmark;

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

    /** 信息分类 */
    @Excel(name = "信息分类")
    private String infoKind;

    /** 同行人脸数 */
    @Excel(name = "同行人脸数")
    private String accompanyNumber;

    /** 痤疮色斑 */
    @Excel(name = "痤疮色斑")
    private String acneStain;

    /** 姿态分布 */
    @Excel(name = "姿态分布")
    private String attitude;

    /** 体表特殊标记 */
    @Excel(name = "体表特殊标记")
    private String bodySpeciallMark;

    /** 帽子颜色 */
    @Excel(name = "帽子颜色")
    private String capColor;

    /** 帽子款式 */
    @Excel(name = "帽子款式")
    private String capStyle;

    /** 左上角X坐标 */
    @Excel(name = "左上角X坐标")
    private String leftTopX;

    /** 左上角Y坐标 */
    @Excel(name = "左上角Y坐标")
    private String leftTopY;

    /** 嘴唇 */
    @Excel(name = "嘴唇")
    private String lipStyle;

    /** 成员类型代码 */
    @Excel(name = "成员类型代码")
    private String memberTypeCode;

    /** 胡型 */
    @Excel(name = "胡型")
    private String mustacheStyle;

    /** 鼻型 */
    @Excel(name = "鼻型")
    private String noseStyle;

    /** 其他特征 */
    @Excel(name = "其他特征")
    private String otherFeature;

    /** 体貌特征 */
    @Excel(name = "体貌特征")
    private String physicalFeature;

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

    /** 肤色 */
    @Excel(name = "肤色")
    private String skinColor;



}
