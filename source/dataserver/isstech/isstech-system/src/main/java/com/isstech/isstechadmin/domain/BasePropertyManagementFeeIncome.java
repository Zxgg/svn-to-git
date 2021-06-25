package com.isstech.isstechadmin.domain;

import java.math.BigDecimal;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 物业费用收入记录 对象 base_property_management_fee_income
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Data
public class BasePropertyManagementFeeIncome extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

    /** 房屋ID */
    @Excel(name = "房屋ID")
    private String communityHousesId;

    /** 应缴费用（元） */
    @Excel(name = "应缴费用", readConverterExp = "元=")
    private BigDecimal shouldPay;

    /** 实缴费用 */
    @Excel(name = "实缴费用")
    private BigDecimal realityPay;

    /** 缴费区间  yyyy-MM,yyyy-MM */
    @Excel(name = "缴费区间  yyyy-MM,yyyy-MM")
    private String payCostRange;

    /** 房屋面积 */
    @Excel(name = "房屋面积")
    private BigDecimal area;

    /** 物业费单价/每平方米（这个手动输入，收取后留档用） */
    @Excel(name = "物业费单价/每平方米", readConverterExp = "这=个手动输入，收取后留档用")
    private BigDecimal price;

    /** 缴费人姓名 */
    @Excel(name = "缴费人姓名")
    private String payerName;

    /** 缴费人手机号（选填） */
    @Excel(name = "缴费人手机号", readConverterExp = "选=填")
    private String payerPhoneNumber;

    /** 房屋业主姓名（房主，出租房的缴费人和业主不一致） */
    @Excel(name = "房屋业主姓名", readConverterExp = "房=主，出租房的缴费人和业主不一致")
    private String ownerName;

    /** 业主身份证号码 */
    @Excel(name = "业主身份证号码")
    private String ownerIdentificationNumber;

    /** 收费人，（默认填入上次输入） */
    @Excel(name = "收费人，", readConverterExp = "默=认填入上次输入")
    private String cashierName;

    /** 计算公式（起记录作用） */
    @Excel(name = "计算公式", readConverterExp = "起=记录作用")
    private String strategy;

    /** 缴费方式 */
    @Excel(name = "缴费方式")
    private String payWay;

    /** 照片列表 */
    @Excel(name = "照片列表")
    private String pics;



}
