package com.isstech.isstechadmin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author issuser
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventAnalysisDTO {

    /**
     * 社会治安
     */
    private Integer publicSecurity;

    /**
     * 纠纷调解
     */
    private Integer disputeMediation;

    /**
     * 专项活动
     */
    private Integer specialActivities;

    /**
     * 公共安全
     * public_safety
     */
    private Integer publicSafety;

    /**
     * 环境保护
     */
    private Integer environmentalProtection;

    /**
     * 城市管理
     */
    private Integer cityManagement;

    /**
     * 民生服务
     */
    private Integer livelihoodServices;

    /**
     * 总数
     */
    private Integer total;
}
