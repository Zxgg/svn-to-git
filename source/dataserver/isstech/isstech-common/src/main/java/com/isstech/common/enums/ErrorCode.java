package com.isstech.common.enums;

/**
 * @Description 错误码
 * @Author yzp
 * @Date 2020/12/18 15:29
 */
public enum ErrorCode {

    SYSTEM_ERROR(500, "服务异常"),

    BAD_REQUEST(400, "请求参数异常"),

    SYSTEM_ERROR_202(202, "已经接受请求，但未处理完成"),

    SYSTEM_ERROR_301(301, "Moved Permanently,资源的URI已更新"),
    SYSTEM_ERROR_301_001(301_001, "资源的版本已更新"),

    SYSTEM_ERROR_304(304, "资源未更改"),

    SYSTEM_ERROR_403(403, "未登录！"),
    SYSTEM_ERROR_404(404, "资源不存在"),
    SYSTEM_ERROR_406(406, "服务端不支持所需表示"),
    SYSTEM_ERROR_409(409, "通用冲突"),
    SYSTEM_ERROR_412(412, "服务器未满足请求者在请求中设置的其中一个前提条件"),
    SYSTEM_ERROR_412_001(412_001, "前置条件失败（如执行条件更新时的冲突）"),
    SYSTEM_ERROR_415(415, "接受到的表示不受支持"),
    SYSTEM_ERROR_416(416, "数据已变更"),
    SYSTEM_ERROR_417(417, "查询的数据不存在"),
    SYSTEM_ERROR_418(418, "不支持请求方法"),

    SYSTEM_ERROR_503(503, "服务端当前无法处理请求"),
    SYSTEM_ERROR_1001(1001, "请求vss服务异常"),
    SYSTEM_ERROR_1002(1002, "请求vcm/vcn服务异常"),
    SYSTEM_ERROR_1003(1003, "user not login."),
    SYSTEM_ERROR_1004(1004, "请求vas服务异常"),

    BAIDU_ERROR_302(302, "未登录！"),
    BAIDU_ERROR_400(400, "请求数据合法性校验失败！"),
    BAIDU_ERROR_404(404, "未找到匹配接口！"),
    BAIDU_ERROR_500(500, "服务异常！"),
    ;

    private final Integer value;
    private final String message;

    ErrorCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public String getCode() {
        return value.toString();
    }

    public static ErrorCode getByCode(Integer value) {
        for (ErrorCode _enum : values()) {
            if (_enum.getValue() == value) {
                return _enum;
            }
        }
        return null;
    }

}
