package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 综治机构和综治队伍关联对象 base_org_basic_org_basic_team
 * 
 * @author yzp
 * @date 2021-01-29
 */
@Data
public class BaseOrgBasicOrgBasicTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 综治机构ID */
    @Excel(name = "综治机构ID")
    private String basicId;

    /** 综治队伍ID */
    @Excel(name = "综治队伍ID")
    private String teamId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;



}
