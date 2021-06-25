package com.isstech.isstechadmin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author issuser
 */
@Data
@Builder
@AllArgsConstructor
public class PushMessageDTO {
    /**
     * 向1+4上报告警消息数据传输对象
     */
    private String rqstNumber;
    private String rqstTime;
    private String rqstAddress;
    private String rowGuid;
    private String caseCode;
    private String rqstName;
    private String gridCode;
    private String gisx;
    private String gisy;
    private String rqstTitle;
    private String rqstContent;
    private String reportMedia;
    private String notes;
    //事件分类
    private String rqstEventType;

}
