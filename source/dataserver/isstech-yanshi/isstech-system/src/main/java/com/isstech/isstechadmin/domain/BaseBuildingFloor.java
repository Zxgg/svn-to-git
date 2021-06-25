package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 楼栋楼层 只做记录用，房屋数据直接使用楼层号对象 base_building_floor
 * 
 * @author yzp
 * @date 2020-11-30
 */
public class BaseBuildingFloor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

    /** 社区楼栋ID */
    @Excel(name = "社区楼栋ID")
    private String communityBuildingId;

    /** 楼层号 */
    @Excel(name = "楼层号")
    private String num;

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
    public void setCommunityBuildingId(String communityBuildingId) 
    {
        this.communityBuildingId = communityBuildingId;
    }

    public String getCommunityBuildingId() 
    {
        return communityBuildingId;
    }
    public void setNum(String num) 
    {
        this.num = num;
    }

    public String getNum() 
    {
        return num;
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
            .append("communityId", getCommunityId())
            .append("communityChildId", getCommunityChildId())
            .append("communityBuildingId", getCommunityBuildingId())
            .append("num", getNum())
            .toString();
    }
}
