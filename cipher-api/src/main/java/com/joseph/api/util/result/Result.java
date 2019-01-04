package com.joseph.api.util.result;

import java.io.Serializable;
import java.util.List;

public interface Result<T> extends Serializable {

    void setSuccess(boolean var1);

    boolean isSuccess();

    String getResultCode();

    void setResultCode(String var1);

    String getI18nCode();

    void setI18nCode(String var1);

    T getModel();

    void setModel(T var1);

    String getMessage();

    void setMessage(String var1);
}
