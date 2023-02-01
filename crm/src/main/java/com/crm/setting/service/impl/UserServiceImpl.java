package com.crm.setting.service.impl;

import com.crm.commons.constants.Constants;
import com.crm.commons.pojo.LoginReqParam;
import com.crm.commons.pojo.LoginReturn;
import com.crm.commons.utils.DateUtil;
import com.crm.setting.service.UserSerice;
import com.crm.setting.mapper.UserMapper;
import com.crm.setting.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.CancelablePrintJob;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Author: zr
 * Date: 2022/12/18 19:36
 * Description:
 */
@Service
public class UserServiceImpl implements UserSerice {
    @Autowired
    UserMapper userMapper;

    public LoginReturn IfUserCorrect(User user,LoginReqParam loginReqParam,String removeAddr) {
        LoginReturn loginReturn = new LoginReturn();
        if (user == null){
            loginReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            loginReturn.setMessage("账号或密码错误");
        }else{
            String dateStr = DateUtil.formatDateTime(new Date());
            if (dateStr.compareTo(user.getExpireTime())>0){
                loginReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                loginReturn.setMessage("账号已过期");
            }else if (!user.getAllowIps().contains(removeAddr)){
                loginReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                loginReturn.setMessage("ip受限");
            }else if("0".equals(user.getLockState())){
                loginReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                loginReturn.setMessage("状态被锁定");
            }else{
                loginReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }
        }

        return loginReturn;
    }


    public User queryUserByActAndPwd(LoginReqParam loginParam){
        User user =  userMapper.queryUserByActAndPwd(loginParam);
        return user;
    }

    @Override
    public List<User> queryAllUsers() {
        System.out.println("奇怪了");
        List<User> users = userMapper.queryAllUsers();
        return users;
    }


}
