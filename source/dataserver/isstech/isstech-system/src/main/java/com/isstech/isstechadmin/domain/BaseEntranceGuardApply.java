package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 *  门禁申请对象 base_entrance_guard_apply
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Data
public class BaseEntranceGuardApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 社区id */
    @Excel(name = "社区id")
    private String communityId;

    /** 小区id */
    @Excel(name = "小区id")
    private String communityChildId;

    /** 申请人personID */
    @Excel(name = "申请人personID")
    private String personId;

    /** 申请人姓名 */
    @Excel(name = "申请人姓名")
    private String personName;

    /** 申请人房屋ID（显示时抓换成具体地址） */
    @Excel(name = "申请人房屋ID", readConverterExp = "显=示时抓换成具体地址")
    private String communityHousesId;

    /** 状态（待审核、审核通过、审核拒绝、待领取，已经领取） */
    @Excel(name = "状态", readConverterExp = "待=审核、审核通过、审核拒绝、待领取，已经领取")
    private String stat;

    /** 申请理由（新领门禁卡、补领门禁卡） */
    @Excel(name = "申请理由", readConverterExp = "新=领门禁卡、补领门禁卡")
    private String reason;

    /** 照片列表（比如领取签字） */
    @Excel(name = "照片列表", readConverterExp = "比=如领取签字")
    private String pics;

    /** 领取时间（状态改为已经领取的时间） */
    @Excel(name = "领取时间", readConverterExp = "状=态改为已经领取的时间")
    private Date receiveTime;

    /** 审核人 */
    @Excel(name = "审核人")
    private String checkerName;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;



}
