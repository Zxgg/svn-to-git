package com.isstech.isstechadmin.domain.vo;

import lombok.Data;

/**
 * @Description 分组统计返回实体
 * @Author yzp
 * @Date 2020/12/1 17:14
 */
@Data
public class GroupByVo {
    /**
     * 返回数量
     */
    private int num;
    /**
     * 当日数量
     */
    private int today;

    /**
     * 返回名称
     */
    private String name;


    /**
     * @autor qishuwei
     */
    public GroupByVo(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public GroupByVo() {

    }
}
