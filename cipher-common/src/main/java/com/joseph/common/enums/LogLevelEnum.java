package com.joseph.common.enums;

/**
 * 日志级别:1-debug,2-info,3-warn,4-error
 * */
public enum LogLevelEnum {

  DEBUG("debug", "debug级别"), INFO("info", "info级别"), WARN("warn", "warn级别"), ERROR("error", "error级别");

  private LogLevelEnum(String logLevel, String desc) {
    this.logLevel = logLevel;
    this.desc = desc;
  }

  private String logLevel;
  private String desc;

  public String getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(String logLevel) {
    this.logLevel = logLevel;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

}
