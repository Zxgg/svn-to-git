package com.isstech.isstechadmin.domain;

import lombok.Data;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 车辆和社区房屋和人员关联 对象 base_vehicle_community_houses_personnel
 * 
 * @author yzp
 * @date 2020-11-30
 */
@Data
public class BaseVehicleCommunityHousesPersonnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

    /** 房屋ID */
    @Excel(name = "房屋ID")
    private String communityHousesId;

    /** 人员ID */
    @Excel(name = "人员ID")
    private String personnelId;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private String vehicleId;

    /** 车辆使用类型（0未知,1自有车辆，2租赁,3临时使用） */
    @Excel(name = "车辆使用类型", readConverterExp = "0=未知,1自有车辆，2租赁,3临时使用")
    private String typeOfResidence;

    /** 是否是车主,(0否1是,默认1) */
    @Excel(name = "是否是车主,(0否1是,默认1)")
    private String homeowner;

    /** 车位号码 */
    @Excel(name = "车位号码")
    private String parkingSpaceNumber;

    /** 删除标志（0代表存在 1代表删除）默认是0 */
    private String delFlag;

    /** 人员名称 */
    @Transient
    private String personName;

}
