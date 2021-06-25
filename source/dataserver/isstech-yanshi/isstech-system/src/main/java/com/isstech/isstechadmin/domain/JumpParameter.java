package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 轨迹跳转参数对象 jump_parameter
 * 
 * @author yzp
 * @date 2021-01-19
 */
@Data
public class JumpParameter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 参数值 */
    @Excel(name = "参数值")
    private String parameterVal;

    /** 此设置项是属于哪一个用户，为空表示是全体用户 */
    @Excel(name = "此设置项是属于哪一个用户，为空表示是全体用户")
    private String creatorId;

    /** 区分字典所属模块 */
    @Excel(name = "区分字典所属模块")
    private String category;



}
