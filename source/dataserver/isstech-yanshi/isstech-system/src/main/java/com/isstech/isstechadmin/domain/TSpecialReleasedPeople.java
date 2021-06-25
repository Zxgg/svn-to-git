package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 刑满释放人员 对象 t_special_released_people
 * 
 * @author yzp
 * @date 2021-02-22
 */
@Data
public class TSpecialReleasedPeople extends BaseEntity
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

    /** 是否累犯 */
    @Excel(name = "是否累犯")
    private String isRecidivism;

    /** 原罪名 */
    @Excel(name = "原罪名")
    private String originalCrimeName;

    /** 原判刑期 */
    @Excel(name = "原判刑期")
    private String punishmentTerm;

    /** 服刑场所 */
    @Excel(name = "服刑场所")
    private String punishmentAddress;

    /** 释放日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "释放日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date releaseDate;

    /** 危险性评估 */
    @Excel(name = "危险性评估")
    private String dangerAssess;

    /** 衔接日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "衔接日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date joinDate;

    /** 衔接情况 */
    @Excel(name = "衔接情况")
    private String joinSituation;

    /** 安置日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "安置日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date placeDate;

    /** 安置情况 */
    @Excel(name = "安置情况")
    private String placeSituation;

    /** 未安置原因 */
    @Excel(name = "未安置原因")
    private String unplaceSituation;

    /** 帮教情况 */
    @Excel(name = "帮教情况")
    private String helpSituation;

    /** 是否重新犯罪 */
    @Excel(name = "是否重新犯罪")
    private String isAgainCrime;

    /** 重犯罪名称 */
    @Excel(name = "重犯罪名称")
    private String crimeName;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;



}
