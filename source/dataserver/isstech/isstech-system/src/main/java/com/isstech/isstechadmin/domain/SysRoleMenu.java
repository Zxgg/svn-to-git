package com.isstech.isstechadmin.domain;

import lombok.Data;

/**
 * @Description 角色和菜单关联 sys_role_menu
 * @Author yzp
 * @Date 2020/11/10 19:32
 */
@Data
public class SysRoleMenu
{
    /** 角色ID */
    private String roleId;
    
    /** 菜单ID */
    private String menuId;

}
