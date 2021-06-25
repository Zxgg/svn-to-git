package com.isstech.isstechadmin.domain;

import java.math.BigDecimal;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 人员图像信息库对象 base_image_perinfor_library
 * 
 * @author yzp
 * @date 2021-02-01
 */
@Data
public class BaseImagePerinforLibrary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String personName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String identificationNumber;

    /** 设备id */
    @Excel(name = "设备id")
    private String deviceId;

    /** 文件索引 */
    @Excel(name = "文件索引")
    private String filePath;

    /** 文件类型（jpeg、avi等) */
    @Excel(name = "文件类型", readConverterExp = "文件类型（jpeg、avi等)")
    private String fileType;

    /** 文件大小，单位KB */
    @Excel(name = "文件大小，单位KB")
    private BigDecimal fileSize;

    /** 文件来源（截图、上传 等） */
    @Excel(name = "文件来源", readConverterExp = "截=图、上传,等=")
    private String fileSource;

    /** 所属信息库ID */
    @Excel(name = "所属信息库ID")
    private String libsId;

    /** 所属信息库类型 */
    @Excel(name = "所属信息库类型")
    private String libsType;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;



}
