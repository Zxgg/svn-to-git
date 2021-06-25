package com.isstech.isstechadmin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Description 系统访问记录表 sys_logininfor
 * @Author yzp
 * @Date 2020/11/10 16:37
 */
@Data
public class SysLogininfor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
//    @Excel(name = "序号", cellType = Excel.ColumnType.NUMERIC)
    private String id;

    /** 用户账号 id*/
//    @Excel(name = "用户账号id")
    private String userId;

    /** 用户账号 name*/
    @Excel(name = "用户名称")
    private String userName;

    /** 登录IP地址 */
    @Excel(name = "登录地址")
    private String ipaddr;

    /** 登录地点 */
    @Excel(name = "登录地点")
    private String loginLocation;

    /** 浏览器类型 */
    @Excel(name = "浏览器")
    private String browser;

    /** 操作系统 */
    @Excel(name = "操作系统")
    private String os;

    /** 登录状态 0成功 1失败 */
    @Excel(name = "登录状态", readConverterExp = "0=成功,1=失败")
    private String status;

    /** 提示消息 */
    @Excel(name = "提示消息")
    private String msg;

    /** 操作类型（0登陆，1登出） */
//    @Excel(name = "操作类型（0登陆，1登出）")
    private String loginOrLogout;

    /** 访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
}
