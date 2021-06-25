package com.isstech.common.core.domain.entity;

import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * app用户 对象
 *
 * @author yzp
 * @date 2021-02-19
 */

@Data
public class SysAppUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 用户名 */
    private String name;

    /** 注册手机号 */
    private String phoneNumber;

    /** 注册证件类型 */
    private String idType;

    /** 注册身份证号 */
    private String identificationNumber;

    /** 是否已经进行业主认证 */
    private String authentication;

    /** 性别 */
    private String sex;

    /** 邮箱 */
    private String email;

    /** 本人照片 */
    private String pics;

    /** 密码 */
    private String pwd;

    /** 历史密码 */
    private String oldPwd;


}
