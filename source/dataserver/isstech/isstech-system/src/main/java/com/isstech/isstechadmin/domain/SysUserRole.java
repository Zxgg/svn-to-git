package com.isstech.isstechadmin.domain;

import lombok.Data;

/**
 * @Description 用户和角色关联 sys_user_role
 * @Author yzp
 * @Date 2020/11/10 19:08
 */
@Data
public class SysUserRole
{
    /** 用户ID */
    private String userId;
    
    /** 角色ID */
    private String roleId;

}
