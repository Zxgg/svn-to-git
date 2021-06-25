package com.isstech.isstechadmin.domain.vo;

import com.isstech.isstechadmin.domain.BaseCommunityHouses;
import lombok.Data;

/**
 * 社区房屋信息补充实体类
 * 
 * @author yzp
 * @date 2020-11-26
 */
@Data
public class CommunityHousesVo extends BaseCommunityHouses
{
    /** 社区房屋和人员关联表id */
    private String comHousePersonId;

    /** 房屋ID */
    private String communityHousesId;

    /** 人员ID */
    private String personnelId;

    /** 居住类型（0未知,1自有房屋,2租住,3寄居,4临时寄居） */
    private String typeOfResidence;

    /** 是否是房主,(0否1是,默认0) */
    private String homeowner;

    /** 删除标志（0代表存在 1代表删除） */
    private String isDelFlag;

    /** 社区名称 */
    private String communityName;

    /** 小区名称 */
    private String communityChildName;
}
