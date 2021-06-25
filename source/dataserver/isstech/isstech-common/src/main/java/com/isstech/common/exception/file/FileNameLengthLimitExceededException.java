package com.isstech.common.exception.file;

/**
 * @Description 文件名称超长限制异常类
 * @Author yzp
 * @Date 2020/12/14 15:54
 */
public class FileNameLengthLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
