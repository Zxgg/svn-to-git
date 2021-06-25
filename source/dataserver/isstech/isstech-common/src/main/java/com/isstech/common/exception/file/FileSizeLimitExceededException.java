package com.isstech.common.exception.file;

/**
 * @Description 文件名大小限制异常类
 * @Author yzp
 * @Date 2020/12/14 15:54
 */
public class FileSizeLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}
