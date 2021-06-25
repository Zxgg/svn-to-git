package com.isstech.common.core.domain;

import lombok.Data;

/**
 * @author lichong<br />
 * @Description: 分页信息<br />
 * @date 2019/10/14  9:33<br/>
 */
@Data
public class PageInfo {

    private int total;

    private int currentPage;

    private int rows;
}
