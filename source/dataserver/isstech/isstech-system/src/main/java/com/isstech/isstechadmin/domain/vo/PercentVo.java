package com.isstech.isstechadmin.domain.vo;

import java.io.Serializable;

public class PercentVo implements Serializable {

    private String name;

    private String percent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public PercentVo(String name, String percent) {
        this.name = name;
        this.percent = percent;
    }

    public PercentVo(){

    }
}
