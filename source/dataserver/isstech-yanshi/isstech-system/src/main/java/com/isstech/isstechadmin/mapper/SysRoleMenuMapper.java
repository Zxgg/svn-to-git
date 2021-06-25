package com.isstech.isstechadmin.mapper;

import java.util.List;

import com.isstech.isstechadmin.domain.SysRoleMenu;

/**
 * @Description 角色与菜单关联表 数据层
 * @Author yzp
 * @Date 2020/12/21 13:44
 */
public interface SysRoleMenuMapper
{
    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int checkMenuExistRole(String menuId);

    /**
     * 通过角色ID删除角色和菜单关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleMenuByRoleId(String roleId);

    /**
     * 批量新增角色菜单信息
     * 
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    public int batchRoleMenu(List<SysRoleMenu> roleMenuList);

    /**
     * 新增角色菜单信息
     *
     * @param sysRoleMenu 角色菜单
     * @return 结果
     */
    public int insertRoleMenu(SysRoleMenu sysRoleMenu);
}
