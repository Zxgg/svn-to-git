package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 群防群治组织和队伍关联对象 base_org_public_org_public_team
 * 
 * @author yzp
 * @date 2021-01-29
 */
@Data
public class BaseOrgPublicOrgPublicTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 群防群治组织ID */
    @Excel(name = "群防群治组织ID")
    private String publicId;

    /** 群防群治队伍ID */
    @Excel(name = "群防群治队伍ID")
    private String publicteamId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;



}
