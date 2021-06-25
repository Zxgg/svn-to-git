package com.isstech.isstechadmin.domain;

import java.math.BigDecimal;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 图像信息库对象 base_image_information_library
 * 
 * @author yzp
 * @date 2021-02-01
 */
@Data
public class BaseImageInformationLibrary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    @Excel(name = "删除标志", readConverterExp = "0=代表存在,1=代表删除")
    private String delFlag;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

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



}
