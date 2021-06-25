package com.isstech.common.core.domain.entity;

import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 菜单权限表 sys_menu
 * @Author yzp
 * @Date 2020/11/10 18:50
 */
@Data
public class SysMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    private String id;

    /** 菜单名称 */
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    private String name;

    /** 父菜单ID */
    private String parentId;

    /** 显示顺序 */
    @NotBlank(message = "显示顺序不能为空")
    private String orderNum;

    /** 路由地址 */
    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    private String path;

    /** 组件路径 */
    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    private String component;

    /** 是否为外链（0是 1否） */
    private String isFrame;

    /** 类型（M目录 C菜单 F按钮） */
    @NotBlank(message = "菜单类型不能为空")
    private String type;

    /** 显示状态（0显示 1隐藏） */
    private String visible;
    
    /** 菜单状态（0显示 1隐藏） */
    private String status;

    /** 权限字符串 */
    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;

    /** 菜单图标 */
    private String icon;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 是否是多路由（0否1是） */
    private String multiComponent;

    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<SysMenu>();


}
