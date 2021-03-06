package com.cl.multigradle.api.common;


import java.io.Serializable;

public interface Result<T>
        extends Serializable {

    boolean isSuccess();

    void setSuccess(boolean paramBoolean);

    T getDataObject();

    void setDataObject(T paramT);

    String getCode();

    void setCode(String paramString);

    String getMessage();

    void setMessage(String paramString);
}
