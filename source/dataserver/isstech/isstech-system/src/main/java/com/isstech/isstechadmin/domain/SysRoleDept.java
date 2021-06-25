package com.isstech.isstechadmin.domain;

import lombok.Data;

/**
 * @Description 角色和部门关联 sys_role_dept
 * @Author yzp
 * @Date 2020/11/10 19:34
 */
@Data
public class SysRoleDept
{
    /** 角色ID */
    private String roleId;
    
    /** 部门ID */
    private String deptId;

}
