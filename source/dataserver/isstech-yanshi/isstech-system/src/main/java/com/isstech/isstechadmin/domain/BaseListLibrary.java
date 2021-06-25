package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 名单库对象 base_list_library
 * 
 * @author yzp
 * @date 2021-01-05
 */
@Data
public class BaseListLibrary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 名单id */
    @Excel(name = "名单id")
    private String listId;

    /** 名单 */
    @Excel(name = "名单")
    private String listName;

    /** 名单类型（1-车辆黑名单，2-人员黑名单，3-人员白名单） */
    @Excel(name = "名单类型", readConverterExp = "1=-车辆黑名单，2-人员黑名单，3-人员白名单")
    private String type;

    /** 描述 */
    @Excel(name = "描述")
    private String describes;

    /** 删除标志 */
    private String delFlag;



}
