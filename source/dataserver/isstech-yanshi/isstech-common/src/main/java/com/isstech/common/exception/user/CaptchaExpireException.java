package com.isstech.common.exception.user;

/**
 * @Description 验证码失效异常类
 * @Author yzp
 * @Date 2020/12/21 13:41
 */
public class CaptchaExpireException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException()
    {
        super("user.jcaptcha.expire", null);
    }
}
