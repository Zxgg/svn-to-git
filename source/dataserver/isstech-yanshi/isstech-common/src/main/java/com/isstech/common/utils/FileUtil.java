package com.isstech.common.utils;

import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author lichong<br />
 * @Description: 文件处理<br />
 * @date 2019/10/18  16:33<br/>
 */
public class FileUtil {

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    //图片转化成base64字符串

    public static String GetImageStr(File imgFile) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理

        InputStream in = null;

        byte[] data = null;

        //读取图片字节数组

        try {

            in = new FileInputStream(imgFile);

            data = new byte[in.available()];

            in.read(data);

            in.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

        //对字节数组Base64编码

        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);//返回Base64编码过的字节数组字符串

    }

}
