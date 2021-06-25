package com.isstech.common.exception.user;

/**
 * @Description 验证码错误异常类
 * @Author yzp
 * @Date 2020/12/21 13:42
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
