package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 隔离家庭 对象 t_epidemic_isolation_family
 * 
 * @author yzp
 * @date 2021-01-20
 */
@Data
public class TEpidemicIsolationFamily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 社区ID */
//    @Excel(name = "社区ID")
    private String communityId;

    @Excel(name = "社区名称")
    private String communityName;

    /** 小区ID */
//    @Excel(name = "小区ID")
    private String communityChildId;

    @Excel(name = "小区名称")
    private String communityChildName;

    /** 社区楼栋ID */
//    @Excel(name = "社区楼栋ID")
    private String communityBuildingId;

    /** 楼栋编号 */
//    @Excel(name = "楼栋编号")
    private String serialNumber;

    /** 楼栋名称 */
    @Excel(name = "楼栋名称")
    private String buildingName;

    /** 单元 */
    @Excel(name = "单元")
    private String unit;

    /** 楼层 */
    @Excel(name = "楼层")
    private String floor;

    /** 门牌号 */
    @Excel(name = "门牌号")
    private String houseNumber;

    /** 住户信息,{张三,性别,身份证号,手机号;} */
    @Excel(name = "住户信息,{姓名,性别,身份证号,手机号}")
    private String personinfo;

    /** 开始隔离时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始隔离时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date isolatioStartTime;

    /** 隔离结束时间(通常计算得出） */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "隔离结束时间(通常计算得出）", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date isolationEndTime;

    /** 隔离总天数 */
    @Excel(name = "隔离总天数")
    private Long isolationDayNum;

    /** 物资采购记录(json) */
//    @Excel(name = "物资采购记录(json)")
    private String suppliesPurchaseRecord;

    /** 最后一次采购的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后一次采购的时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastSuppliesPurchase;

    /** 状态（隔离中，解除隔离，） */
    @Excel(name = "状态")
    private String stat;

    /** 是否有可能需要紧急外出人员,外出类型写备注里 */
    @Excel(name = "外出需求")
    private String needOut;

    /** 核酸检测记录（人员，身份证号，时间，结果） */
    @Excel(name = "核酸检测记录")
    private String nucleicAcidTestingRecord;

    /** 下次核酸检测时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次核酸检测时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextNucleicAcidTestingData;

    /** 是否有外出记录（人员，时间） */
    @Excel(name = "是否有外出记录")
    private String outRecord;

    /** 隔离原因 */
    @Excel(name = "隔离原因")
    private String isolationReason;



}
