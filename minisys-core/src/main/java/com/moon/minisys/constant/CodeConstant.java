package com.moon.minisys.constant;

/**
 * 错误编码
 * @author yujiangtao
 * @date 2018/4/30 13:47
 */
public enum CodeConstant {
    SUCCESS("0000", "标识成功"),
    FAILURE("9999", "标识失败");

    private String code;
    private String desc;

    private CodeConstant(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
