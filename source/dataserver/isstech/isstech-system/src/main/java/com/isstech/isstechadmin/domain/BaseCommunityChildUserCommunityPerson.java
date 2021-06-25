package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 小区用户和小区人员房屋关联 对象 base_community_child_user_community_person
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Data
public class BaseCommunityChildUserCommunityPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 业主的personID */
    @Excel(name = "业主的personID")
    private String personId;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

    /** 房屋ID */
    @Excel(name = "房屋ID")
    private String communityHousesId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 产权证或购房合同或租房合同图片列表 */
    @Excel(name = "产权证或购房合同或租房合同图片列表")
    private String credentials;

    /** 关联类型（租房、业主、家属） */
    @Excel(name = "关联类型", readConverterExp = "租=房、业主、家属")
    private String type;



}
