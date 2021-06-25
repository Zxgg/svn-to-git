package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 投票项目信息 对象 base_vote_project
 * 
 * @author yzp
 * @date 2020-11-25
 */
public class BaseVoteProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 投票类型 */
    @Excel(name = "投票类型")
    private String type;

    /** 是否发布 */
    @Excel(name = "是否发布")
    private String toShow;

    /** 时间发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date toShowTime;

    /** 投票开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投票开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 投票截至时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投票截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 投票状态(0是创建，1是正在进行投票，2是投票截至) */
    @Excel(name = "投票状态(0是创建，1是正在进行投票，2是投票截至)")
    private String stat;

    /** 状态(是否正常使用） */
    @Excel(name = "状态(是否正常使用）")
    private String status;

    /** 投票结果（可以是多个选项） */
    @Excel(name = "投票结果", readConverterExp = "可=以是多个选项")
    private String result;

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
    public void setToShow(String toShow)
    {
        this.toShow = toShow;
    }

    public String getToShow()
    {
        return toShow;
    }
    public void setToShowTime(Date toShowTime)
    {
        this.toShowTime = toShowTime;
    }

    public Date getToShowTime()
    {
        return toShowTime;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setStat(String stat)
    {
        this.stat = stat;
    }

    public String getStat()
    {
        return stat;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
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
            .append("toShow", getToShow())
            .append("toShowTime", getToShowTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("stat", getStat())
            .append("status", getStatus())
            .append("result", getResult())
            .toString();
    }
}
