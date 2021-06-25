package com.isstech.isstechadmin.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 社区矫正人员信息 对象 t_specia_correct
 * 
 * @author yzp
 * @date 2021-02-22
 */
@Data
public class TSpeciaCorrect extends BaseEntity
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

    /** 社区矫正人员编号 */
    @Excel(name = "社区矫正人员编号")
    private String communityPersonnelNumber;

    /** 原羁押场所 */
    @Excel(name = "原羁押场所")
    private String originalDetentionPlace;

    /** 矫正类别 */
    @Excel(name = "矫正类别")
    private String correctType;

    /** 案件类别 */
    @Excel(name = "案件类别")
    private String caseType;

    /** 罪犯类型 */
    @Excel(name = "罪犯类型")
    private String crimeType;

    /** 具体罪名 */
    @Excel(name = "具体罪名")
    private String specificCrime;

    /** 原判刑期 */
    @Excel(name = "原判刑期")
    private BigDecimal termDate;

    /** 刑种 */
    @Excel(name = "刑种")
    private String punishmentKinds;

    /** 原判刑开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "原判刑开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date termStartDate;

    /** 原判刑结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "原判刑结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date termEndDate;

    /** 矫正开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "矫正开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date correctStartDate;

    /** 矫正结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "矫正结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date correctEndDate;

    /** 接收方式 */
    @Excel(name = "接收方式")
    private String acceptMode;

    /** 四史情况 */
    @Excel(name = "四史情况")
    private String fourHistoriesSituation;

    /** 是否累惯犯 */
    @Excel(name = "是否累惯犯")
    private String sfgf;

    /** 三涉情况 */
    @Excel(name = "三涉情况")
    private String threeInvolveSituation;

    /** 是否建立矫正小组 */
    @Excel(name = "是否建立矫正小组")
    private String isBuildCorrectTeam;

    /** 矫正小组人员组成情况 */
    @Excel(name = "矫正小组人员组成情况")
    private String correctTeamSituation;

    /** 矫正解除(终止)类型 */
    @Excel(name = "矫正解除(终止)类型")
    private String correctRemoveType;

    /** 是否脱管 */
    @Excel(name = "是否脱管")
    private String isTrusteeship;

    /** 脱管原因 */
    @Excel(name = "脱管原因")
    private String trusteeshipReason;

    /** 检查监督脱管情况 */
    @Excel(name = "检查监督脱管情况")
    private String checkTrusteeshipSituation;

    /** 脱管纠正情况 */
    @Excel(name = "脱管纠正情况")
    private String trusteeshipCorrectSituation;

    /** 是否漏管 */
    @Excel(name = "是否漏管")
    private String isLeak;

    /** 漏管原因 */
    @Excel(name = "漏管原因")
    private String leakReason;

    /** 检查监督漏管情况 */
    @Excel(name = "检查监督漏管情况")
    private String checkLeakSituation;

    /** 漏管纠正情况 */
    @Excel(name = "漏管纠正情况")
    private String correctSituation;

    /** 奖惩情况 */
    @Excel(name = "奖惩情况")
    private String rewardPunishmentSituation;

    /** 刑罚变更执行情况 */
    @Excel(name = "刑罚变更执行情况")
    private String punishmentChangeSituation;

    /** 重新犯罪名称 */
    @Excel(name = "重新犯罪名称")
    private String crimeName;

    /** 是否重新犯罪 */
    @Excel(name = "是否重新犯罪")
    private String isAgainCrime;

    /** 社区矫正决定机关 */
    @Excel(name = "社区矫正决定机关")
    private String correctOffice;

    /** 矫正期限 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "矫正期限", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date correctTerm;

    /** 法律文书收到日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "法律文书收到日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date documentReceiptDate;

    /** 被收规定时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "被收规定时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date receivedTime;

    /** 具体报到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "具体报到时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date specificTime;

    /** 主要犯罪事实 */
    @Excel(name = "主要犯罪事实")
    private String mainCrime;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;



}
