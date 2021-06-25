package com.isstech.isstechadmin.domain;

import lombok.Data;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 关爱人员信息记录 对象 base_caring_person
 * 
 * @author yzp
 * @date 2020-11-26
 */
@Data
public class BaseCaringPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 关爱人员ID */
//    @Excel(name = "关爱人员ID")
    private String personId;

    /** 关爱类型 */
//    @Excel(name = "关爱类型")
    private String type;

    /** 社区ID */
//    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
//    @Excel(name = "小区ID")
    private String communityChildId;
}
