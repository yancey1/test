package com.joseph.api.enums;

public enum PublicCode {

    MULTI_101549("101549", "请求成功"),
    MULTI_101550("101550", "请求失败"),
    MULTI_000516("000516", "错误的算法"),
    MULTI_000519("000519", "读取输入流出错"),
    MULTI_000523("000523", "加密传入参数不正确"),
    MULTI_000072("000072", "参数不正确"),
    MULTI_102931("102931", "电话号码格式不正确"),
    MULTI_102932("102932", "手机号码格式不正确"),
    MULTI_102933("102933", "邮箱格式不正确"),
    MULTI_102934("102934", "域名格式不正确"),
    MULTI_102935("102935", "网址格式不正确"),
    MULTI_102936("102936", "时间格式不正确"),
    MULTI_102937("102937", "实体ID不正确"),
    MULTI_102938("102938", "必须小于指定值的数字"),
    MULTI_102939("102939", "字符串长度必须小于指定值"),
    MULTI_102940("102940", "必须大于指定值的数字"),
    MULTI_102941("102941", "字符串长度必须大于指定值"),
    MULTI_102942("102942", "不能为NULL且不能为空"),
    MULTI_102943("102943", "不能为空"),
    MULTI_102944("102944", "不能为NULL"),
    MULTI_102945("102945", "必须在指定值范围"),
    MULTI_102946("102946", "长度必须在指定长度范围"),
    MULTI_102947("102947", "只能为指定值中的一个"),
    MULTI_102948("102948", "请传入验证参数"),
    MULTI_102949("102949", "暂不支持此类型验证"),;

    private String code;
    private String message;


    private PublicCode(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
