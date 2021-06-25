package com.isstech.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Description 角色表 sys_role
 * @Author yzp
 * @Date 2020/11/10 18:46
 */
@Data
public class SysRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
//    @Excel(name = "角色序号", cellType = Excel.ColumnType.NUMERIC)
    private String id;

    /** 角色名称 */
    @Excel(name = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    private String name;

    /** 角色权限 */
    @Excel(name = "权限字符")
    @NotBlank(message = "权限字符不能为空")
    @Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
    private String roleKey;

    /** 角色排序 */
    @Excel(name = "显示顺序")
    @NotBlank(message = "显示顺序不能为空")
    private String sort;

    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限） */
//    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限,3=本部门数据权限,4=本部门及以下数据权限")
    private String dataScope;

    /** 角色状态（0正常 1停用） */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;


    /** 菜单组 */
    private String[] menuIds;

    /** 部门组（数据权限） */
    private String[] deptIds;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public boolean isAdmin()
    {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(String roleId)
    {
        return roleId != null && "1".equals(roleId);
    }




}
