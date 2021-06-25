package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 资料领取通知对象 base_data_request_notification
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Data
public class BaseDataRequestNotification extends BaseEntity
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

    /** 领取人personID */
    @Excel(name = "领取人personID")
    private String personId;

    /** 领取材料类型（门禁卡、社保卡、居住证） */
    @Excel(name = "领取材料类型", readConverterExp = "门=禁卡、社保卡、居住证")
    private String materialType;

    /** 领取状态（待领取、已经领取、 */
    @Excel(name = "领取状态", readConverterExp = "领取状态（待领取、已经领取、")
    private String getStatus;

    /** 领取时间点（比如每天的9点到18点，枚举） */
    @Excel(name = "领取时间点", readConverterExp = "比=如每天的9点到18点，枚举")
    private String getTime;

    /** 领取地点 */
    @Excel(name = "领取地点")
    private String getLocation;

    /** 领取时需要的携带的材料（比如本人身份证） */
    @Excel(name = "领取时需要的携带的材料", readConverterExp = "比=如本人身份证")
    private String getByCertificate;

    /** 是否必须本人领取 */
    @Excel(name = "是否必须本人领取")
    private String getThe;

    /** 待领人需要携带的证件（如果允许待领） */
    @Excel(name = "待领人需要携带的证件", readConverterExp = "如=果允许待领")
    private String getReplaceByCertificate;

    /** 领取人姓名 */
    @Excel(name = "领取人姓名")
    private String getTheName;

    /** 照片列表（比如领取签字） */
    @Excel(name = "照片列表", readConverterExp = "比=如领取签字")
    private String pics;



}
