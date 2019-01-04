package com.joseph.api.util.resultCode;

public enum BaseResultCode implements ResultCode {

    SYSTEM_DEFAULT("SYSTEM_DEFAULT_ERROR", "服务端异常"),
    SYSTEM("SYSTEM_ERROR_1001", "系统异常"),
    DUBBO_REMOTE("SYSTEM_ERROR_DUBBO_1002", "服务调用异常"),
    SAVE_DATA("SAVE_DATA_1003", "保存数据失败"),
    UPDATE_DATA("UPDATE_DATA_1003", "修改数据失败"),
    OBJECT_NULL("OBJECT_NULL_1004", "对象不能为空"),
    FIELD_NULL("FIELD_NULL_1005", "属性不能为空"),
    RECORD_NOT_EXIST("RECORD_NOT_EXIST_1006", "记录不存在");

    private String code;

    private String message;

    private BaseResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
