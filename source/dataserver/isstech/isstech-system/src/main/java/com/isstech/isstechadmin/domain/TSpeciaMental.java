package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 易肇事肇祸精神病人 对象 t_specia_mental
 * 
 * @author yzp
 * @date 2021-02-22
 */
@Data
public class TSpeciaMental extends BaseEntity
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

    /** 家庭经济状况 */
    @Excel(name = "家庭经济状况")
    private String economicSituation;

    /** 是否纳入低保 */
    @Excel(name = "是否纳入低保")
    private String isEnjoySubsidy;

    /** 监护人身份证号码 */
    @Excel(name = "监护人身份证号码")
    private String custodyIdCard;

    /** 监护人姓名 */
    @Excel(name = "监护人姓名")
    private String custodyName;

    /** 监护人联系方式 */
    @Excel(name = "监护人联系方式")
    private String custodyPhone;

    /** 初次发病日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "初次发病日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date firstIllnessDate;

    /** 目前诊断类型 */
    @Excel(name = "目前诊断类型")
    private String diagnosisType;

    /** 有无肇事肇祸史 */
    @Excel(name = "有无肇事肇祸史")
    private String isHaveTrouble;

    /** 肇事肇祸次数 */
    @Excel(name = "肇事肇祸次数")
    private String troubleCount;

    /** 上次肇事肇祸日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上次肇事肇祸日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date upTroubleDate;

    /** 目前危险性评估等级 */
    @Excel(name = "目前危险性评估等级")
    private String dangerLevel;

    /** 治疗情况 */
    @Excel(name = "治疗情况")
    private String treatmentSituation;

    /** 治疗医院名称 */
    @Excel(name = "治疗医院名称")
    private String hospitalName;

    /** 实施住院治疗原因 */
    @Excel(name = "实施住院治疗原因")
    private String hospitalizationReason;

    /** 接受康复训练机构名称 */
    @Excel(name = "接受康复训练机构名称")
    private String recoveryName;

    /** 参与管理人员 */
    @Excel(name = "参与管理人员")
    private String adminName;

    /** 帮扶情况 */
    @Excel(name = "帮扶情况")
    private String helpSituation;

    /** 控制情况 */
    @Excel(name = "控制情况")
    private String controlSituation;

    /** 是否收容 */
    @Excel(name = "是否收容")
    private String isAsylum;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;



}
