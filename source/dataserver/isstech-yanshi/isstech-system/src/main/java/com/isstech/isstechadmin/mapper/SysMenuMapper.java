package com.isstech.isstechadmin.mapper;

import java.util.List;

import com.isstech.common.core.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 菜单表 数据层
 * @Author yzp
 * @Date 2020/12/21 13:45
 */
public interface SysMenuMapper
{
    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 根据用户所有权限
     *
     * @return 权限列表
     */
    public List<String> selectMenuPerms();

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuListByUserId(SysMenu menu);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectMenuPermsByUserId(String userId);

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuTreeByUserId(String userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    public List<String> selectMenuListByRoleId(String roleId);

    /**
     * admin查询所有菜单
     * */
    public List<String> selectMenuListByAdmin();

    /**
     * 根据菜单ID查询信息
     *
     * @param id 菜单ID
     * @return 菜单信息
     */
    public SysMenu selectMenuById(String id);

    /**
     * 是否存在菜单子节点
     *
     * @param id 菜单ID
     * @return 结果
     */
    public int hasChildByMenuId(String id);

    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(SysMenu menu);

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysMenu menu);

    /**
     * 删除菜单管理信息
     *
     * @param id 菜单ID
     * @return 结果
     */
    public int deleteMenuById(String id);

    /**
     * 软删除菜单管理信息
     *
     * @param id 菜单ID
     * @return 结果
     */
    public int updateMenuById(String id);

    /**
     * 校验菜单名称是否唯一
     *
     * @param name 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    public SysMenu checkMenuNameUnique(@Param("name") String name, @Param("parentId") String parentId);

}
