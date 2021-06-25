package com.isstech.isstechadmin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.isstechadmin.domain.BaseVehicleCommunityHousesPersonnel;
import lombok.Data;

import java.util.Date;

/**
 * 车辆和社区房屋和人员关联 补充车辆信息
 * 
 * @author yzp
 * @date 2020-11-30
 */
@Data
public class VehComHousesPersonVo extends BaseVehicleCommunityHousesPersonnel
{

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String plate;

    /** 车型 */
    @Excel(name = "车型")
    private String model;

    /** 车品牌 */
    @Excel(name = "车品牌")
    private String brand;

    /** 具体品牌型号 */
    @Excel(name = "具体品牌型号")
    private String specificBrand;

    /** 座位数 */
    @Excel(name = "座位数")
    private Long numberOfSeats;

    /** 车库序号 */
    @Excel(name = "车库序号")
    private String parkingSpaceNumber;

    /** 购买时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buyTime;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 是否已经审核确认,(0否1是,默认0) */
    @Excel(name = "是否已经审核确认,(0否1是,默认0)")
    private String checked;

}
