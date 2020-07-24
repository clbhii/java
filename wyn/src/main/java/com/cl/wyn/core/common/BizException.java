package com.cl.wyn.core.common;

/**
 * by cl at 2020/7/2 0002
 */

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;

    public BizException() {
        super();
    }

    public BizException(BaseErrorCode errorCode) {
        super(errorCode.getResultMsg());
        this.errorCode = errorCode.getResultCode();
    }

    public BizException(BaseErrorCode errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode.getResultCode();
    }

    public BizException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode.getResultMsg(), cause);
        this.errorCode = errorCode.getResultCode();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
    }

    public BizException(BaseErrorCode errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode.getResultCode();
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
