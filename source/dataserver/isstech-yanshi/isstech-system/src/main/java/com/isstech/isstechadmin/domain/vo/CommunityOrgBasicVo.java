package com.isstech.isstechadmin.domain.vo;

import com.isstech.isstechadmin.domain.BaseOrgBasic;
import lombok.Data;

/**
 * 社区与综治机构补充实体类
 *
 * @author dyf
 * @date 2020-12-29
 */
@Data
public class CommunityOrgBasicVo extends BaseOrgBasic {
    private String communityId; //社区id

    private String communityChildId; //小区id

    private String communityHousesId; //房屋id
}
