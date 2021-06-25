package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 证明办理申请单对象 base_certify_application_form
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Data
public class BaseCertifyApplicationForm extends BaseEntity
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

    /** 申请办理的证件类型 */
    @Excel(name = "申请办理的证件类型")
    private String certificateType;

    /** 所需申请人提供的材料信息（由配置项中获取） */
    @Excel(name = "所需申请人提供的材料信息", readConverterExp = "由=配置项中获取")
    private String requiredMaterial;

    /** 申请人上传的图片列表 */
    @Excel(name = "申请人上传的图片列表")
    private String pics;

    /** 办理状态（已申请、审核通过、审核拒绝、待领取，已经领取） */
    @Excel(name = "办理状态", readConverterExp = "已=申请、审核通过、审核拒绝、待领取，已经领取")
    private String transactionStatus;

    /** 申请时间（创建时间） */
    @Excel(name = "申请时间", readConverterExp = "创=建时间")
    private Date applyDate;

    /** 领取时间（状态改为已经领取的时间） */
    @Excel(name = "领取时间", readConverterExp = "状=态改为已经领取的时间")
    private Date receiveDate;

    /** 审核人 */
    @Excel(name = "审核人")
    private String auditBy;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date auditDate;



}
