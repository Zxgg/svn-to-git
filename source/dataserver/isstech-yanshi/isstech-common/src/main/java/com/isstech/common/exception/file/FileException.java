package com.isstech.common.exception.file;

import com.isstech.common.exception.BaseException;

/**
 * @Description 文件信息异常类
 * @Author yzp
 * @Date 2020/12/14 15:53
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
