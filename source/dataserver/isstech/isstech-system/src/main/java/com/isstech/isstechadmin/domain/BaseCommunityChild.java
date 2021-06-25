package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 小区信息  对象 base_community_child
 * 
 * @author yzp
 * @date 2020-11-26
 */
public class BaseCommunityChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 社区ID */
//    @Excel(name = "社区ID")
    private String communityId;

    /** 小区类型 */
    @Excel(name = "小区类型")
    private String type;

    /** 楼宇数量 */
    @Excel(name = "楼宇数量")
    private Integer buildingNum;

    /** 房屋数量 */
    @Excel(name = "房屋数量")
    private Integer houseNum;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailedAddress;

    /** 行政区划 */
    @Excel(name = "行政区划")
    private String code;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registeredTime;

    /** 开始建设时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始建设时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buildTime;

    /** 竣工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "竣工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buildEndTime;

    /** 建设单位 */
    @Excel(name = "建设单位")
    private String buildUnit;

    /** 施工单位 */
    @Excel(name = "施工单位")
    private String constructionUnit;

    /** 监理单位 */
    private String supervisionUnit;

    /** 部门组织 */
    private String deptId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

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
    public void setCommunityId(String communityId) 
    {
        this.communityId = communityId;
    }

    public String getCommunityId() 
    {
        return communityId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setBuildingNum(Integer buildingNum) 
    {
        this.buildingNum = buildingNum;
    }

    public Integer getBuildingNum() 
    {
        return buildingNum;
    }
    public void setHouseNum(Integer houseNum) 
    {
        this.houseNum = houseNum;
    }

    public Integer getHouseNum() 
    {
        return houseNum;
    }
    public void setDetailedAddress(String detailedAddress) 
    {
        this.detailedAddress = detailedAddress;
    }

    public String getDetailedAddress() 
    {
        return detailedAddress;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setRegisteredTime(Date registeredTime) 
    {
        this.registeredTime = registeredTime;
    }

    public Date getRegisteredTime() 
    {
        return registeredTime;
    }
    public void setBuildTime(Date buildTime) 
    {
        this.buildTime = buildTime;
    }

    public Date getBuildTime() 
    {
        return buildTime;
    }
    public void setBuildEndTime(Date buildEndTime) 
    {
        this.buildEndTime = buildEndTime;
    }

    public Date getBuildEndTime() 
    {
        return buildEndTime;
    }
    public void setBuildUnit(String buildUnit) 
    {
        this.buildUnit = buildUnit;
    }

    public String getBuildUnit() 
    {
        return buildUnit;
    }
    public void setConstructionUnit(String constructionUnit) 
    {
        this.constructionUnit = constructionUnit;
    }

    public String getConstructionUnit() 
    {
        return constructionUnit;
    }
    public void setSupervisionUnit(String supervisionUnit) 
    {
        this.supervisionUnit = supervisionUnit;
    }

    public String getSupervisionUnit() 
    {
        return supervisionUnit;
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
            .append("communityId", getCommunityId())
            .append("type", getType())
            .append("buildingNum", getBuildingNum())
            .append("houseNum", getHouseNum())
            .append("detailedAddress", getDetailedAddress())
            .append("code", getCode())
            .append("registeredTime", getRegisteredTime())
            .append("buildTime", getBuildTime())
            .append("buildEndTime", getBuildEndTime())
            .append("buildUnit", getBuildUnit())
            .append("constructionUnit", getConstructionUnit())
            .append("supervisionUnit", getSupervisionUnit())
            .toString();
    }
}
