package com.xiaoxiong.nbst01.common;

/**
 * 错误码对应数据库error_code表的Key值
 * Created by Administrator on 2017/2/23 0023.
 */
public enum ErrorCode {
    RESULT_SUCCESS(1,"成功"),
    EXCEPTION_ERR(20,"数据异常"),
    ACCOUNT_OR_PASSWORD_NULL(21,"帐号或密码为空"),
    ACCOUNT_EXIST(22,"帐号已存在"),
    ACCOUNT_NOT_EXIST(23,"帐号不存在"),
    ACCOUNT_OR_PASSWORD_ERR(24,"帐号或密码错误"),
    ACCOUNT_INFO_NULL(25,"个人信息为空"),

    PRODUCT_NAME_NULL(30,"产品名称不能为空");
    private int code;
    private String message;


    ErrorCode(int code) {
        this.code = code;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
