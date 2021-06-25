package com.isstech.isstechadmin.domain.vo;

import lombok.Data;

/**
 * 服务于事件统计
 * @author qishuwei
 * @date 2020-12-11
 */
@Data
public class EventVo {

    private String eventName; // 事件名称

    private String type; // 事件类型

    private int num; // 数量

    private String alarmType; // 告警层级 0最小

    private String communityId; // 社区id

    private int processedNum; // 已处理数量

    private int noProcessedNum; // 未处理数量


}
