package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 投票记录 对象 base_vote_record
 * 
 * @author yzp
 * @date 2020-11-25
 */
public class BaseVoteRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 投票人姓名（如果是记名投票） */
    @Excel(name = "投票人姓名", readConverterExp = "如=果是记名投票")
    private String name;

    /** 唯一ID，实名制投票为人员ID，否则为设备ID， */
    @Excel(name = "唯一ID，实名制投票为人员ID，否则为设备ID，")
    private String uuid;

    /** 投票项目ID */
    @Excel(name = "投票项目ID")
    private String voteProjectId;

    /** 选项ID（可以是列表） */
    @Excel(name = "选项ID", readConverterExp = "可=以是列表")
    private String voteProjectOptionId;

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
    public void setUuid(String uuid) 
    {
        this.uuid = uuid;
    }

    public String getUuid() 
    {
        return uuid;
    }
    public void setVoteProjectId(String voteProjectId) 
    {
        this.voteProjectId = voteProjectId;
    }

    public String getVoteProjectId() 
    {
        return voteProjectId;
    }
    public void setVoteProjectOptionId(String voteProjectOptionId) 
    {
        this.voteProjectOptionId = voteProjectOptionId;
    }

    public String getVoteProjectOptionId() 
    {
        return voteProjectOptionId;
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
            .append("uuid", getUuid())
            .append("voteProjectId", getVoteProjectId())
            .append("voteProjectOptionId", getVoteProjectOptionId())
            .toString();
    }
}
