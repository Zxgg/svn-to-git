package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 非公经济组织信息对象 base_group_economic
 * 
 * @author yzp
 * @date 2020-11-25
 */
public class BaseGroupEconomic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String businessName;

    /** 非公经济组织企业类别 */
    @Excel(name = "非公经济组织企业类别")
    private String type;

    /** 企业地址 */
    @Excel(name = "企业地址")
    private String address;

    /** 企业联系方式 */
    @Excel(name = "企业联系方式")
    private String tel;

    /** 企业员工数 */
    @Excel(name = "企业员工数")
    private String count;

    /** 工商执照号码(统一社会信用代码) */
    @Excel(name = "工商执照号码(统一社会信用代码)")
    private String socialCode;

    /** 组织机构代码 */
    @Excel(name = "组织机构代码")
    private String orgCode;

    /** 法人证件类型 */
    @Excel(name = "法人证件类型")
    private String legalType;

    /** 法人证件号码 */
    @Excel(name = "法人证件号码")
    private String legalIdcard;

    /** 法人姓名 */
    @Excel(name = "法人姓名")
    private String legalName;

    /** 法人联系方式 */
    @Excel(name = "法人联系方式")
    private String legalTel;

    /** 治保负责人姓名 */
    @Excel(name = "治保负责人姓名")
    private String chargePersonName;

    /** 治保负责人联系方式 */
    @Excel(name = "治保负责人联系方式")
    private String chargePersonTel;

    /** 是否危化企业 */
    @Excel(name = "是否危化企业")
    private String isDanger;

    /** 安全隐患类型 */
    @Excel(name = "安全隐患类型")
    private String dangerType;

    /** 关注程度 */
    @Excel(name = "关注程度")
    private String isFocus;

    /** 是否具备建立党组织条件 */
    @Excel(name = "是否具备建立党组织条件")
    private String isPartyConditions;

    /** 是否有党组织 */
    @Excel(name = "是否有党组织")
    private String isParty;

    /** 党员数量 */
    @Excel(name = "党员数量")
    private String partyMemberCount;

    /** 是否有工会 */
    @Excel(name = "是否有工会")
    private String isUnion;

    /** 工会会员数量 */
    @Excel(name = "工会会员数量")
    private String unionMemberCount;

    /** 是否有共青团组织 */
    @Excel(name = "是否有共青团组织")
    private String isCommunist;

    /** 共青团团员数量 */
    @Excel(name = "共青团团员数量")
    private String communistMemberCount;

    /** 是否有妇联组织 */
    @Excel(name = "是否有妇联组织")
    private String isWomenFed;

    /** 妇联组织成员数量 */
    @Excel(name = "妇联组织成员数量")
    private String womenFedCount;

    /** 经度 */
    private String longitudes;

    /** 纬度 */
    private String latitudes;

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
    public void setBusinessName(String businessName) 
    {
        this.businessName = businessName;
    }

    public String getBusinessName() 
    {
        return businessName;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setCount(String count) 
    {
        this.count = count;
    }

    public String getCount() 
    {
        return count;
    }
    public void setSocialCode(String socialCode) 
    {
        this.socialCode = socialCode;
    }

    public String getSocialCode() 
    {
        return socialCode;
    }
    public void setOrgCode(String orgCode) 
    {
        this.orgCode = orgCode;
    }

    public String getOrgCode() 
    {
        return orgCode;
    }
    public void setLegalType(String legalType) 
    {
        this.legalType = legalType;
    }

    public String getLegalType() 
    {
        return legalType;
    }
    public void setLegalIdcard(String legalIdcard) 
    {
        this.legalIdcard = legalIdcard;
    }

    public String getLegalIdcard() 
    {
        return legalIdcard;
    }
    public void setLegalName(String legalName) 
    {
        this.legalName = legalName;
    }

    public String getLegalName() 
    {
        return legalName;
    }
    public void setLegalTel(String legalTel) 
    {
        this.legalTel = legalTel;
    }

    public String getLegalTel() 
    {
        return legalTel;
    }
    public void setChargePersonName(String chargePersonName) 
    {
        this.chargePersonName = chargePersonName;
    }

    public String getChargePersonName() 
    {
        return chargePersonName;
    }
    public void setChargePersonTel(String chargePersonTel) 
    {
        this.chargePersonTel = chargePersonTel;
    }

    public String getChargePersonTel() 
    {
        return chargePersonTel;
    }
    public void setIsDanger(String isDanger) 
    {
        this.isDanger = isDanger;
    }

    public String getIsDanger() 
    {
        return isDanger;
    }
    public void setDangerType(String dangerType) 
    {
        this.dangerType = dangerType;
    }

    public String getDangerType() 
    {
        return dangerType;
    }
    public void setIsFocus(String isFocus) 
    {
        this.isFocus = isFocus;
    }

    public String getIsFocus() 
    {
        return isFocus;
    }
    public void setIsPartyConditions(String isPartyConditions) 
    {
        this.isPartyConditions = isPartyConditions;
    }

    public String getIsPartyConditions() 
    {
        return isPartyConditions;
    }
    public void setIsParty(String isParty) 
    {
        this.isParty = isParty;
    }

    public String getIsParty() 
    {
        return isParty;
    }
    public void setPartyMemberCount(String partyMemberCount) 
    {
        this.partyMemberCount = partyMemberCount;
    }

    public String getPartyMemberCount() 
    {
        return partyMemberCount;
    }
    public void setIsUnion(String isUnion) 
    {
        this.isUnion = isUnion;
    }

    public String getIsUnion() 
    {
        return isUnion;
    }
    public void setUnionMemberCount(String unionMemberCount) 
    {
        this.unionMemberCount = unionMemberCount;
    }

    public String getUnionMemberCount() 
    {
        return unionMemberCount;
    }
    public void setIsCommunist(String isCommunist) 
    {
        this.isCommunist = isCommunist;
    }

    public String getIsCommunist() 
    {
        return isCommunist;
    }
    public void setCommunistMemberCount(String communistMemberCount) 
    {
        this.communistMemberCount = communistMemberCount;
    }

    public String getCommunistMemberCount() 
    {
        return communistMemberCount;
    }
    public void setIsWomenFed(String isWomenFed) 
    {
        this.isWomenFed = isWomenFed;
    }

    public String getIsWomenFed() 
    {
        return isWomenFed;
    }
    public void setWomenFedCount(String womenFedCount) 
    {
        this.womenFedCount = womenFedCount;
    }

    public String getWomenFedCount() 
    {
        return womenFedCount;
    }
    public void setLongitudes(String longitudes) 
    {
        this.longitudes = longitudes;
    }

    public String getLongitudes() 
    {
        return longitudes;
    }
    public void setLatitudes(String latitudes) 
    {
        this.latitudes = latitudes;
    }

    public String getLatitudes() 
    {
        return latitudes;
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
            .append("businessName", getBusinessName())
            .append("type", getType())
            .append("address", getAddress())
            .append("tel", getTel())
            .append("count", getCount())
            .append("socialCode", getSocialCode())
            .append("orgCode", getOrgCode())
            .append("legalType", getLegalType())
            .append("legalIdcard", getLegalIdcard())
            .append("legalName", getLegalName())
            .append("legalTel", getLegalTel())
            .append("chargePersonName", getChargePersonName())
            .append("chargePersonTel", getChargePersonTel())
            .append("isDanger", getIsDanger())
            .append("dangerType", getDangerType())
            .append("isFocus", getIsFocus())
            .append("isPartyConditions", getIsPartyConditions())
            .append("isParty", getIsParty())
            .append("partyMemberCount", getPartyMemberCount())
            .append("isUnion", getIsUnion())
            .append("unionMemberCount", getUnionMemberCount())
            .append("isCommunist", getIsCommunist())
            .append("communistMemberCount", getCommunistMemberCount())
            .append("isWomenFed", getIsWomenFed())
            .append("womenFedCount", getWomenFedCount())
            .append("longitudes", getLongitudes())
            .append("latitudes", getLatitudes())
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
