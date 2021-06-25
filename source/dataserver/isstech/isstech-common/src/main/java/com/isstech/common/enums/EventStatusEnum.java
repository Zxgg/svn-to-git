package com.isstech.common.enums;

/**
 * @author issuser
 */

public enum EventStatusEnum {

    /**
     * 事件状态
     */
    ACCEPTANCE("受理", 1),
    ASSIGNMENT("分派", 2),
    COMPLETED("办结", 3),
    FILING("归档", 4),
    PENDING("待受理", 0);

    public String description;
    public Integer code;

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }

    public String getCodeToString() {
        return code.toString();
    }

    EventStatusEnum(String s, Integer i) {
        this.code = i;
        this.description = s;
    }
}
