package com.isstech.common.utils;

/**
 * @Description 处理并记录日志文件
 * @Author yzp
 * @Date 2020/12/14 16:12
 */
public class LogUtils
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
