package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 车辆信息对象 base_vehicle_information
 * 
 * @author yzp
 * @date 2020-12-16
 */
@Data
public class BaseVehicleInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

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

    /** 车辆最初的购买时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车辆最初的购买时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buyTime;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 是否已经审核确认,(0否1是,默认0) */
    @Excel(name = "是否已经审核确认",readConverterExp = "0=否,1=是")
    private String checked;

    /** 管控限制,(0否1是,默认0) */
    @Excel(name = "管控限制",readConverterExp = "0=否,1=是")
    private String limited;

    /** 本条记录所属社区ID */
//    @Excel(name = "本条记录所属社区ID")
    private String communityId;

    /** 本条记录所属小区ID */
//    @Excel(name = "本条记录所属小区ID")
    private String communityChildId;

    /** 能源类型 */
    @Excel(name = "能源类型")
    private String energyType;

    /** 是否为重点车辆（0 否 1 是） */
    @Excel(name = "是否为重点车辆",readConverterExp = "0=否,1=是")
    private String isKey;

    /**
     * 颜色
     */
    private String color;

    /**
     * 所属网格
     */
    private String gridId;

    /**
     * 图片
     */
    private String image;

    /**
     * 车位
     */
    private String parkingSet;
}
