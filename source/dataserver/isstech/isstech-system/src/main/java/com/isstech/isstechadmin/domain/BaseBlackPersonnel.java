package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 人员黑名单对象 base_black_personnel
 * 
 * @author yzp
 * @date 2020-12-22
 */
@Data
public class BaseBlackPersonnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 曾用名 */
    @Excel(name = "曾用名")
    private String oldName;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 国籍（地区） */
    @Excel(name = "国籍", readConverterExp = "地=区")
    private String nationality;

    /** 证件类型代码（默认为中国身份证） */
    @Excel(name = "证件类型代码", readConverterExp = "默=认为中国身份证")
    private String idType;

    /** 证件号码(默认是18位身份证号码) */
    @Excel(name = "证件号码(默认是18位身份证号码)")
    private String identificationNumber;

    /** 证件有效期开始时间 */
    @Excel(name = "证件有效期开始时间")
    private String idcardStartDate;

    /** 证件有效期结束时间 */
    @Excel(name = "证件有效期结束时间")
    private String idcardEndDate;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String location;

    /** 户籍详址 */
    @Excel(name = "户籍详址")
    private String residenceDetail;

    /** 现住地区划 */
    @Excel(name = "现住地区划")
    private String address;

    /** 现住详址 */
    @Excel(name = "现住详址")
    private String addressDetail;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 婚姻状况(0否1是,默认0) */
    @Excel(name = "婚姻状况(0否1是,默认0)")
    private String marital;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String political;

    /** 学历 */
    @Excel(name = "学历")
    private String degree;

    /** 宗教信仰 */
    @Excel(name = "宗教信仰")
    private String religion;

    /** 职业类别 */
    @Excel(name = "职业类别")
    private String profession;

    /** 职业信息（不是字典项） */
    @Excel(name = "职业信息", readConverterExp = "不=是字典项")
    private String professioDetail;

    /** 服务处所 */
    @Excel(name = "服务处所")
    private String servicePremises;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 特殊人员类型,(0否,大于0标识特殊人员类型，查询时-1查询所有特殊人员） */
    @Excel(name = "特殊人员类型,(0否,大于0标识特殊人员类型，查询时-1查询所有特殊人员）")
    private String special;

    /** 管控限制人员,(0否1是,默认0) */
    @Excel(name = "管控限制人员,(0否1是,默认0)")
    private String limited;

    /** 关爱人员,(0否1是,默认0) */
    @Excel(name = "关爱人员,(0否1是,默认0)")
    private String caring;

    /** 是否是流动人员,(0否1是,默认0) */
    @Excel(name = "是否是流动人员,(0否1是,默认0)")
    private String floating;

    /** 最后入境（国境）时间 */
    @Excel(name = "最后入境", readConverterExp = "国=境")
    private Date arrivalCountryDate;

    /** 最后入境（省级边界）时间 */
    @Excel(name = "最后入境", readConverterExp = "省=级边界")
    private Date arrivalProvinceDate;

    /** 预计离开日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计离开日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date departureDate;

    /** 流入原因 */
    @Excel(name = "流入原因")
    private String floatingReasons;

    /** 流入地办证类型 */
    @Excel(name = "流入地办证类型")
    private String cardType;

    /** 流入地办证机关 */
    @Excel(name = "流入地办证机关")
    private String cardHandlingAgency;

    /** 流入地证件号码 */
    @Excel(name = "流入地证件号码")
    private String cardNum;

    /** 流入地证件登记日期 */
    @Excel(name = "流入地证件登记日期")
    private String cardRecordDate;

    /** 照片列表，多张照片以逗号隔开，默认显示第一张 */
    @Excel(name = "照片列表，多张照片以逗号隔开，默认显示第一张")
    private String pics;

    /** 是否已经审核确认,(0否1是,默认0) */
    @Excel(name = "是否已经审核确认,(0否1是,默认0)")
    private String checked;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date brithDate;

    /** 本条记录所属社区ID */
    @Excel(name = "本条记录所属社区ID")
    private String communityId;

    /** 本条记录所属小区ID */
    @Excel(name = "本条记录所属小区ID")
    private String communityChildId;

    /** 来源表 */
    @Excel(name = "来源表")
    private String sourceTableName;

    /** 对应的人员表里的人员ID */
    private String personId;

    /** 图片 */
    private String imgUrl;


}
