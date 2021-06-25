package com.isstech.isstechadmin.service;

import com.isstech.isstechadmin.domain.XzqhTree;

import java.util.List;

/**
 * @author yurenh
 * @version 1.0
 * @date 2021/1/13 14:03
 */

public interface XzqhService {
    /**
     * 构建前端所需要树结构
     *
     * @param xzqhs 部门列表
     * @return 树结构列表
     */
    public List<XzqhTree> buildxzqhTree(List<XzqhTree> xzqhs);



}
