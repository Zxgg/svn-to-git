package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 部门和小区关联关系 记录小区（包含所属社区）和部门的对应关系对象 base_dept_community_child
 *
 * @author yzp
 * @date 2021-01-09
 */
@Data
public class BaseDeptCommunityChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    @Excel(name = "部门ID")
    private String deptIp;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;



}
