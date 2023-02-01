package com.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: zr
 * Date: 2022/12/26 11:41
 * Description:
 */
@Controller
public class WorkBenchIndexController {

    @RequestMapping("/workbench/index.do")
    public String toIndex(){
        return "workbench/index";
    }
}
