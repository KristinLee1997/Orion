package com.aries.orion.constants;


public enum SystemStatus {
    SUCCESS(1000, "成功生效"),
    NOT_CHANGED(1001, "成功但无变化"),
    WRONG_ANSWER(1002, "答案错误"),
    PARAM_ERROR(2000, "未知参数错误"),
    PARAM_ILLEGAL(2001, "参数非法（参数不全，参数格式错误等）"),
    PARAM_NULL(2002, "参数为null"),
    SYSTEM_ERROR(3000, "系统内部未知异常"),
    OTHERS_SYSTEM_ERROR(3001, "调用其他系统异常"),
    PERMISSION_FAIL(3002, "权限异常"),
    DATABASE_ERROR(3003, "数据库错误"),
    HOPE_RETRY(4001, "希望调用方重试");
    private Integer  code;
    private String message;

    SystemStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

