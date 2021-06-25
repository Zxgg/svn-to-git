package com.isstech.isstechadmin.domain;

import lombok.Data;

/**
 *  1+4 平台调用列表对应实体
 * 
 * @author yzp
 * @date 2020-11-26
 */
@Data
public class OneToFour
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String rowguid;

    /** 当前步骤 */
    private String lasthandlestep;

    /** 上报内容 */
    private String rqstcontent;

    /** 数据来源 */
    private String rqstsource;

    /** 上报图片 */
    private String reportFile;

    /** 事件标题 */
    private String rqsttitle;

    /** 事件类型 */
    private String accordType;

    /** 上报人 */
    private String rqstname;

    /** 上报时间 */
    private String registertime;

    /** 上报地址 */
    private String rqstaddress;

    /** 处置附件 */
    private String handleFile;

    /** 上报人电话 */
    private String rqstnumber;

    /** 紧急程度 */
    private String urgentlevel;

    /** 核查附件 */
    private String checkFile;

    /** 网格 */
    private String scsmart;

    /** 事件类型详情 */
    private String remark;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
}
