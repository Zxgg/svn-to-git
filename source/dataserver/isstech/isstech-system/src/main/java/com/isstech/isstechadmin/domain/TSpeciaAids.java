package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 艾滋病人 对象 t_specia_aids
 * 
 * @author yzp
 * @date 2021-02-22
 */
@Data
public class TSpeciaAids extends BaseEntity
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

    /** 感染途径 */
    @Excel(name = "感染途径")
    private String infectedWay;

    /** 有无违法犯罪史 */
    @Excel(name = "有无违法犯罪史")
    private String isHaveCrime;

    /** 违法犯罪情况 */
    @Excel(name = "违法犯罪情况")
    private String crimeSituation;

    /** 案件类别 */
    @Excel(name = "案件类别")
    private String caseType;

    /** 关注类型 */
    @Excel(name = "关注类型")
    private String followType;

    /** 帮扶情况 */
    @Excel(name = "帮扶情况")
    private String helpSituation;

    /** 帮扶人姓名 */
    @Excel(name = "帮扶人姓名")
    private String helpName;

    /** 帮扶人联系方式 */
    @Excel(name = "帮扶人联系方式")
    private String helpTel;

    /** 收治情况 */
    @Excel(name = "收治情况")
    private String admittedSituation;

    /** 收治机构名称 */
    @Excel(name = "收治机构名称")
    private String admittedName;

    /** 家庭主要经济来源 */
    @Excel(name = "家庭主要经济来源")
    private String mainSource;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;



}
