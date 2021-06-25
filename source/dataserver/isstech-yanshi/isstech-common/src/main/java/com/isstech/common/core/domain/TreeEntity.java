package com.isstech.common.core.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Tree基类
 * @Author yzp
 * @Date 2020/12/14 15:50
 */
@Data
public class TreeEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private String parentId;

    /** 显示顺序 */
    private Integer orderNum;

    /** 祖级列表 */
    private String ancestors;

    /** 子部门 */
    private List<?> children = new ArrayList<>();


}