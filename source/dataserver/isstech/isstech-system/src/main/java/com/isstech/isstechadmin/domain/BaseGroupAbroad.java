package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 境内外非政府组织对象 base_group_abroad
 * 
 * @author yzp
 * @date 2020-11-25
 */
public class BaseGroupAbroad extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 组织名称 */
    @Excel(name = "组织名称")
    private String organizationName;

    /** 境外非政府组织类别 */
    @Excel(name = "境外非政府组织类别")
    private String organizationType;

    /** 来源地 */
    @Excel(name = "来源地")
    private String source;

    /** 网址 */
    private String website;

    /** 涉及领域 */
    @Excel(name = "涉及领域")
    private String involveField;

    /** 主要联系人 */
    @Excel(name = "主要联系人")
    private String mainLink;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 国籍 */
    @Excel(name = "国籍")
    private String nationality;

    /** 出生年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date brithDate;

    /** 工作职务 */
    @Excel(name = "工作职务")
    private String jobPost;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zipCode;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String tel;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 创建人 */
    @Excel(name = "创建人")
    private String userId;

    /** 创建单位 */
    @Excel(name = "创建单位")
    private String deptId;

    /** 更新人 */
    @Excel(name = "更新人")
    private String uUserId;

    /** 更新单位 */
    @Excel(name = "更新单位")
    private String uDeptId;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOrganizationName(String organizationName) 
    {
        this.organizationName = organizationName;
    }

    public String getOrganizationName() 
    {
        return organizationName;
    }
    public void setOrganizationType(String organizationType) 
    {
        this.organizationType = organizationType;
    }

    public String getOrganizationType() 
    {
        return organizationType;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setInvolveField(String involveField) 
    {
        this.involveField = involveField;
    }

    public String getInvolveField() 
    {
        return involveField;
    }
    public void setMainLink(String mainLink) 
    {
        this.mainLink = mainLink;
    }

    public String getMainLink() 
    {
        return mainLink;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setNationality(String nationality) 
    {
        this.nationality = nationality;
    }

    public String getNationality() 
    {
        return nationality;
    }
    public void setBrithDate(Date brithDate) 
    {
        this.brithDate = brithDate;
    }

    public Date getBrithDate() 
    {
        return brithDate;
    }
    public void setJobPost(String jobPost) 
    {
        this.jobPost = jobPost;
    }

    public String getJobPost() 
    {
        return jobPost;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setZipCode(String zipCode) 
    {
        this.zipCode = zipCode;
    }

    public String getZipCode() 
    {
        return zipCode;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setDeptId(String deptId) 
    {
        this.deptId = deptId;
    }

    public String getDeptId() 
    {
        return deptId;
    }
    public void setuUserId(String uUserId) 
    {
        this.uUserId = uUserId;
    }

    public String getuUserId() 
    {
        return uUserId;
    }
    public void setuDeptId(String uDeptId) 
    {
        this.uDeptId = uDeptId;
    }

    public String getuDeptId() 
    {
        return uDeptId;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("organizationName", getOrganizationName())
            .append("organizationType", getOrganizationType())
            .append("source", getSource())
            .append("website", getWebsite())
            .append("involveField", getInvolveField())
            .append("mainLink", getMainLink())
            .append("sex", getSex())
            .append("nationality", getNationality())
            .append("brithDate", getBrithDate())
            .append("jobPost", getJobPost())
            .append("address", getAddress())
            .append("zipCode", getZipCode())
            .append("tel", getTel())
            .append("phone", getPhone())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("createTime", getCreateTime())
            .append("uUserId", getuUserId())
            .append("uDeptId", getuDeptId())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
