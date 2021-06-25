package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 报事报修记录对象 base_repair_record
 * 
 * @author yzp
 * @date 2020-11-25
 */
public class BaseRepairRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 报修人姓名 */
    @Excel(name = "报修人姓名")
    private String repairPersonName;

    /** 报修事项 */
    @Excel(name = "报修事项")
    private String repairContent;

    /** 是否已经处理(0未处理，1正在处理，2，已经处理，3不处理） */
    @Excel(name = "是否已经处理(0未处理，1正在处理，2，已经处理，3不处理）")
    private String deal;

    /** 处理人姓名 */
    @Excel(name = "处理人姓名")
    private String dealPersonName;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setRepairPersonName(String repairPersonName) 
    {
        this.repairPersonName = repairPersonName;
    }

    public String getRepairPersonName() 
    {
        return repairPersonName;
    }
    public void setRepairContent(String repairContent) 
    {
        this.repairContent = repairContent;
    }

    public String getRepairContent() 
    {
        return repairContent;
    }
    public void setDeal(String deal) 
    {
        this.deal = deal;
    }

    public String getDeal() 
    {
        return deal;
    }
    public void setDealPersonName(String dealPersonName) 
    {
        this.dealPersonName = dealPersonName;
    }

    public String getDealPersonName() 
    {
        return dealPersonName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("repairPersonName", getRepairPersonName())
            .append("repairContent", getRepairContent())
            .append("deal", getDeal())
            .append("dealPersonName", getDealPersonName())
            .toString();
    }
}
