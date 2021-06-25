package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 社区事件 对象 t_community_event
 * 
 * @author yzp
 * @date 2020-12-12
 */
public class TCommunityEvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 名称（根据社区事件类型变化） */
    @Excel(name = "名称", readConverterExp = "根=据社区事件类型变化")
    private String name;

    /** 事件类型表 */
    @Excel(name = "事件类型表")
    private String type;

    /** 关联人员ID，可以是多个 */
    @Excel(name = "关联人员ID，可以是多个")
    private String personid;

    /** 车辆ID，可以是多个 */
    @Excel(name = "车辆ID，可以是多个")
    private String vehicleid;

    /** 告警层级，0最小 */
    @Excel(name = "告警层级，0最小")
    private Long alarmtype;

    /** 事件具体内容 */
    @Excel(name = "事件具体内容")
    private String content;

    /** 是否处理（0否1是，默认为1） */
    @Excel(name = "是否处理", readConverterExp = "0=否1是，默认为1")
    private String processed;

    /** 处理情况 */
    @Excel(name = "处理情况")
    private String processedSituation;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setPersonid(String personid) 
    {
        this.personid = personid;
    }

    public String getPersonid() 
    {
        return personid;
    }
    public void setVehicleid(String vehicleid) 
    {
        this.vehicleid = vehicleid;
    }

    public String getVehicleid() 
    {
        return vehicleid;
    }
    public void setAlarmtype(Long alarmtype) 
    {
        this.alarmtype = alarmtype;
    }

    public Long getAlarmtype() 
    {
        return alarmtype;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setProcessed(String processed) 
    {
        this.processed = processed;
    }

    public String getProcessed() 
    {
        return processed;
    }
    public void setProcessedSituation(String processedSituation) 
    {
        this.processedSituation = processedSituation;
    }

    public String getProcessedSituation() 
    {
        return processedSituation;
    }
    public void setCommunityId(String communityId) 
    {
        this.communityId = communityId;
    }

    public String getCommunityId() 
    {
        return communityId;
    }
    public void setCommunityChildId(String communityChildId) 
    {
        this.communityChildId = communityChildId;
    }

    public String getCommunityChildId() 
    {
        return communityChildId;
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
            .append("name", getName())
            .append("type", getType())
            .append("personid", getPersonid())
            .append("vehicleid", getVehicleid())
            .append("alarmtype", getAlarmtype())
            .append("content", getContent())
            .append("processed", getProcessed())
            .append("processedSituation", getProcessedSituation())
            .append("communityId", getCommunityId())
            .append("communityChildId", getCommunityChildId())
            .toString();
    }
}
