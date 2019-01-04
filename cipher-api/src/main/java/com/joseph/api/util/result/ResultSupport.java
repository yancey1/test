package com.joseph.api.util.result;

import com.joseph.api.util.resultCode.BaseResultCode;
import com.joseph.api.util.resultCode.ResultCode;

import java.util.ArrayList;
import java.util.List;

public class ResultSupport<T> implements Result<T> {

    private boolean success = true;
    private String resultCode;
    private String i18nCode;
    private String message;
    private T model;
    private int totalRecord;
    private List<T> models = new ArrayList();

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public T getModel() {
        return this.model;
    }

    public int getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public ResultSupport() {
    }

    public ResultSupport(boolean success) {
        this.success = success;
    }

    public ResultSupport(boolean success, String resultCode, String message) {
        this.success = success;
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResultSupport(String resultCode, String message) {
        this.success = Boolean.FALSE;
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResultSupport(ResultCode resultCode) {
        this.success = false;
        this.resultCode = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    @Override
    public String getResultCode() {
        return !this.isSuccess() && isBlank(this.resultCode) ? BaseResultCode.SYSTEM_DEFAULT.getCode() : this.resultCode;
    }

    @Override
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String getI18nCode() {
        return this.i18nCode;
    }

    @Override
    public void setI18nCode(String code) {
        this.i18nCode = code;
    }

    public List<T> getModels() {
        return this.models;
    }

    public void setModels(List<T> models) {
        this.models = models;
    }

    @Override
    public void setModel(T model) {
        this.model = model;
    }
}
