package com.isstech.isstechadmin.domain.vo;

import com.isstech.isstechadmin.domain.BaseOrgBasicTeam;
import com.isstech.isstechadmin.domain.BaseOrgPublicTeam;
import lombok.Data;

/**
 * @author zpyank
 * @version 1.0
 * @date 2021/1/29 16:25
 */
@Data
public class BaseOrgPublicTeamVo extends BaseOrgPublicTeam {

    /** 机构id */
    private String orgId;

    /** 机构名称 */
    private String publicOrgName;

    /** 机构类型 */
    private String publicOrgType;

    /** 机构层级 */
    private String orgLevel;

    /** 主要职能 */
    private String res;

    /** 行政区划 */
    private String divison;

}
