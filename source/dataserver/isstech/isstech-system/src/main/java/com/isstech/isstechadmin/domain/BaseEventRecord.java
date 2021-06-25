package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 事件对象 base_event_record
 * 
 * @author yzp
 * @date 2021-03-30
 */
@Data
public class BaseEventRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 事件id */
    @Excel(name = "事件id")
    private String eventId;

    /** 事件名称 */
    @Excel(name = "事件名称")
    private String eventName;

    private Date reportingTime;
    /** 事件内容 */
    @Excel(name = "事件内容")
    private String eventContent;

    /** 状态 */
    @Excel(name = "状态")
    private String eventStatus;

    /** 子状态 */
    @Excel(name = "子状态")
    private String eventStatusSon;

    /** 处置描述/审核意见 */
    @Excel(name = "处置描述/审核意见")
    private String content;

    /** 社区id */
    @Excel(name = "社区id")
    private String communityId;

    /** 处理人 */
    @Excel(name = "处理人")
    private String handleBy;

    private String handleById;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 附件id */
    @Excel(name = "附件id")
    private String fileId;

    /** 删除标记 */
    private String delFlag;

    private String communityChildId;

    private String  eventDegree;

    private String eventType;

    private String  address;

    private String  reportBy;

    private String  isback;

    private  String eventInfoFileId;

}
