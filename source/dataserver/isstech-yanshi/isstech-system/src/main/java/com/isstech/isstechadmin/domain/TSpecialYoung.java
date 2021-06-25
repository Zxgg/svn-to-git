package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 重点青少年 对象 t_special_young
 * 
 * @author yzp
 * @date 2020-12-08
 */
public class TSpecialYoung extends BaseEntity
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

    /** 人员类型 */
    @Excel(name = "人员类型")
    private String youngType;

    /** 家庭情况 */
    @Excel(name = "家庭情况")
    private String familyType;

    /** 监护人身份证号 */
    @Excel(name = "监护人身份证号")
    private String guardianIdcard;

    /** 监护人姓名 */
    @Excel(name = "监护人姓名")
    private String guardianName;

    /** 与监护人关系 */
    @Excel(name = "与监护人关系")
    private String isGuardianRel;

    /** 监护人联系方式 */
    @Excel(name = "监护人联系方式")
    private String guardianTel;

    /** 监护人居住详址 */
    @Excel(name = "监护人居住详址")
    private String guardianAddressDetail;

    /** 是否违法犯罪 */
    @Excel(name = "是否违法犯罪")
    private String isCrime;

    /** 违法犯罪情况 */
    @Excel(name = "违法犯罪情况")
    private String crimeCase;

    /** 帮扶人姓名 */
    @Excel(name = "帮扶人姓名")
    private String helperName;

    /** 帮扶人联系方式 */
    @Excel(name = "帮扶人联系方式")
    private String helperTel;

    /** 帮扶人手段 */
    @Excel(name = "帮扶人手段")
    private String helperMeans;

    /** 帮扶人情况 */
    @Excel(name = "帮扶人情况")
    private String helperCase;

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
    public void setYoungType(String youngType) 
    {
        this.youngType = youngType;
    }

    public String getYoungType() 
    {
        return youngType;
    }
    public void setFamilyType(String familyType) 
    {
        this.familyType = familyType;
    }

    public String getFamilyType() 
    {
        return familyType;
    }
    public void setGuardianIdcard(String guardianIdcard) 
    {
        this.guardianIdcard = guardianIdcard;
    }

    public String getGuardianIdcard() 
    {
        return guardianIdcard;
    }
    public void setGuardianName(String guardianName) 
    {
        this.guardianName = guardianName;
    }

    public String getGuardianName() 
    {
        return guardianName;
    }
    public void setIsGuardianRel(String isGuardianRel) 
    {
        this.isGuardianRel = isGuardianRel;
    }

    public String getIsGuardianRel() 
    {
        return isGuardianRel;
    }
    public void setGuardianTel(String guardianTel) 
    {
        this.guardianTel = guardianTel;
    }

    public String getGuardianTel() 
    {
        return guardianTel;
    }
    public void setGuardianAddressDetail(String guardianAddressDetail) 
    {
        this.guardianAddressDetail = guardianAddressDetail;
    }

    public String getGuardianAddressDetail() 
    {
        return guardianAddressDetail;
    }
    public void setIsCrime(String isCrime) 
    {
        this.isCrime = isCrime;
    }

    public String getIsCrime() 
    {
        return isCrime;
    }
    public void setCrimeCase(String crimeCase) 
    {
        this.crimeCase = crimeCase;
    }

    public String getCrimeCase() 
    {
        return crimeCase;
    }
    public void setHelperName(String helperName) 
    {
        this.helperName = helperName;
    }

    public String getHelperName() 
    {
        return helperName;
    }
    public void setHelperTel(String helperTel) 
    {
        this.helperTel = helperTel;
    }

    public String getHelperTel() 
    {
        return helperTel;
    }
    public void setHelperMeans(String helperMeans) 
    {
        this.helperMeans = helperMeans;
    }

    public String getHelperMeans() 
    {
        return helperMeans;
    }
    public void setHelperCase(String helperCase) 
    {
        this.helperCase = helperCase;
    }

    public String getHelperCase() 
    {
        return helperCase;
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
            .append("youngType", getYoungType())
            .append("familyType", getFamilyType())
            .append("guardianIdcard", getGuardianIdcard())
            .append("guardianName", getGuardianName())
            .append("isGuardianRel", getIsGuardianRel())
            .append("guardianTel", getGuardianTel())
            .append("guardianAddressDetail", getGuardianAddressDetail())
            .append("isCrime", getIsCrime())
            .append("crimeCase", getCrimeCase())
            .append("helperName", getHelperName())
            .append("helperTel", getHelperTel())
            .append("helperMeans", getHelperMeans())
            .append("helperCase", getHelperCase())
            .toString();
    }
}
