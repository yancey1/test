package com.joseph.api.enums;

public enum TargetEnum {

    PACKAGE("package","包"),
    CLASS("class","类"),
    METHOD("method","方法");

    private String name;

    private String desc;

    TargetEnum(String name,String desc){
        this.name=name;
        this.desc=desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
