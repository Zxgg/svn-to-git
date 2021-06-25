package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 小区用户 对象 base_community_child_user
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Data
public class BaseCommunityChildUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 用户名 */
    @Excel(name = "用户名")
    private String name;

    /** 注册手机号 */
    @Excel(name = "注册手机号")
    private String phoneNumber;

    /** 注册证件类型 */
    @Excel(name = "注册证件类型")
    private String idType;

    /** 注册身份证号 */
    @Excel(name = "注册身份证号")
    private String identificationNumber;

    /** 是否已经进行业主认证 */
    @Excel(name = "是否已经进行业主认证")
    private String authentication;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 本人照片 */
    @Excel(name = "本人照片")
    private String pics;

    /** 密码 */
    @Excel(name = "密码")
    private String pwd;

    /** 历史密码 */
    private String oldPwd;



}
