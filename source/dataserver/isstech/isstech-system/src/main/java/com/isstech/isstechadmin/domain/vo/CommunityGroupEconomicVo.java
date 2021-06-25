package com.isstech.isstechadmin.domain.vo;

import com.isstech.isstechadmin.domain.BaseGroupEconomic;
import lombok.Data;

/**
 * 社区与综治机构补充实体类
 *
 * @author dyf
 * @date 2020-12-29
 */
@Data
public class CommunityGroupEconomicVo extends BaseGroupEconomic {
    private String communityId; //社区id

    private String communityChildId; //小区id

    private String communityHousesId; //房屋id
}
