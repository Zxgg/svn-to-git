package com.isstech.common.enums;

/**
 * @author issuser
 */

public enum EventSonStatusEnum {
    /**
     * 事件子状态
     */
    ACCEPTANCE("已受理", 1),
    ASSIGNMENT("未分派", 2),
    COMPLETED("已办结", 3),
    FILING("待办结", 4),
    FILED("已办结", 5),
    ARCHIVING("未归档", 6),
    ARCHIVED("已归档", 7),
    PENDING("待受理", 0);

    private String description;
    private Integer code;

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }

    public String getCodeToString() {
        return code.toString();
    }

    EventSonStatusEnum(String s, Integer i) {
        this.code = i;
        this.description = s;
    }
}
