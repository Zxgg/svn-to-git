package com.isstech.isstechadmin.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 社区房屋信息对象 base_community_houses
 * 
 * @author yzp
 * @date 2020-11-26
 */
@Data
public class BaseCommunityHouses extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 名称，房屋的名称无用，暂时不用
     */
    @Excel(name = "名称，房屋的名称无用，暂时不用")
    private String name;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 社区ID
     */
    @Excel(name = "社区ID")
    private String communityId;

    /**
     * 小区ID
     */
    @Excel(name = "小区ID")
    private String communityChildId;

    /**
     * 社区楼栋ID
     */
    @Excel(name = "社区楼栋ID")
    private String communityBuildingId;

    /**
     * 楼栋编号
     */
    private String serialNumber;

    /**
     * 楼栋名称
     */
    @Excel(name = "楼栋名称")
    private String buildingName;

    /**
     * 单元
     */
    @Excel(name = "单元")
    private String unit;

    /**
     * 楼层
     */
    @Excel(name = "楼层")
    private String floor;

    /**
     * 门牌号
     */
    @Excel(name = "门牌号")
    private String houseNumber;

    /**
     * 电话
     */
    @Excel(name = "电话")
    private String phone;

    /**
     * 厅室构造(“两室一厅一厨一卫”比如)
     */
    @Excel(name = "厅室构造(“两室一厅一厨一卫”比如)")
    private String houseStructure;

    /**
     * 建筑面积(单位平方米)
     */
    @Excel(name = "建筑面积(单位平方米)")
    private String buildingArea;

    /**
     * 套内面积(单位平方米)
     */
    @Excel(name = "套内面积(单位平方米)")
    private String area;

    /**
     * 房屋建设时间(房屋建设时间)
     */
    @Excel(name = "房屋建设时间(房屋建设时间)")
    private String constructionTime;

    /**
     * 产权时间(比如“70年”“50年”)
     */
    @Excel(name = "产权时间(比如“70年”“50年”)")
    private String housePropertyRight;

    /**
     * 使用性质(比如“商用”、“居住使用”)
     */
    @Excel(name = "使用性质(比如“商用”、“居住使用”)")
    private String natureOfUse;

    /**
     * 是否出租,(0否1是,默认0)
     */
    @Excel(name = "是否出租,(0否1是,默认0)")
    private String rent;

    /**
     * 出租用途
     */
    @Excel(name = "出租用途")
    private String rentalPurpose;

    /**
     * 隐患类型(0无隐患）
     */
    @Excel(name = "隐患类型(0无隐患）")
    private String hazardType;

    /**
     * 经度
     */
    private String longitud;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 照片列表
     */
    @Excel(name = "照片列表")
    private String pics;

    /**
     * 是否有电梯(0否1是,默认0)
     */
    @Excel(name = "是否有电梯(0否1是,默认0)")
    private String elevator;

    /**
     * 房屋平面图（图片或者pdf文件）路径
     */
    @Excel(name = "房屋平面图", readConverterExp = "图=片或者pdf文件")
    private String planarGraph;

    /**
     * 方位图（图片或者pdf）文件路径
     */
    @Excel(name = "方位图", readConverterExp = "图=片或者pdf")
    private String azimuthGraph;

    /**
     * 星级1-5，默认为空
     */
    @Excel(name = "星级1-5，默认为空")
    private String starRating;


}
