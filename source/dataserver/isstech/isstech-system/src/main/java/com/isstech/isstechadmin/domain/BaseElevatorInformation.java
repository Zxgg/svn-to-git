package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 电梯信息对象 base_elevator_information
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Data
public class BaseElevatorInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 社区id */
    @Excel(name = "社区id")
    private String communityId;

    /** 小区id */
    @Excel(name = "小区id")
    private String communityChildId;

    /** 楼栋id */
    @Excel(name = "楼栋id")
    private String communityBuildingId;

    /** 单元 */
    @Excel(name = "单元")
    private String unit;

    /** 电梯编号 */
    @Excel(name = "电梯编号")
    private String elevatorNumber;

    /** 电梯型号 */
    @Excel(name = "电梯型号")
    private String elevatorModel;

    /** 电梯安装时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "电梯安装时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date elevatorInstallDate;

    /** 年检记录json，年检人员，年检人员联系电话，年检人员编号，年检时间，年检结果 */
    @Excel(name = "年检记录json，年检人员，年检人员联系电话，年检人员编号，年检时间，年检结果")
    private String checkRecord;

    /** 最后一次年检时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后一次年检时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastCheckData;

    /** 最后一次年检人员 */
    @Excel(name = "最后一次年检人员")
    private String lastCheckBy;

    /** 最后一次年检人员联系电话 */
    @Excel(name = "最后一次年检人员联系电话")
    private String lastCheckByPhone;

    /** 最后一次年检人员编号 */
    @Excel(name = "最后一次年检人员编号")
    private String lastCheckByNumber;

    /** 最后一次年检结果 */
    @Excel(name = "最后一次年检结果")
    private String lastCheckResult;

    /** 年检间隔（多久检查一次，单位月） */
    @Excel(name = "年检间隔", readConverterExp = "多=久检查一次，单位月")
    private String checkInterval;

    /** 电梯厂家名称 */
    @Excel(name = "电梯厂家名称")
    private String elevatorFactoryName;

    /** 电梯厂家联系人 */
    @Excel(name = "电梯厂家联系人")
    private String elevatorFactoryBy;

    /** 电梯厂家联系电话 */
    @Excel(name = "电梯厂家联系电话")
    private String elevatorFactoryPhone;



}
