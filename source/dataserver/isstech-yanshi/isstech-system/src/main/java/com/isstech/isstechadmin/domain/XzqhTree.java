package com.isstech.isstechadmin.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yurenh
 * @version 1.0
 * @date 2021/1/13 11:14
 */
@Data
public class XzqhTree {

    /**
     * 区划编码 id
     */
    private Long Value;
    /**
     * 父区划编码  id
     */

    private Long dictParentValue;
    /**
     * 区划名称
     */
    private String Label;


    /**
     * 子区域
     */
    private List<XzqhTree> children = new ArrayList<XzqhTree>();
}
