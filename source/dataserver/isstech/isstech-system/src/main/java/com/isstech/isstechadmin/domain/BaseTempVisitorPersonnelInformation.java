package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 临时访客人员信息对象 base_temp_visitor_personnel_information
 * 
 * @author isstech
 * @date 2020-11-23
 */
public class BaseTempVisitorPersonnelInformation extends BaseEntity
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

    /** 性别（0男 1女 2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
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
            .toString();
    }
}
