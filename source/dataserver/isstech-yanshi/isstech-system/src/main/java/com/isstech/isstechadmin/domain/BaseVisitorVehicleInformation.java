package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 访客车辆信息对象 base_visitor_vehicle_information
 * 
 * @author yzp
 * @date 2020-11-27
 */
@Data
public class BaseVisitorVehicleInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String plate;

    /** 车型 */
    @Excel(name = "车型")
    private String model;

    /** 车品牌 */
    @Excel(name = "车品牌")
    private String brand;

    /** 具体品牌型号 */
    @Excel(name = "具体品牌型号")
    private String specificBrand;

    /** 座位数 */
    @Excel(name = "座位数")
    private Long numberOfSeats;

    /** 驾驶员姓名 */
    @Excel(name = "驾驶员姓名")
    private String driverName;

    /** 性别（0男 1女 2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String driverSex;

    /** 驾驶员身份证号码(18位身份证号码) */
    @Excel(name = "驾驶员身份证号码(18位身份证号码)")
    private String driverIdentificationNumber;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String driverPhoneNumber;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 是否已经审核确认,(0否1是,默认0) */
    @Excel(name = "是否已经审核确认,(0否1是,默认0)")
    private String checked;

    /** 照片列表，多张照片以逗号隔开，默认显示第一张 */
    @Excel(name = "照片列表，多张照片以逗号隔开，默认显示第一张")
    private String pics;

    /** 审核人员ID，一般是管理员或者被访人 */
    @Excel(name = "审核人员ID，一般是管理员或者被访人")
    private String checkedPersonId;

    /** 审核人员名称 */
    @Excel(name = "审核人员名称")
    private String checkedPersonName;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkedTime;

    /** 被拜访人ID */
    @Excel(name = "被拜访人ID")
    private String targetPersonId;

    /** 被拜访人名称 */
    @Excel(name = "被拜访人名称")
    private String targetPersonName;

    /** 被拜访人社区ID */
    @Excel(name = "被拜访人社区ID")
    private String targetPersonCommunityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String targetPersonCommunityChildId;

    /** 被拜访人房屋ID */
    @Excel(name = "被拜访人房屋ID")
    private String targetPersonHouseId;

    /** 被拜访人房屋具体地址 */
    @Excel(name = "被拜访人房屋具体地址")
    private String targetPersonHouseAddress;

    /** 拜访开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "拜访开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date visitStartTime;

    /** 拜访结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "拜访结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date visitEndTime;

    /** 访客的图片是否已经加入到闸机许可中，0否1是，默认为0 */
    @Excel(name = "访客的图片是否已经加入到闸机许可中，0否1是，默认为0")
    private String visitorPicPermit;

    /** 访客的车牌号是否已经加入到闸机许可中，0否1是，默认为0 */
    @Excel(name = "访客的车牌号是否已经加入到闸机许可中，0否1是，默认为0")
    private String visitorPlatePermit;

    /** 车辆类型 */
    private String vehicleType;

}
