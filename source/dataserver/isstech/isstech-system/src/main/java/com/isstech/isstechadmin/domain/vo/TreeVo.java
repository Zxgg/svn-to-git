package com.isstech.isstechadmin.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  社区菜单
 * @Author yzp
 * @Date 2020/11/26 14:06
 */
@Data
public class TreeVo
{
    /** 数据的ID */
    private String id;

    /** 名称 */
    private String name;

    /** 父节点ID */
    private String parentId;

    /** 多个部门组织 */
    private String deptIds;

    /** 表名作为类型 */
    private String type;

    /** 子节点 */
    private List<TreeVo> children = new ArrayList<>();


}
