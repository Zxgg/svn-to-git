package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 投诉意见记录对象 base_opinion_record
 * 
 * @author yzp
 * @date 2020-11-25
 */
public class BaseOpinionRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 投诉人姓名 */
    @Excel(name = "投诉人姓名")
    private String opinionPersonName;

    /** 投诉事项 */
    @Excel(name = "投诉事项")
    private String opinionContent;

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
    public void setOpinionPersonName(String opinionPersonName) 
    {
        this.opinionPersonName = opinionPersonName;
    }

    public String getOpinionPersonName() 
    {
        return opinionPersonName;
    }
    public void setOpinionContent(String opinionContent) 
    {
        this.opinionContent = opinionContent;
    }

    public String getOpinionContent() 
    {
        return opinionContent;
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
            .append("opinionPersonName", getOpinionPersonName())
            .append("opinionContent", getOpinionContent())
            .append("deal", getDeal())
            .append("dealPersonName", getDealPersonName())
            .toString();
    }
}
