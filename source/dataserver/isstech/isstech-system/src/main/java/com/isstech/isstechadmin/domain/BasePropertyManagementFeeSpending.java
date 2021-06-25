package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 物业费用支出记录 对象 base_property_management_fee_spending
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Data
public class BasePropertyManagementFeeSpending extends BaseEntity
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

    /** 支出类型（绿化费用、公共部位维修费、基础设施维修费比如某家的水管） */
    @Excel(name = "支出类型", readConverterExp = "绿=化费用、公共部位维修费、基础设施维修费比如某家的水管")
    private String type;

    /** 支出费用（元） */
    @Excel(name = "支出费用", readConverterExp = "元=")
    private String amountOfMoney;

    /** 支出人姓名 */
    @Excel(name = "支出人姓名")
    private String payerName;

    /** 照片列表 */
    @Excel(name = "照片列表")
    private String pics;

    /** 实施人员（比如绿化施工人、维修工人） */
    @Excel(name = "实施人员", readConverterExp = "比=如绿化施工人、维修工人")
    private String relatedPerson;



}
