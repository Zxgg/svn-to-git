package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 投票项的选项 对象 base_vote_option
 * 
 * @author yzp
 * @date 2020-11-25
 */
public class BaseVoteOption extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 排列序号 */
    @Excel(name = "排列序号")
    private Long sort;

    /** 状态(是否正常使用） */
    @Excel(name = "状态(是否正常使用）")
    private String status;

    /** 投票项目ID */
    @Excel(name = "投票项目ID")
    private String voteProjectId;

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
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setVoteProjectId(String voteProjectId) 
    {
        this.voteProjectId = voteProjectId;
    }

    public String getVoteProjectId() 
    {
        return voteProjectId;
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
            .append("sort", getSort())
            .append("status", getStatus())
            .append("voteProjectId", getVoteProjectId())
            .toString();
    }
}
