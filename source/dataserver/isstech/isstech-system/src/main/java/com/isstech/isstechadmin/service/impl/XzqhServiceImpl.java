package com.isstech.isstechadmin.service.impl;

import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.XzqhTree;
import com.isstech.isstechadmin.service.XzqhService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yurenh
 * @version 1.0
 * @date 2021/1/13 14:09
 */
@Service
public class XzqhServiceImpl implements XzqhService {
    @Override
    public List<XzqhTree> buildxzqhTree(List<XzqhTree> xzqhs) {
        List<XzqhTree> returnList = new ArrayList<XzqhTree>();
        List<Long> tempList = new ArrayList<Long>();
        for (XzqhTree dept : xzqhs) {
            tempList.add(dept.getValue());
        }
        for (Iterator<XzqhTree> iterator = xzqhs.iterator(); iterator.hasNext(); ) {
            XzqhTree xzqh = (XzqhTree) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(xzqh.getDictParentValue())) {
                recursionFn(xzqhs, xzqh);
                returnList.add(xzqh);
            }
        }
        if (returnList.isEmpty()) {
            returnList = xzqhs;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<XzqhTree> list, XzqhTree t) {
        // 得到子节点列表
        List<XzqhTree> childList = getChildList(list, t);
        t.setChildren(childList);
        for (XzqhTree tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<XzqhTree> getChildList(List<XzqhTree> list, XzqhTree t) {
        List<XzqhTree> tlist = new ArrayList<XzqhTree>();
        Iterator<XzqhTree> it = list.iterator();
        while (it.hasNext()) {
            XzqhTree n = (XzqhTree) it.next();
            if (StringUtils.isNotNull(n.getDictParentValue()) && n.getDictParentValue().longValue() == t.getValue().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<XzqhTree> list, XzqhTree t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
