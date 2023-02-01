package com.crm.workbench.web.controller;

import com.crm.commons.constants.Constants;
import com.crm.commons.pojo.LoginReturn;
import com.crm.commons.utils.DateUtil;
import com.crm.commons.utils.UUIDUtil;
import com.crm.setting.mapper.DicValueMapper;
import com.crm.setting.pojo.DicValue;
import com.crm.setting.pojo.User;
import com.crm.setting.service.DicValueService;
import com.crm.setting.service.UserSerice;
import com.crm.workbench.pojo.Activity;
import com.crm.workbench.pojo.Clue;
import com.crm.workbench.pojo.ClueActivityRelation;
import com.crm.workbench.service.ActivityService;
import com.crm.workbench.service.ClueActivityRelationService;
import com.crm.workbench.service.ClueService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: zr
 * Date: 2023/1/18 13:50
 * Description:
 */
@Controller
public class ClueController {
    @Autowired
    DicValueService dicValueService;
    @Autowired
    UserSerice userSerice;
    @Autowired
    ClueService clueService;

    @Autowired
    ActivityService activityService;


    @Autowired
    ClueActivityRelationService clueActivityRelationService;

    @RequestMapping("/workbench/clue/index.do")
    public String toIndex(HttpServletRequest request){
        List<DicValue> appellationList = dicValueService.queryDicValueByDivType(Constants.DIV_TYPE_APPELLATION);
        List<DicValue> clueStateList = dicValueService.queryDicValueByDivType(Constants.DIV_TYPE_CLUE_STATE);
        List<DicValue> sourceList = dicValueService.queryDicValueByDivType(Constants.DIV_TYPE_SOURCE);
        List<User> userList = userSerice.queryAllUsers();
        request.setAttribute(Constants.REQUEST_DIV_TYPE_APPELLATION,appellationList);
        request.setAttribute(Constants.REQUEST_DIV_TYPE_CLUE_STATE,clueStateList);
        request.setAttribute(Constants.REQUEST_DIV_TYPE_SOURCE,sourceList);
        request.setAttribute(Constants.REQUEST_ALLUSERS,userList);
        return "workbench/clue/index";
    }

    @RequestMapping("/workbench/clue/addClue.do")
    @ResponseBody
    public Object addClue(@RequestBody Clue clue,HttpSession session){
        System.out.println("线索在呢");
        User user = (User)session.getAttribute(Constants.SESSION_USER);
        LoginReturn aReturn = new LoginReturn();

        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateUtil.formatDateTime(new Date()));
        clue.setCreateBy(user.getId());

        try {
            int ret = clueService.insertClue(clue);
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            aReturn.setMessage("系统繁忙，请稍后重试...");
        }

        return aReturn;
    }

    @RequestMapping("/workbench/clue/queryAllClue.do")
    @ResponseBody
    public Object queryAllClue(int pageNo, int pageSize){
        LoginReturn aReturn = new LoginReturn();
        PageInfo<Clue> cluePageInfo = clueService.queryAllClue(pageNo,pageSize);
        return cluePageInfo;
    }

    @RequestMapping("/workbench/clue/toDetail.do")
    public String toDetail(String id,HttpServletRequest request){
        Clue clue = clueService.queryClueById(id);
        request.setAttribute("clue",clue);
        return "workbench/clue/detail";
    }

    @RequestMapping("/workbench/clue/queryAllActivityIsNotConnect.do")
    @ResponseBody
    public Object queryAllActivityIsNotConnect(String clueId){
        System.out.println("在查了");
        LoginReturn aReturn = new LoginReturn();
        List<Activity> activityList = null;

        try {
            activityList = activityService.queryAllActivityIsNotConnect(clueId);
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            aReturn.setRemain(activityList);
        } catch (Exception e) {
            e.printStackTrace();
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            aReturn.setMessage("系统繁忙，请稍后重试...");
        }
        return aReturn;
    }

    @RequestMapping("/workbench/clue/connectActivity.do")
    @ResponseBody
    public Object connectActivity(@RequestBody Map<String,Object> paramMap){
        LoginReturn aReturn = new LoginReturn();
        List<ClueActivityRelation> relationList = new ArrayList<ClueActivityRelation>();

        String clueId = (String) paramMap.get("clueId");
        List<String> ids = (List<String>) paramMap.get("ids");
        for (int i=0;i<ids.size();i++) {
            ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
            clueActivityRelation.setId(UUIDUtil.getUUID());
            clueActivityRelation.setClueId(clueId);
            clueActivityRelation.setActivityId(ids.get(i));
            relationList.add(clueActivityRelation);
        }

        try {
            int ret = clueActivityRelationService.connectActivity(relationList);
            List<Activity> activityList = activityService.queryActivitiesByIds(ids);
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            aReturn.setRemain(activityList);
        } catch (Exception e) {
            e.printStackTrace();
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            aReturn.setMessage("系统繁忙，请稍后重试...");
        }

        return aReturn;
    }

    @RequestMapping("/workbench/clue/queryActivityOfClue.do")
    @ResponseBody
    public Object queryActivityOfClue(String clueId){
        LoginReturn aReturn = new LoginReturn();
        List<Activity> activityList = activityService.queryActivityOfClue(clueId);
        aReturn.setRemain(activityList);
        return aReturn;
    }

    @RequestMapping("/workbench/clue/removeActivityAndClueRelation.do")
    @ResponseBody
    public Object removeActivityAndClueRelation(String clueId,String activityId){
        LoginReturn aReturn = new LoginReturn();

        try {
            int ret = clueActivityRelationService.removeRelationByClueIdAndActId(clueId,activityId);
            if (ret == 1) {
                aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                aReturn.setMessage("系统繁忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            aReturn.setMessage("系统繁忙，请稍后重试...");
        }

        return aReturn;
    }

    @RequestMapping("/workbench/clue/toConvert.do")
    public String toConvert(String clueId,HttpServletRequest request){
        Clue clue = clueService.queryClueById(clueId);
        List<DicValue> stage = dicValueService.queryDicValueByDivType("stage");
        request.setAttribute("stage",stage);
        request.setAttribute("clue",clue);
        return "workbench/clue/convert";
    }

    @RequestMapping("/workbench/clue/clueConvert.do")
    @ResponseBody
    public Object clueConvert(@RequestBody Map<String,Object> map,HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        LoginReturn aReturn = new LoginReturn();
        map.put(Constants.SESSION_USER,user);
        try {
            clueService.clueConvert(map);
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            aReturn.setMessage("系统繁忙，请稍后重试...");
        }

        return aReturn;
    }
}
