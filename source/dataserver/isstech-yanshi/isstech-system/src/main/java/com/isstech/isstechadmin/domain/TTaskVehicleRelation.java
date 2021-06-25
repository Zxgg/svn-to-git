package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 车辆对应关系任务 对象 t_task_vehicle_relation
 * 
 * @author yzp
 * @date 2020-12-24
 */
@Data
public class TTaskVehicleRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车辆名单id */
    @Excel(name = "车辆名单id")
    private String plateId;

    /** 车辆人员id */
    @Excel(name = "车辆人员id")
    private String vehicleId;

    /** 社区id */
    @Excel(name = "社区id")
    private String communityId;

    /** 小区id */
    @Excel(name = "小区id")
    private String communityChildId;

    /** 分析任务ID */
    @Excel(name = "分析任务ID")
    private String taskId;

    /** 布控ID */
    @Excel(name = "布控ID")
    private String suspectId;

    /** 名单组id */
    @Excel(name = "名单组id")
    private String groupId;

    /** 来源 01车辆 02访客车辆 03临时访客车辆  */
    @Excel(name = "来源 01车辆 02访客车辆 03临时访客车辆 ")
    private String sourceType;

    /** 名单类型 01 白名单  02 黑名单 */
    @Excel(name = "名单类型 01 白名单  02 黑名单")
    private String rosterType;
    /** 车牌号 */
    private String plate;


}
