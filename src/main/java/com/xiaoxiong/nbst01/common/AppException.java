package com.xiaoxiong.nbst01.common;

import java.text.MessageFormat;

/**
 * 应用系统异常类，一般处理应用级异常
 */
public class AppException extends RuntimeException {
    private int code;
    private String message;


    /**
     * 获得异常代码
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public static AppException getException(ErrorCode errorCode) {
        return new AppException(errorCode.getCode(), errorCode.getMessage());
    }

    public static AppException getException(int code, String errorDetails) {
        return new AppException(code, errorDetails);
    }

    /**
     * 应用异常封装
     *
     * @param code      错误码
     * @param throwable 异常
     * @return
     * @see #AppException(int, String, Throwable)
     */
    public static AppException getException(int code, Throwable throwable) {
        return new AppException(code, throwable.getMessage(), throwable);
    }

    /**
     * 应用异常封装
     *
     * @param code      错误码
     * @param throwable 异常
     * @param args      信息参数
     * @return
     * @see #AppException(int, String)
     */
    public static AppException getException(int code, Throwable throwable, Object... args) {
        return new AppException(code, throwable.getMessage(), throwable).replaceErrorMessage(args);
    }

    /**
     * 根据定义的异常格式将参数传如形成相应的异常提示
     *
     * @param args 信息参数
     * @return
     */
    private AppException replaceErrorMessage(Object... args) {
        if (null != this.message && !"".equalsIgnoreCase(this.message) && args != null && args.length != 0)
            this.message = MessageFormat.format(this.message, args);
        return this;
    }

    public AppException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

    public AppException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public AppException(String message) {
        super(message);
        this.message = message;
    }

    public AppException(Exception ex) {
        super(ex);
    }
}
