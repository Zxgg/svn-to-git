package com.isstech.isstechadmin.domain.vo;

import com.isstech.isstechadmin.domain.BaseVehicleInformation;
import lombok.Data;

/**
 * @Description 车辆信息对象补充实体类(补充关联表信息)
 * @Author yzp
 * @Date 2020/11/27 13:26
 */
@Data
public class VehicleInformationVo extends BaseVehicleInformation
{

    /** 车辆和社区房屋和人员关联表id */
    private String vehicleComHousePersonId;

//    /** 社区ID */
//    private String communityId;
//
//    /** 小区ID */
//    private String communityChildId;

    /** 房屋ID */
    private String communityHousesId;

    /** 人员ID */
    private String personnelId;

    /** 车辆ID */
    private String vehicleId;

    /** 车辆使用类型（0未知,1自有车辆，2租赁,3临时使用） */
    private String typeOfResidence;

    /** 是否是车主,(0否1是,默认1) */
    private String homeowner;

    /** 删除标志（0代表存在 1代表删除）默认是0 */
    private String isDelFlag;

    /** 布控车辆的抓拍图 */
    private String limitPic;

    /** 车主姓名 */
    private String ownerName;

    /** 车主证件号 */
    private String ownerIDNO;

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
