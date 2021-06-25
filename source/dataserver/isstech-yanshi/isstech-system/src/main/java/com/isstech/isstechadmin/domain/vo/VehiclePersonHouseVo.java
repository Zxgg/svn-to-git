package com.isstech.isstechadmin.domain.vo;

import lombok.Data;

/**
 * @author zengxi
 * @version 1.0
 * @date 2021/5/11 3:46 下午
 **/
@Data
public class VehiclePersonHouseVo {

    /**
     * 车辆ID
     */
    private String vehicleId;

    /**
     * 车牌
     */
    private String plate;

    /**
     * 品牌
     */
    private String brand;
    /**
     * 车位
     */
    private String parkingSpaceNum;
    /**
     * 车辆图片
     */
    private String vehicleImg;
    /**
     * 颜色
     */
    private String color;
    /**
     * 所属网格
     */
    private String gridId;
    /**
     * 人员id
     */
    private String personId;
    /**
     * 人名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 籍贯
     */
    private String location;
    /**
     * 身份证号码
     */
    private String idNumber;
    /**
     * 所属小区id
     */
    private String communityChildId;
    /**
     * 所属小区名
     */
    private String communityChildName;
    /**
     * 住址
     */
    private String address;

    /**
     * 楼栋id
     */
    private String buildingId;

    /**
     * 楼栋名称
     */
    private String buildingName;
}
