package com.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: zr
 * Date: 2022/12/31 9:26
 * Description:
 */
@Controller
public class MainController {

    @RequestMapping("/workbench/main/index.do")
    public String toIndex(){
        return "workbench/main/index";
    }
}
