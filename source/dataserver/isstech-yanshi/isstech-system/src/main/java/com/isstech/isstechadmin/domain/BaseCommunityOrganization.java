package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 社区与组织关联对象 base_community_organization
 * 
 * @author yzp
 * @date 2020-12-29
 */
@Data
public class BaseCommunityOrganization extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 社会组织ID */
    @Excel(name = "社会组织ID")
    private String socialOrganizationId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 终止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "终止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

    /** 组织机构类型（01-非公经济组织，02-综治机构，03-群防群治组织，04-社会组织，05-境外非政府组织） */
    @Excel(name = "组织机构类型", readConverterExp = "0=1-非公经济组织，02-综治机构，03-群防群治组织，04-社会组织，05-境外非政府组织")
    private String type;

    /** 是否是本社区物业,(0否1是,默认0) */
    @Excel(name = "是否是本社区物业,(0否1是,默认0)")
    private String propertyManagement;

    /** 社区房屋ID */
    @Excel(name = "社区房屋ID")
    private String communityHousesId;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCommunityId(String communityId) 
    {
        this.communityId = communityId;
    }

    public String getCommunityId() 
    {
        return communityId;
    }
    public void setSocialOrganizationId(String socialOrganizationId) 
    {
        this.socialOrganizationId = socialOrganizationId;
    }

    public String getSocialOrganizationId() 
    {
        return socialOrganizationId;
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
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setCommunityChildId(String communityChildId) 
    {
        this.communityChildId = communityChildId;
    }

    public String getCommunityChildId() 
    {
        return communityChildId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setPropertyManagement(String propertyManagement) 
    {
        this.propertyManagement = propertyManagement;
    }

    public String getPropertyManagement() 
    {
        return propertyManagement;
    }
    public void setCommunityHousesId(String communityHousesId) 
    {
        this.communityHousesId = communityHousesId;
    }

    public String getCommunityHousesId() 
    {
        return communityHousesId;
    }

}
