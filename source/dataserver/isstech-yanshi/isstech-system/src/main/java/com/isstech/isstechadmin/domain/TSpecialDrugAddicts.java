package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 吸毒人员信息 对象 t_special_drug_addicts
 * 
 * @author yzp
 * @date 2021-02-22
 */
@Data
public class TSpecialDrugAddicts extends BaseEntity
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

    /** 初次发现时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "初次发现时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date firstFindTime;

    /** 管控情况 */
    @Excel(name = "管控情况")
    private String controllerSituation;

    /** 管控人姓名 */
    @Excel(name = "管控人姓名")
    private String controllerName;

    /** 管控人联系方式 */
    @Excel(name = "管控人联系方式")
    private String controllerTel;

    /** 帮扶情况 */
    @Excel(name = "帮扶情况")
    private String helpSituation;

    /** 帮扶人姓名 */
    @Excel(name = "帮扶人姓名")
    private String helpName;

    /** 帮扶人联系方式 */
    @Excel(name = "帮扶人联系方式")
    private String helpPhone;

    /** 有无犯罪史 */
    @Excel(name = "有无犯罪史")
    private String isCrimeHistory;

    /** 犯罪情况 */
    @Excel(name = "犯罪情况")
    private String crimeSituation;

    /** 初次吸食日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "初次吸食日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date firstSuckDate;

    /** 来源标注 */
    @Excel(name = "来源标注")
    private String sourceMark;

    /** 吸食情况 */
    @Excel(name = "吸食情况")
    private String vsituation;

    /** 吸毒原因 */
    @Excel(name = "吸毒原因")
    private String drugCause;

    /** 吸毒后果 */
    @Excel(name = "吸毒后果")
    private String drugResult;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;



}
