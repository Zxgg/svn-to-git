package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 社区房屋和人员关联对象 base_community_houses_personnel
 * 
 * @author yzp
 * @date 2020-11-30
 */
public class BaseCommunityHousesPersonnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 房屋ID */
    @Excel(name = "房屋ID")
    private String communityHousesId;

    /** 人员ID */
    @Excel(name = "人员ID")
    private String personnelId;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 终止时间 */
    @Excel(name = "终止时间")
    private String endTime;

    /** 居住类型（0未知,1自有房屋,2租住,3寄居,4临时寄居） */
    @Excel(name = "居住类型", readConverterExp = "0=未知,1自有房屋,2租住,3寄居,4临时寄居")
    private String typeOfResidence;

    /** 是否是房主,(0否1是,默认0) */
    @Excel(name = "是否是房主,(0否1是,默认0)")
    private String homeowner;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

    /** 楼栋ID */
    @Excel(name = "楼栋ID")
    private String communityBuildingId;

    /** 住户与房主关系 */
    @Excel(name = "住户与房主关系")
    private String relationshipWithHomeowner;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCommunityHousesId(String communityHousesId) 
    {
        this.communityHousesId = communityHousesId;
    }

    public String getCommunityHousesId() 
    {
        return communityHousesId;
    }
    public void setPersonnelId(String personnelId) 
    {
        this.personnelId = personnelId;
    }

    public String getPersonnelId() 
    {
        return personnelId;
    }
    public void setStartTime(String startTime) 
    {
        this.startTime = startTime;
    }

    public String getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }
    public void setTypeOfResidence(String typeOfResidence) 
    {
        this.typeOfResidence = typeOfResidence;
    }

    public String getTypeOfResidence() 
    {
        return typeOfResidence;
    }
    public void setHomeowner(String homeowner) 
    {
        this.homeowner = homeowner;
    }

    public String getHomeowner() 
    {
        return homeowner;
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
    public void setRelationshipWithHomeowner(String relationshipWithHomeowner) 
    {
        this.relationshipWithHomeowner = relationshipWithHomeowner;
    }

    public String getRelationshipWithHomeowner() 
    {
        return relationshipWithHomeowner;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("communityHousesId", getCommunityHousesId())
            .append("personnelId", getPersonnelId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("typeOfResidence", getTypeOfResidence())
            .append("homeowner", getHomeowner())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("communityId", getCommunityId())
            .append("communityChildId", getCommunityChildId())
            .append("communityBuildingId", getCommunityBuildingId())
            .append("relationshipWithHomeowner", getRelationshipWithHomeowner())
            .toString();
    }
}
