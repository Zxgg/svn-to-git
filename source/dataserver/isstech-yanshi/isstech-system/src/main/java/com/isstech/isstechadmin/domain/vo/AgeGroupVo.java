package com.isstech.isstechadmin.domain.vo;

import java.util.List;

/**
 * @author zengxi
 * @version 1.0
 * @date 2021/5/9 4:58 下午
 **/
public class AgeGroupVo {
    /**
     * 年龄段
     */
    List<String> x;

    /**
     * 对应人数
     */
    List<Double> y;

    public List<String> getX() {
        return x;
    }

    public void setX(List<String> x) {
        this.x = x;
    }

    public List<Double> getY() {
        return y;
    }

    public void setY(List<Double> y) {
        this.y = y;
    }
}
