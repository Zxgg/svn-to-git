package com.isstech.isstechadmin.domain.vo;

/**
 * 通用键值对对象
 * @author zengxi
 * @version 1.0
 * @date 2021/5/9 4:32 下午
 **/
public class CommonMap {
    /**
     * 键
     */
    private String name;

    /**
     * 值
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
