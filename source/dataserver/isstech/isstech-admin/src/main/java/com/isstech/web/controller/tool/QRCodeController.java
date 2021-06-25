package com.isstech.web.controller.tool;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.utils.QRCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 二维码生成接口
 *
 * @author dyf
 * @version 1.0
 * @date 2021/01/11 14:48
 */
@RestController
public class QRCodeController {

    /**
     * 根据地址转换成Base64二维码图片
     */
    @GetMapping("/generateQRCodeToBase64")
    public AjaxResult generateQRCodeToBase64(String url)
    {
        return AjaxResult.success("生成二维码成功！",QRCodeUtils.generateQRCodeToBase64(url));
    }
}
