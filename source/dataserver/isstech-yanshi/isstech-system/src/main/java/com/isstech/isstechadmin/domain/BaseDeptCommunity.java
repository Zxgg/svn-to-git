package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 部门和社区关联关系 记录社区和部门的对应关系对象 base_dept_community
 * 
 * @author yzp
 * @date 2021-02-20
 */
@Data
public class BaseDeptCommunity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    @Excel(name = "部门ID")
    private String deptId;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;



}
