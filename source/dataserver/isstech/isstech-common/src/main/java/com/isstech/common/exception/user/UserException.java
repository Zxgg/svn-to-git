package com.isstech.common.exception.user;

import com.isstech.common.exception.BaseException;

/**
 * @Description 用户信息异常类
 * @Author yzp
 * @Date 2020/12/21 13:41
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
