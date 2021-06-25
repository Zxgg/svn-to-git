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
public class EventCountDTO {
    private Integer num;

    /**
     * 社区id
     */
    private String communityId;

    /**
     * 网格
     */
    private String division;

    /**
     * 办理
     */
    private String handle;

    /**
     * 办结
     */
    private String toConclude;
}
