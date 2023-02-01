package com.crm.commons.pojo;

/**
 * Author: zr
 * Date: 2022/12/18 19:17
 * Description:
 */
public class LoginReturn {

    private String code;//是否登录成功

    private String message;//错误消息

    private Object remain;//剩余消息

    public Object getRemain() {
        return remain;
    }

    public void setRemain(Object remain) {
        this.remain = remain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
