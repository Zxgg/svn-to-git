package com.isstech.common.utils;

import com.alibaba.druid.util.Base64;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * @Classname QRCodeUtils
 * @Description 二维码生成工具
 * @Date 2020-02-03 12:56
 * @Created by issuser
 */
public class QRCodeUtils {

    /*
     * 图片的宽度和高度
     */
    private static int height = 300;
    private static int width = 300;
    // 图片的格式
    private static String format = "png";

    public static String generateQRCodeToBase64(String url){

        // 定义二维码的参数
        HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 定义字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 纠错的等级 L > M > Q > H 纠错的能力越高可存储的越少，一般使用M
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置图片边距
        hints.put(EncodeHintType.MARGIN, 2);

        try {
            // 最终生成 参数列表 （1.内容 2.格式 3.宽度 4.高度 5.二维码参数）
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);

            //字节数组输出流
            ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", imageOut);

            //字节数组输入流
            ByteArrayInputStream imageIn = new ByteArrayInputStream(imageOut.toByteArray());
            BufferedImage bImage = ImageIO.read(imageIn);

            ByteArrayOutputStream bs =new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bs);

            String base64 = "data:image/png;base64,"+ Base64.byteArrayToBase64(bs.toByteArray());

            return base64;
        } catch (Exception e) {
            e.printStackTrace();
            return "二维码生成异常";
        }
    }

    public static boolean orCode(String content, String path) {
        // 定义二维码的参数
        HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 定义字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 纠错的等级 L > M > Q > H 纠错的能力越高可存储的越少，一般使用M
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置图片边距
        hints.put(EncodeHintType.MARGIN, 2);

        try {
            // 最终生成 参数列表 （1.内容 2.格式 3.宽度 4.高度 5.二维码参数）
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 写入到本地
            Path file = new File(path).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args) {
        orCode("https://www.baidu.com", "d://temp/plot.release.wcuu.cn1.png");
    }
}


