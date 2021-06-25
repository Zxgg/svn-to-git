package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的对象 base_pass_analysis_task
 * 
 * @author yzp
 * @date 2020-12-18
 */
@Data
public class BasePassAnalysisTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String name;

    /** 任务类型表 */
    @Excel(name = "任务类型表")
    private String type;

    /** 告警层级 */
    @Excel(name = "告警层级")
    private Long alarmLevel;

    /** 命中类型 */
    @Excel(name = "命中类型")
    private String hitType;

    /** $column.columnComment */
    @Excel(name = "命中类型")
    private Long confidenceThreshold;

    /** 布控ID */
    @Excel(name = "布控ID")
    private String suspectId;

    /** 分析任务ID，不一定有这个字段 */
    @Excel(name = "分析任务ID，不一定有这个字段")
    private String taskId;

    /** 布控开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "布控开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 布控结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "布控结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 是否启用布控 */
    @Excel(name = "是否启用布控")
    private String enable;

    /** 布控的卡口列表，多个卡口以逗号隔开 */
    @Excel(name = "布控的卡口列表，多个卡口以逗号隔开")
    private String selectBayonets;

    /** 布控的名单组列表，多个分组用逗号隔开 */
    @Excel(name = "布控的名单组列表，多个分组用逗号隔开")
    private String selectGroups;

    /** 域ID */
    @Excel(name = "域ID")
    private String srcDomainCode;

    /** 是否开启车牌模糊命中 */
    @Excel(name = "是否开启车牌模糊命中")
    private String plateSimilar;

    /** 名单类型 01 白名单  02 黑名单 */
    private String rosterType;

    /** 卡口列表(带名称），多个卡口以逗号隔开 */
    private String bayonetNames;

    /** 名单组列表(带名称），多个分组用逗号隔开 */
    private String groupNames;


}
