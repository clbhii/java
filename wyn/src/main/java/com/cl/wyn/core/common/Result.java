package com.cl.wyn.core.common;


import java.io.Serializable;

public interface Result<T>
        extends Serializable {

    boolean isSuccess();

    void setSuccess(boolean paramBoolean);

    T getData();

    void setData(T paramT);

    String getCode();

    void setCode(String paramString);

    String getMessage();

    void setMessage(String paramString);
}
