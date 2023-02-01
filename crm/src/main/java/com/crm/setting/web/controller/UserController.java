package com.crm.setting.web.controller;

import com.crm.commons.constants.Constants;
import com.crm.commons.pojo.LoginReqParam;
import com.crm.commons.pojo.LoginReturn;
import com.crm.setting.pojo.User;
import com.crm.setting.service.UserSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Author: zr
 * Date: 2022/12/17 16:07
 * Description:
 */
@Controller
public class UserController {
    @Autowired
    UserSerice userSerice;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public LoginReturn login(@RequestBody LoginReqParam loginReqParam, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String removeAddr = request.getRemoteAddr();
        System.out.println("ip:"+removeAddr);
        User user = userSerice.queryUserByActAndPwd(loginReqParam);
        LoginReturn loginReturn = userSerice.IfUserCorrect(user,loginReqParam,removeAddr);

        session.setAttribute(Constants.SESSION_USER,user);
//        userSerice.deliveryCookieRemLogin(loginReqParam,user,response);
        return loginReturn;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        session.invalidate();
        return "redirect:/settings/qx/user/toLogin.do";
    }
    public void aa(){
        
    }
    public void cc(){
    }
    public void dd(){
    }

    public void bb(){

    }
}
