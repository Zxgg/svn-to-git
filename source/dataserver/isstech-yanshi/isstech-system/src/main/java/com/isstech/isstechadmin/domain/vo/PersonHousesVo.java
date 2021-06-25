package com.isstech.isstechadmin.domain.vo;

import com.isstech.isstechadmin.domain.BasePersonnelInformation;
import lombok.Data;


/**
 * 人员信息对象 base_personnel_information
 * 
 * @author yzp
 * @date 2020-11-23
 */
@Data
public class PersonHousesVo extends BasePersonnelInformation
{
    /** 房屋id */
    private String houseId;
    /** 房屋名称 */
    private String houseName;

//    /** 社区ID */
//    private String communityId;
//
//    /** 小区ID */
//    private String communityChildId;

    /** 社区楼栋ID */
    private String communityBuildingId;

    /** 楼栋编号 */
    private String serialNumber;

    /** 楼栋名称 */
    private String buildingName;

    /** 单元 */
    private String unit;

    /** 楼层 */
    private String floor;

    /** 门牌号 */
    private String houseNumber;

    /** 电话 */
    private String phone;

    /** 厅室构造(“两室一厅一厨一卫”比如)  */
    private String houseStructure;

    /** 面积(单位平方米)建筑 */
    private String area;

    /** 房屋建设时间(房屋建设时间) */
    private String constructionTime;

    /** 产权时间(比如“70年”“50年”) */
    private String housePropertyRight;

    /** 使用性质(比如“商用”、“居住使用”) */
    private String natureOfUse;

    /** 是否出租,(0否1是,默认0) */
    private String rent;

    /** 出租用途 */
    private String rentalPurpose;

    /** 隐患类型(0无隐患） */
    private String hazardType;

    /** 经度 */
    private String longitud;

    /** 纬度 */
    private String latitude;

    /** 照片列表 */
    private String pics;

    /** 是否有电梯(0否1是,默认0) */
    private String elevator;

    /** 住户与房主关系 */
    private String relationshipWithHomeowner ;

    /** 开始时间 */
    private String startTime ;

    /** 终止时间 */
    private String endTime ;

    /** 居住类型（0未知,1自有房屋,2租住,3寄居,4临时寄居） */
    private String typeOfResidence ;

    /** 是否是房主,(0否1是,默认0) */
    private String homeowner ;

    /** 社区房屋和人员关联表备注 */
    private String homePerRemark;

    /** 社区房屋和人员关联表id */
    private String comHousesPerId;
}
