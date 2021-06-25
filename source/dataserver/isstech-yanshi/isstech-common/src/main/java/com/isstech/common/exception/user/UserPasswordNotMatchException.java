package com.isstech.common.exception.user;

/**
 * @Description 用户密码不正确或不符合规范异常类
 * @Author yzp
 * @Date 2020/12/18 18:06
 */
public class UserPasswordNotMatchException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("user.password.not.match", null);
    }
}
