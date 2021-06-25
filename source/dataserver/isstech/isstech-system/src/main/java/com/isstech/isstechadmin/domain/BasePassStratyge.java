package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 通行策略 对象 base_pass_stratyge
 * 
 * @author yzp
 * @date 2021-02-01
 */
@Data
public class BasePassStratyge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

    /** 通行策略_预约访客 */
    @Excel(name = "通行策略_预约访客")
    private String passstrategyVistor;

    /** 通行策略_访问时间段 */
    @Excel(name = "通行策略_访问时间段")
    private String passstrategyVistorTimespan;

    /** 通行策略_临时访客 */
    @Excel(name = "通行策略_临时访客")
    private String passstrategyTempvistor;

    /** 通行策略_临时访客访问时间段 */
    @Excel(name = "通行策略_临时访客访问时间段")
    private String passstrategyTempvistorTimespan;

    /** 通行策略_黑名单人员 */
    @Excel(name = "通行策略_黑名单人员")
    private String passstrategyBlackface;



}
