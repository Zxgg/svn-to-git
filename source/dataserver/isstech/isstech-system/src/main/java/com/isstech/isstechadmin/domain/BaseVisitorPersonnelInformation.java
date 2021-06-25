package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 访客人员信息对象 base_visitor_personnel_information
 * 
 * @author yzp
 * @date 2020-11-26
 */
public class BaseVisitorPersonnelInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 身份证号码(18位身份证号码) */
    @Excel(name = "身份证号码(18位身份证号码)")
    private String identificationNumber;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 特殊人员,(0否1是,默认0) */
    private String special;

    /** 管控限制人员,(0否1是,默认0) */
    private String limited;

    /** 关爱人员,(0否1是,默认0) */
    private String caring;

    /** 照片列表，多张照片以逗号隔开，默认显示第一张 */
    @Excel(name = "照片列表，多张照片以逗号隔开，默认显示第一张")
    private String pics;

    /** 是否已经审核确认,(0否1是,默认0) */
    @Excel(name = "是否已经审核确认,(0否1是,默认0)")
    private String checked;

    /** 审核人员ID，一般是管理员或者被访人 */
    private String checkedPersonId;

    /** 审核人员名称 */
    @Excel(name = "审核人员名称")
    private String checkedPersonName;

    /** 审核时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date checkedTime;

    /** 被拜访人ID */
    private String targetPersonId;

    /** 被拜访人名称 */
    @Excel(name = "被拜访人名称")
    private String targetPersonName;

    /** 被拜访人社区ID */
    private String targetPersonCommunityId;

    /** 小区ID */
    private String targetPersonCommunityChildId;

    /** 被拜访人房屋ID */
    private String targetPersonHouseId;

    /** 被拜访人房屋具体地址 */
    @Excel(name = "被拜访人房屋具体地址")
    private String targetPersonHouseAddress;

    /** 拜访开始时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @Excel(name = "拜访开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date visitStartTime;

    /** 拜访结束时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @Excel(name = "拜访结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date visitEndTime;

    /** 访客的图片是否已经加入到闸机许可中，0否1是，默认为0 */
    @Excel(name = "访客的图片是否已经加入到闸机许可中，0否1是，默认为0")
    private String visitorPicPermit;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setIdentificationNumber(String identificationNumber) 
    {
        this.identificationNumber = identificationNumber;
    }

    public String getIdentificationNumber() 
    {
        return identificationNumber;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setSpecial(String special) 
    {
        this.special = special;
    }

    public String getSpecial() 
    {
        return special;
    }
    public void setLimited(String limited) 
    {
        this.limited = limited;
    }

    public String getLimited() 
    {
        return limited;
    }
    public void setCaring(String caring) 
    {
        this.caring = caring;
    }

    public String getCaring() 
    {
        return caring;
    }
    public void setPics(String pics) 
    {
        this.pics = pics;
    }

    public String getPics() 
    {
        return pics;
    }
    public void setChecked(String checked) 
    {
        this.checked = checked;
    }

    public String getChecked() 
    {
        return checked;
    }
    public void setCheckedPersonId(String checkedPersonId) 
    {
        this.checkedPersonId = checkedPersonId;
    }

    public String getCheckedPersonId() 
    {
        return checkedPersonId;
    }
    public void setCheckedPersonName(String checkedPersonName) 
    {
        this.checkedPersonName = checkedPersonName;
    }

    public String getCheckedPersonName() 
    {
        return checkedPersonName;
    }
    public void setCheckedTime(Date checkedTime) 
    {
        this.checkedTime = checkedTime;
    }

    public Date getCheckedTime() 
    {
        return checkedTime;
    }
    public void setTargetPersonId(String targetPersonId) 
    {
        this.targetPersonId = targetPersonId;
    }

    public String getTargetPersonId() 
    {
        return targetPersonId;
    }
    public void setTargetPersonName(String targetPersonName) 
    {
        this.targetPersonName = targetPersonName;
    }

    public String getTargetPersonName() 
    {
        return targetPersonName;
    }
    public void setTargetPersonCommunityId(String targetPersonCommunityId) 
    {
        this.targetPersonCommunityId = targetPersonCommunityId;
    }

    public String getTargetPersonCommunityId() 
    {
        return targetPersonCommunityId;
    }
    public void setTargetPersonCommunityChildId(String targetPersonCommunityChildId) 
    {
        this.targetPersonCommunityChildId = targetPersonCommunityChildId;
    }

    public String getTargetPersonCommunityChildId() 
    {
        return targetPersonCommunityChildId;
    }
    public void setTargetPersonHouseId(String targetPersonHouseId) 
    {
        this.targetPersonHouseId = targetPersonHouseId;
    }

    public String getTargetPersonHouseId() 
    {
        return targetPersonHouseId;
    }
    public void setTargetPersonHouseAddress(String targetPersonHouseAddress) 
    {
        this.targetPersonHouseAddress = targetPersonHouseAddress;
    }

    public String getTargetPersonHouseAddress() 
    {
        return targetPersonHouseAddress;
    }
    public void setVisitStartTime(Date visitStartTime) 
    {
        this.visitStartTime = visitStartTime;
    }

    public Date getVisitStartTime() 
    {
        return visitStartTime;
    }
    public void setVisitEndTime(Date visitEndTime) 
    {
        this.visitEndTime = visitEndTime;
    }

    public Date getVisitEndTime() 
    {
        return visitEndTime;
    }
    public void setVisitorPicPermit(String visitorPicPermit) 
    {
        this.visitorPicPermit = visitorPicPermit;
    }

    public String getVisitorPicPermit() 
    {
        return visitorPicPermit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("identificationNumber", getIdentificationNumber())
            .append("phoneNumber", getPhoneNumber())
            .append("sex", getSex())
            .append("special", getSpecial())
            .append("limited", getLimited())
            .append("caring", getCaring())
            .append("pics", getPics())
            .append("checked", getChecked())
            .append("checkedPersonId", getCheckedPersonId())
            .append("checkedPersonName", getCheckedPersonName())
            .append("checkedTime", getCheckedTime())
            .append("targetPersonId", getTargetPersonId())
            .append("targetPersonName", getTargetPersonName())
            .append("targetPersonCommunityId", getTargetPersonCommunityId())
            .append("targetPersonCommunityChildId", getTargetPersonCommunityChildId())
            .append("targetPersonHouseId", getTargetPersonHouseId())
            .append("targetPersonHouseAddress", getTargetPersonHouseAddress())
            .append("visitStartTime", getVisitStartTime())
            .append("visitEndTime", getVisitEndTime())
            .append("visitorPicPermit", getVisitorPicPermit())
            .toString();
    }
}
