package com.isstech.isstechadmin.domain;

import lombok.Data;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 社区楼栋对象 base_community_building
 * 
 * @author yzp
 * @date 2020-11-26
 */
@Data
public class BaseCommunityBuilding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 楼栋名称 */
    @Excel(name = "楼栋名称")
    private String name;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 是否是多小区社区 */
    private String multiple;

    /** 小区编号 */
    @Excel(name = "小区编号")
    private String communityChildId;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private String communityChildName;

    /** 楼栋编号 */
    @Excel(name = "楼栋编号")
    private String serialNumber;

    /** 楼层数 */
    @Excel(name = "楼层数")
    private Long floorNumber;

    /** 单元数 */
    @Excel(name = "单元数")
    private Long unitNumber;

    /** 经度 */
    private String longitud;

    /** 纬度 */
    private String latitude;

    /** 是否有电梯(0否1是,默认0) */
    @Excel(name = "是否有电梯(0否1是,默认0)")
    private String elevator;

}
