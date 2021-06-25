package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 闲散流浪人员信息 对象 t_specia_stray
 * 
 * @author yzp
 * @date 2020-12-08
 */
public class TSpeciaStray extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 创建单位 */
    @Excel(name = "创建单位")
    private String deptId;

    /** 更新单位 */
    @Excel(name = "更新单位")
    private String updateDeptId;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 人员ID */
    @Excel(name = "人员ID")
    private String personnelId;

    /** 来源类型 */
    @Excel(name = "来源类型")
    private String sourceType;

    /** 是否有效 */
    @Excel(name = "是否有效")
    private String enable;

    /** 是否属于留守儿童 */
    @Excel(name = "是否属于留守儿童")
    private String isStayChildren;

    /** 是否享受低保补助 */
    @Excel(name = "是否享受低保补助")
    private String isEnjoySubsidy;

    /** 学习状况 */
    @Excel(name = "学习状况")
    private String studySituation;

    /** 未入学原因 */
    @Excel(name = "未入学原因")
    private String unenrolledReason;

    /** 辍学原因 */
    @Excel(name = "辍学原因")
    private String dropReason;

    /** 辍学其他原因 */
    @Excel(name = "辍学其他原因")
    private String dropOtherReason;

    /** 就业情况 */
    @Excel(name = "就业情况")
    private String jobSituation;

    /** 就业情况其他 */
    @Excel(name = "就业情况其他")
    private String jobSituationOther;

    /** 未就业或失业原因 */
    @Excel(name = "未就业或失业原因")
    private String notJobReason;

    /** 未就业或失业其他原因 */
    @Excel(name = "未就业或失业其他原因")
    private String notJobOtherReason;

    /** 监护情况 */
    @Excel(name = "监护情况")
    private String custodySituation;

    /** 监护情况原因 */
    @Excel(name = "监护情况原因")
    private String custodySituationReason;

    /** 是否有不良行为 */
    @Excel(name = "是否有不良行为")
    private String isUnhealthyAction;

    /** 不良行为其他类型 */
    @Excel(name = "不良行为其他类型")
    private String unhealthyActionType;

    /** 是否有违法犯罪经历 */
    @Excel(name = "是否有违法犯罪经历")
    private String isCrimeExperience;

    /** 家庭主要经济来源 */
    @Excel(name = "家庭主要经济来源")
    private String mainSource;

    /** 其他经济来源 */
    @Excel(name = "其他经济来源")
    private String otherSource;

    /** 所属类别 */
    @Excel(name = "所属类别")
    private String type;

    /** 其它需要说明的问题 */
    @Excel(name = "其它需要说明的问题")
    private String otherProblem;

    /** 家庭成员基本情况 */
    @Excel(name = "家庭成员基本情况")
    private String memberSituation;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDeptId(String deptId) 
    {
        this.deptId = deptId;
    }

    public String getDeptId() 
    {
        return deptId;
    }
    public void setUpdateDeptId(String updateDeptId) 
    {
        this.updateDeptId = updateDeptId;
    }

    public String getUpdateDeptId() 
    {
        return updateDeptId;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setPersonnelId(String personnelId) 
    {
        this.personnelId = personnelId;
    }

    public String getPersonnelId() 
    {
        return personnelId;
    }
    public void setSourceType(String sourceType) 
    {
        this.sourceType = sourceType;
    }

    public String getSourceType() 
    {
        return sourceType;
    }
    public void setEnable(String enable) 
    {
        this.enable = enable;
    }

    public String getEnable() 
    {
        return enable;
    }
    public void setIsStayChildren(String isStayChildren) 
    {
        this.isStayChildren = isStayChildren;
    }

    public String getIsStayChildren() 
    {
        return isStayChildren;
    }
    public void setIsEnjoySubsidy(String isEnjoySubsidy) 
    {
        this.isEnjoySubsidy = isEnjoySubsidy;
    }

    public String getIsEnjoySubsidy() 
    {
        return isEnjoySubsidy;
    }
    public void setStudySituation(String studySituation) 
    {
        this.studySituation = studySituation;
    }

    public String getStudySituation() 
    {
        return studySituation;
    }
    public void setUnenrolledReason(String unenrolledReason) 
    {
        this.unenrolledReason = unenrolledReason;
    }

    public String getUnenrolledReason() 
    {
        return unenrolledReason;
    }
    public void setDropReason(String dropReason) 
    {
        this.dropReason = dropReason;
    }

    public String getDropReason() 
    {
        return dropReason;
    }
    public void setDropOtherReason(String dropOtherReason) 
    {
        this.dropOtherReason = dropOtherReason;
    }

    public String getDropOtherReason() 
    {
        return dropOtherReason;
    }
    public void setJobSituation(String jobSituation) 
    {
        this.jobSituation = jobSituation;
    }

    public String getJobSituation() 
    {
        return jobSituation;
    }
    public void setJobSituationOther(String jobSituationOther) 
    {
        this.jobSituationOther = jobSituationOther;
    }

    public String getJobSituationOther() 
    {
        return jobSituationOther;
    }
    public void setNotJobReason(String notJobReason) 
    {
        this.notJobReason = notJobReason;
    }

    public String getNotJobReason() 
    {
        return notJobReason;
    }
    public void setNotJobOtherReason(String notJobOtherReason) 
    {
        this.notJobOtherReason = notJobOtherReason;
    }

    public String getNotJobOtherReason() 
    {
        return notJobOtherReason;
    }
    public void setCustodySituation(String custodySituation) 
    {
        this.custodySituation = custodySituation;
    }

    public String getCustodySituation() 
    {
        return custodySituation;
    }
    public void setCustodySituationReason(String custodySituationReason) 
    {
        this.custodySituationReason = custodySituationReason;
    }

    public String getCustodySituationReason() 
    {
        return custodySituationReason;
    }
    public void setIsUnhealthyAction(String isUnhealthyAction) 
    {
        this.isUnhealthyAction = isUnhealthyAction;
    }

    public String getIsUnhealthyAction() 
    {
        return isUnhealthyAction;
    }
    public void setUnhealthyActionType(String unhealthyActionType) 
    {
        this.unhealthyActionType = unhealthyActionType;
    }

    public String getUnhealthyActionType() 
    {
        return unhealthyActionType;
    }
    public void setIsCrimeExperience(String isCrimeExperience) 
    {
        this.isCrimeExperience = isCrimeExperience;
    }

    public String getIsCrimeExperience() 
    {
        return isCrimeExperience;
    }
    public void setMainSource(String mainSource) 
    {
        this.mainSource = mainSource;
    }

    public String getMainSource() 
    {
        return mainSource;
    }
    public void setOtherSource(String otherSource) 
    {
        this.otherSource = otherSource;
    }

    public String getOtherSource() 
    {
        return otherSource;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setOtherProblem(String otherProblem) 
    {
        this.otherProblem = otherProblem;
    }

    public String getOtherProblem() 
    {
        return otherProblem;
    }
    public void setMemberSituation(String memberSituation) 
    {
        this.memberSituation = memberSituation;
    }

    public String getMemberSituation() 
    {
        return memberSituation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("deptId", getDeptId())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateDeptId", getUpdateDeptId())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("personnelId", getPersonnelId())
            .append("sourceType", getSourceType())
            .append("enable", getEnable())
            .append("remark", getRemark())
            .append("isStayChildren", getIsStayChildren())
            .append("isEnjoySubsidy", getIsEnjoySubsidy())
            .append("studySituation", getStudySituation())
            .append("unenrolledReason", getUnenrolledReason())
            .append("dropReason", getDropReason())
            .append("dropOtherReason", getDropOtherReason())
            .append("jobSituation", getJobSituation())
            .append("jobSituationOther", getJobSituationOther())
            .append("notJobReason", getNotJobReason())
            .append("notJobOtherReason", getNotJobOtherReason())
            .append("custodySituation", getCustodySituation())
            .append("custodySituationReason", getCustodySituationReason())
            .append("isUnhealthyAction", getIsUnhealthyAction())
            .append("unhealthyActionType", getUnhealthyActionType())
            .append("isCrimeExperience", getIsCrimeExperience())
            .append("mainSource", getMainSource())
            .append("otherSource", getOtherSource())
            .append("type", getType())
            .append("otherProblem", getOtherProblem())
            .append("memberSituation", getMemberSituation())
            .toString();
    }
}
