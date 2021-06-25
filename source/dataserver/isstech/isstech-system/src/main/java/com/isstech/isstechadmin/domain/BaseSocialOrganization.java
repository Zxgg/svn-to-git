package com.isstech.isstechadmin.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 社会组织信息对象 base_social_organization
 * 
 * @author yzp
 * @date 2020-11-25
 */
public class BaseSocialOrganization extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 统一社会信用代码(18位信用码) */
    @Excel(name = "统一社会信用代码(18位信用码)")
    private String unifiedSocialCreditCode;

    /** 登记证号(15位登记证号) */
    @Excel(name = "登记证号(15位登记证号)")
    private String registrationCode;

    /** 注册地址 */
    @Excel(name = "注册地址")
    private String registeredAddress;

    /** 成立日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成立日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateOfEstablishment;

    /** 法人代表 */
    @Excel(name = "法人代表")
    private String legalRepresentative;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String nameOfPersonInCharge;

    /** 负责人身份证(18位身份证号码) */
    @Excel(name = "负责人身份证(18位身份证号码)")
    private String identificationNumberOfPersonInCharge;

    /** 负责任性别（0男 1女 2未知） */
    @Excel(name = "负责任性别", readConverterExp = "0=男,1=女,2=未知")
    private String sexOfPersonInCharge;

    /** 负责人联系方式 */
    @Excel(name = "负责人联系方式")
    private String phonenumOfPersonInCharge;

    /** 负责任联系地址 */
    @Excel(name = "负责任联系地址")
    private String addressOfPersonInCharge;

    /** 治安负责人姓名 */
    @Excel(name = "治安负责人姓名")
    private String securityResponsibleName;

    /** 治安负责人联系方式 */
    @Excel(name = "治安负责人联系方式")
    private String securityResponsiblePhone;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;

    /** 有境外背景关系(0为没有1为有，默认0) */
    @Excel(name = "有境外背景关系(0为没有1为有，默认0)")
    private String haveForeignRelationship;

    /** 注册资金 */
    @Excel(name = "注册资金")
    private BigDecimal registeredCapital;

    /** 员工人数(比如“500-700人”) */
    @Excel(name = "员工人数(比如“500-700人”)")
    private String numberOfEmployees;

    /** 社会组织类型 */
    @Excel(name = "社会组织类型")
    private String socialOrganizationType;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 关注程度 */
    @Excel(name = "关注程度")
    private Long caringLevel;

    /** 是否具备条件建立中国共产党支部,(0否1是,默认0) */
    @Excel(name = "是否具备条件建立中国共产党支部,(0否1是,默认0)")
    private String canCreateChineseCommunistPartyBranch;

    /** 是否有中国共产党支部,(0否1是,默认0) */
    @Excel(name = "是否有中国共产党支部,(0否1是,默认0)")
    private String haveChineseCommunistPartyBranch;

    /** 共产党支部人员数量 */
    @Excel(name = "共产党支部人员数量")
    private Long chineseCommunistPartyBranchPeopleNum;

    /** 是否有公会,(0否1是,默认0) */
    @Excel(name = "是否有公会,(0否1是,默认0)")
    private String haveUnion;

    /** 公会人数 */
    @Excel(name = "公会人数")
    private Long unionPeopleNum;

    /** 是否有共青团,(0否1是,默认0) */
    @Excel(name = "是否有共青团,(0否1是,默认0)")
    private String haveCommunistYouthLeague;

    /** 共青团员数量 */
    @Excel(name = "共青团员数量")
    private Long communistYouthLeaguePeopleNum;

    /** 是否有妇联组织,(0否1是,默认0) */
    @Excel(name = "是否有妇联组织,(0否1是,默认0)")
    private String haveWomenSFederation;

    /** 妇联组织成员人数 */
    @Excel(name = "妇联组织成员人数")
    private Long womenSFederationPeopleNum;

    /** 资金来源信息 */
    private String sourcesOfFunds;

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
    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) 
    {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }

    public String getUnifiedSocialCreditCode() 
    {
        return unifiedSocialCreditCode;
    }
    public void setRegistrationCode(String registrationCode) 
    {
        this.registrationCode = registrationCode;
    }

    public String getRegistrationCode() 
    {
        return registrationCode;
    }
    public void setRegisteredAddress(String registeredAddress) 
    {
        this.registeredAddress = registeredAddress;
    }

    public String getRegisteredAddress() 
    {
        return registeredAddress;
    }
    public void setDateOfEstablishment(Date dateOfEstablishment) 
    {
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public Date getDateOfEstablishment() 
    {
        return dateOfEstablishment;
    }
    public void setLegalRepresentative(String legalRepresentative) 
    {
        this.legalRepresentative = legalRepresentative;
    }

    public String getLegalRepresentative() 
    {
        return legalRepresentative;
    }
    public void setNameOfPersonInCharge(String nameOfPersonInCharge) 
    {
        this.nameOfPersonInCharge = nameOfPersonInCharge;
    }

    public String getNameOfPersonInCharge() 
    {
        return nameOfPersonInCharge;
    }
    public void setIdentificationNumberOfPersonInCharge(String identificationNumberOfPersonInCharge) 
    {
        this.identificationNumberOfPersonInCharge = identificationNumberOfPersonInCharge;
    }

    public String getIdentificationNumberOfPersonInCharge() 
    {
        return identificationNumberOfPersonInCharge;
    }
    public void setSexOfPersonInCharge(String sexOfPersonInCharge) 
    {
        this.sexOfPersonInCharge = sexOfPersonInCharge;
    }

    public String getSexOfPersonInCharge() 
    {
        return sexOfPersonInCharge;
    }
    public void setPhonenumOfPersonInCharge(String phonenumOfPersonInCharge) 
    {
        this.phonenumOfPersonInCharge = phonenumOfPersonInCharge;
    }

    public String getPhonenumOfPersonInCharge() 
    {
        return phonenumOfPersonInCharge;
    }
    public void setAddressOfPersonInCharge(String addressOfPersonInCharge) 
    {
        this.addressOfPersonInCharge = addressOfPersonInCharge;
    }

    public String getAddressOfPersonInCharge() 
    {
        return addressOfPersonInCharge;
    }
    public void setSecurityResponsibleName(String securityResponsibleName) 
    {
        this.securityResponsibleName = securityResponsibleName;
    }

    public String getSecurityResponsibleName() 
    {
        return securityResponsibleName;
    }
    public void setSecurityResponsiblePhone(String securityResponsiblePhone) 
    {
        this.securityResponsiblePhone = securityResponsiblePhone;
    }

    public String getSecurityResponsiblePhone() 
    {
        return securityResponsiblePhone;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }
    public void setHaveForeignRelationship(String haveForeignRelationship) 
    {
        this.haveForeignRelationship = haveForeignRelationship;
    }

    public String getHaveForeignRelationship() 
    {
        return haveForeignRelationship;
    }
    public void setRegisteredCapital(BigDecimal registeredCapital) 
    {
        this.registeredCapital = registeredCapital;
    }

    public BigDecimal getRegisteredCapital() 
    {
        return registeredCapital;
    }
    public void setNumberOfEmployees(String numberOfEmployees) 
    {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getNumberOfEmployees() 
    {
        return numberOfEmployees;
    }
    public void setSocialOrganizationType(String socialOrganizationType) 
    {
        this.socialOrganizationType = socialOrganizationType;
    }

    public String getSocialOrganizationType() 
    {
        return socialOrganizationType;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCaringLevel(Long caringLevel) 
    {
        this.caringLevel = caringLevel;
    }

    public Long getCaringLevel() 
    {
        return caringLevel;
    }
    public void setCanCreateChineseCommunistPartyBranch(String canCreateChineseCommunistPartyBranch) 
    {
        this.canCreateChineseCommunistPartyBranch = canCreateChineseCommunistPartyBranch;
    }

    public String getCanCreateChineseCommunistPartyBranch() 
    {
        return canCreateChineseCommunistPartyBranch;
    }
    public void setHaveChineseCommunistPartyBranch(String haveChineseCommunistPartyBranch) 
    {
        this.haveChineseCommunistPartyBranch = haveChineseCommunistPartyBranch;
    }

    public String getHaveChineseCommunistPartyBranch() 
    {
        return haveChineseCommunistPartyBranch;
    }
    public void setChineseCommunistPartyBranchPeopleNum(Long chineseCommunistPartyBranchPeopleNum) 
    {
        this.chineseCommunistPartyBranchPeopleNum = chineseCommunistPartyBranchPeopleNum;
    }

    public Long getChineseCommunistPartyBranchPeopleNum() 
    {
        return chineseCommunistPartyBranchPeopleNum;
    }
    public void setHaveUnion(String haveUnion) 
    {
        this.haveUnion = haveUnion;
    }

    public String getHaveUnion() 
    {
        return haveUnion;
    }
    public void setUnionPeopleNum(Long unionPeopleNum) 
    {
        this.unionPeopleNum = unionPeopleNum;
    }

    public Long getUnionPeopleNum() 
    {
        return unionPeopleNum;
    }
    public void setHaveCommunistYouthLeague(String haveCommunistYouthLeague) 
    {
        this.haveCommunistYouthLeague = haveCommunistYouthLeague;
    }

    public String getHaveCommunistYouthLeague() 
    {
        return haveCommunistYouthLeague;
    }
    public void setCommunistYouthLeaguePeopleNum(Long communistYouthLeaguePeopleNum) 
    {
        this.communistYouthLeaguePeopleNum = communistYouthLeaguePeopleNum;
    }

    public Long getCommunistYouthLeaguePeopleNum() 
    {
        return communistYouthLeaguePeopleNum;
    }
    public void setHaveWomenSFederation(String haveWomenSFederation) 
    {
        this.haveWomenSFederation = haveWomenSFederation;
    }

    public String getHaveWomenSFederation() 
    {
        return haveWomenSFederation;
    }
    public void setWomenSFederationPeopleNum(Long womenSFederationPeopleNum) 
    {
        this.womenSFederationPeopleNum = womenSFederationPeopleNum;
    }

    public Long getWomenSFederationPeopleNum() 
    {
        return womenSFederationPeopleNum;
    }
    public void setSourcesOfFunds(String sourcesOfFunds) 
    {
        this.sourcesOfFunds = sourcesOfFunds;
    }

    public String getSourcesOfFunds() 
    {
        return sourcesOfFunds;
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
            .append("unifiedSocialCreditCode", getUnifiedSocialCreditCode())
            .append("registrationCode", getRegistrationCode())
            .append("registeredAddress", getRegisteredAddress())
            .append("dateOfEstablishment", getDateOfEstablishment())
            .append("legalRepresentative", getLegalRepresentative())
            .append("nameOfPersonInCharge", getNameOfPersonInCharge())
            .append("identificationNumberOfPersonInCharge", getIdentificationNumberOfPersonInCharge())
            .append("sexOfPersonInCharge", getSexOfPersonInCharge())
            .append("phonenumOfPersonInCharge", getPhonenumOfPersonInCharge())
            .append("addressOfPersonInCharge", getAddressOfPersonInCharge())
            .append("securityResponsibleName", getSecurityResponsibleName())
            .append("securityResponsiblePhone", getSecurityResponsiblePhone())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("haveForeignRelationship", getHaveForeignRelationship())
            .append("registeredCapital", getRegisteredCapital())
            .append("numberOfEmployees", getNumberOfEmployees())
            .append("socialOrganizationType", getSocialOrganizationType())
            .append("status", getStatus())
            .append("caringLevel", getCaringLevel())
            .append("canCreateChineseCommunistPartyBranch", getCanCreateChineseCommunistPartyBranch())
            .append("haveChineseCommunistPartyBranch", getHaveChineseCommunistPartyBranch())
            .append("chineseCommunistPartyBranchPeopleNum", getChineseCommunistPartyBranchPeopleNum())
            .append("haveUnion", getHaveUnion())
            .append("unionPeopleNum", getUnionPeopleNum())
            .append("haveCommunistYouthLeague", getHaveCommunistYouthLeague())
            .append("communistYouthLeaguePeopleNum", getCommunistYouthLeaguePeopleNum())
            .append("haveWomenSFederation", getHaveWomenSFederation())
            .append("womenSFederationPeopleNum", getWomenSFederationPeopleNum())
            .append("sourcesOfFunds", getSourcesOfFunds())
            .toString();
    }
}
