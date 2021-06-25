package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 车辆通行对象 base_pass_vehicle
 *
 * @author yzp
 * @date 2020-12-18
 */
@Data
public class BasePassVehicle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    private String motorVehicleId;

    /** 车辆出现时间 */
    private Date appearTime;

    /** 品牌标志识别可信度 */
    private String brandReliability;

    /** 打电话状态 */
    private String calling;

    /** 车厢 */
    private String carOfVehicle;

    /** 车前部物描述 */
    private String descOfFrontItem;

    /** 车后部物品描述 */
    private String descOfRearItem;

    /** 设备编码 */
    private String deviceId;

    /** 行驶方向 */
    private String direction;

    /** 车辆消失时间 */
    private Date disappearTime;

    /** 行驶状态代码 */
    private String drivingStatusCode;

    /** 贴膜颜色 */
    private String filmColor;

    /** 有无车牌 */
    private String hasPlate;

    /** 撞痕信息 */
    private String hitMarkInfo;

    /** 信息分类 */
    private String infoKind;

    /** 是否涂改 */
    private String isAltered;

    /** 是否遮挡 */
    private String isCovered;

    /** 是否套牌 */
    private String isDecked;

    /** 改装标志 */
    private String isModified;

    /** 是否可疑车 */
    private String isSuspicious;

    /** 车道号 */
    private String laneNo;

    /** 左上角X坐标 */
    private String leftTopX;

    /** 左上角Y坐标 */
    private String leftTopY;

    /** 位置标记时间-人工采集时有效 */
    private Date markTime;

    /** 经过道路名称 */
    private String nameOfPassedRoad;

    /** 车内人数 */
    @Excel(name = "车内人数")
    private String numOfPassenger;

    /** 经过时刻 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "经过时刻", width = 30, dateFormat = "yyyy-MM-dd")
    private Date passTime;

    /** 每位号牌号码可信度 */
    private String plateCharReliabil;

    /** 号牌种类 */
    private String plateClass;

    /** 车牌颜色 */
    private String plateColor;

    /** 车牌描述 */
    private String plateDescribe;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String plateNo;

    /** 挂车牌号 */
    private String plateNoAttach;

    /** 号牌识别可信度 */
    private String plateReliability;

    /** 后视镜 */
    private String rearviewMirror;

    /** 右下角X坐标 */
    private String rightBtmX;

    /** 右下角Y坐标 */
    private String rightBtmY;

    /** 安全带状态 */
    private String safetyBelt;

    /** 车侧 */
    private String sideOfVehicle;

    /** 来源标识 */
    private String sourceId;

    /** 行驶速度 */
    private String speed;

    /** 近景照片 */
    private String storageUrl1;

    /** 车牌照片 */
    private String storageUrl2;

    /** 远景照片 */
    private String storageUrl3;

    /** 合成图 */
    private String storageUrl4;

    /** 缩略图 */
    private String storageUrl5;

    /** 遮阳板状态 */
    private String sunvisor;

    /** 关联卡口编号 */
    private String tollgateId;

    /** 车辆使用性质代码 */
    private String usingPropertiesCode;

    /** 车身描述 */
    private String vehicleBodyDesc;

    /** 车辆品牌 */
    @Excel(name = "车辆品牌")
    private String vehicleBrand;

    /** 底盘 */
    @Excel(name = "底盘")
    private String vehicleChassis;

    /** 车辆类型 */
    private String vehicleClass;

    /** 车身颜色 */
    private String vehicleColor;

    /** 颜色深浅 */
    private String vehicleColorDepth;

    /** 车门 */
    private String vehicleDoor;

    /** 车前部物品 */
    private String vehicleFrontItem;

    /** 车辆高度 */
    private String vehicleHeight;

    /** 车前盖 */
    @Excel(name = "车前盖")
    private String vehicleHood;

    /** 车辆长度 */
    private String vehicleLength;

    /** 车辆型号 */
    private String vehicleModel;

    /** 车后部物品 */
    private String vehicleRearItem;

    /** 车顶 */
    private String vehicleRoof;

    /** 遮挡 */
    private String vehicleShielding;

    /** 车辆年款 */
    private String vehicleStyles;

    /** 车后盖 */
    private String vehicleTrunk;

    /** 车轮 */
    private String vehicleWheel;

    /** 车辆宽度 */
    private String vehicleWidth;

    /** 车窗 */
    private String vehicleWindow;

    /** 车轮印花纹 */
    private String wheelPrintedPattern;

    /** 对应的在车辆表里的车辆ID */
    private String vehicleId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 楼栋id */
    private String communityBuildingId;

    /** 楼栋名称 */
    private String communityBuildingName;

    /** 小区id */
    @Excel(name = "小区id")
    private String communityChildId;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private String communityChildName;

    /** 房屋id */
    private String communityHousesId;

    /** 房屋名称 */
    @Excel(name = "房屋名称")
    private String communityHousesName;

    /** 社区id */
    @Excel(name = "社区id")
    private String communityId;

    /** 社区名称 */
    @Excel(name = "社区名称")
    private String communityName;

    /** 来源表 */
    @Excel(name = "来源表")
    private String sourceTableName;

}
