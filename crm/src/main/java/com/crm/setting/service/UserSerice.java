package com.crm.setting.service;

import com.crm.commons.pojo.LoginReqParam;
import com.crm.commons.pojo.LoginReturn;
import com.crm.setting.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Author: zr
 * Date: 2022/12/18 19:17
 * Description:
 */
public interface UserSerice {
    public LoginReturn IfUserCorrect(User user,LoginReqParam loginReqParam, String removeAddr);

    public User queryUserByActAndPwd(LoginReqParam loginParam);

//    public void deliveryCookieRemLogin(LoginReqParam loginReqParam, User user, HttpServletResponse response);
    public List<User> queryAllUsers();
}
