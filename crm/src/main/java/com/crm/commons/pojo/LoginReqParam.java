package com.crm.commons.pojo;

/**
 * Author: zr
 * Date: 2022/12/18 10:21
 * Description:
 */
public class LoginReqParam {

    private String loginAct;//登录账户
    private String loginPwd;//登录密码
    private String isRemPwd;//是否记住密码（10天）

    public LoginReqParam() {
    }

    public LoginReqParam(String loginAct, String loginPwd, String isRemPwd) {
        this.loginAct = loginAct;
        this.loginPwd = loginPwd;
        this.isRemPwd = isRemPwd;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getIsRemPwd() {
        return isRemPwd;
    }

    public void setIsRemPwd(String isRemPwd) {
        this.isRemPwd = isRemPwd;
    }
}
