package com.isstech.isstechadmin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 事件详情对象 base_event_info
 *
 * @author yzp
 * @date 2021-04-27
 */
@Data
public class BaseEventInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 事件名称
     */
    @Excel(name = "事件名称")
    private String eventName;

    /**
     * 事件类型
     */
    @Excel(name = "事件类型")
    private String eventType;

    /**
     * 上报时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上报时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reportingTime;

    /**
     * 状态
     */
    @Excel(name = "状态", readConverterExp = "0=待受理，1=受理，2=分派，3=待办结，4=办结，5=归档, -1=误报")
//    @Excel(name = "状态", readConverterExp = "1=待受理, 2=受理, 3=代办结, 4=办结, 5=归档")
    private String eventStatus;

    /**
     * 子状态
     */
    @Excel(name = "子状态")
    private String eventStatusSon;

    /**
     * 处理类型（1-智能化事件，2-人工处理）
     */
    @Excel(name = "处理类型", readConverterExp = "1=智能化事件，2=人工处理")
    private String dealWithType;

    /**
     * 内容
     */
    @Excel(name = "内容")
    private String content;

    /**
     * 社区id
     */
    @Excel(name = "社区id")
    private String communityId;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;

    /**
     * 附件id
     */
    @Excel(name = "附件id")
    private String fileId;

    /**
     * 附件大图
     */
    @Excel(name = "附件大图")
    private String bigPicture;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 小区id
     */
    @Excel(name = "小区id")
    private String communityChildId;

    /**
     * 事件程度
     */
    @Excel(name = "事件程度")
    private String eventDegree;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;

    /**
     * 处理人
     */
    @Excel(name = "处理人")
    private String handleBy;
    private String handleById;

    /**
     * 上报人手机号
     */
    @Excel(name = "上报人手机号")
    private String createbyPhone;


    @Excel(name = "抓拍图片")
    private String alertFile;

    /**
     * 相似度
     */
    @Excel(name = "相似度")
    private String confidence;

    /**
     * 所属网格
     */
    @Excel(name = "所属网格")
    private String division;

    /**
     * 1+4 平台对应的处置类型
     */
    private String disposition;
    /**
     * 诉求人姓名
     */
    private String petitioner;


}


