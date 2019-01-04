package com.joseph.api.exceptions;

public class ConsumerException extends RuntimeException {

    private String code;

    public ConsumerException(String code,String message){
        super(message);
        this.code=code;
    }

    public ConsumerException(String code,String message,Throwable throwable){
        super(message,throwable);
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
