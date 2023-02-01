package com.crm.workbench.web.controller;

import com.crm.commons.constants.Constants;
import com.crm.commons.pojo.LoginReturn;
import com.crm.commons.utils.DateUtil;
import com.crm.commons.utils.HSSFUtil;
import com.crm.commons.utils.UUIDUtil;
import com.crm.setting.pojo.User;
import com.crm.setting.service.UserSerice;
import com.crm.workbench.mapper.ActivityMapper;
import com.crm.workbench.mapper.ActivityRemarkMapper;
import com.crm.workbench.pojo.Activity;
import com.crm.workbench.pojo.ActivityRemark;
import com.crm.workbench.service.ActivityRemarkService;
import com.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Author: zr
 * Date: 2022/12/31 10:39
 * Description:
 */
@Controller
public class ActivityController {
    @Autowired
    UserSerice userSerice;
    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/index.do")
    public String toIndex(HttpServletRequest request) {
        List<User> users = userSerice.queryAllUsers();
        request.setAttribute(Constants.REQUEST_ALLUSER, users);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/addActivity.do")
    @ResponseBody
    public Object saveActivity(@RequestBody Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        LoginReturn loginReturn = new LoginReturn();

        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateUtil.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());

        try {
            int ret = activityService.insertActivity(activity);
            if (ret > 0) {
                loginReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                loginReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                loginReturn.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            loginReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            loginReturn.setMessage("系统忙，请稍后重试...");
        }
        return loginReturn;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionsForPage.do")
    @ResponseBody
    public PageInfo<Activity> queryActivityByConditionsForPage(@RequestBody Map<String, Object> param) {
        System.out.println("+++++++++++++++++++++++++++++++++" + param.get("owner"));
        PageInfo<Activity> activityPageInfo = activityService.queryActivityByConditionsForPage(param);
        return activityPageInfo;
    }

    @RequestMapping("/workbench/activity/deleteActivitiesByIds.do")
    @ResponseBody
    public Object deleteActivitiesByIds(@RequestBody String[] ids) {
//        for (String id:ids){
//            System.out.println("id:"+id);
//        }
        int ret = activityService.deleteActivitiesByIds(ids);
        LoginReturn aReturn = new LoginReturn();
        try {
            if (ret > 0) {
                aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
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

    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(@RequestParam String id) {
        System.out.println("queryActivityById:" + id);
        Activity activity = activityService.queryActivityById(id);
        System.out.println("我是owner" + activity.getOwner());
        return activity;
    }

    @RequestMapping("/workbench/activity/editActivityByConditions.do")
    @ResponseBody
    public Object editActivityByConditions(@RequestBody Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        activity.setEditBy(user.getId());
        activity.setEditTime(DateUtil.formatDateTime(new Date()));
        int ret = activityService.editActivityByConditions(activity);
        LoginReturn aReturn = new LoginReturn();
        try {
            if (ret > 0) {
                aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
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

    @RequestMapping("/workbench/activity/queryAllActivitiesToExcel.do")
    public void queryAllActivitiesToExcel(HttpServletResponse response) throws IOException {
        List<Activity> activityList = activityService.queryAllActivities();
        response.setContentType("application/octet-steam;charset=UTF-8");
        response.addHeader("Content-Disposition", "attendment;filename=activityList.xlsx");
        ServletOutputStream out = response.getOutputStream();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("活动信息列表");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("所有者");
        row.createCell(2).setCellValue("名字");
        row.createCell(3).setCellValue("开始日期");
        row.createCell(4).setCellValue("结束日期");
        row.createCell(5).setCellValue("成本");
        row.createCell(6).setCellValue("描述");
        row.createCell(7).setCellValue("创建时间");
        row.createCell(8).setCellValue("创建者");
        row.createCell(9).setCellValue("上一次修改时间");
        row.createCell(10).setCellValue("修改者");
        for (int i = 0; i < activityList.size(); i++) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(activityList.get(i).getId());
            row.createCell(1).setCellValue(activityList.get(i).getOwner());
            row.createCell(2).setCellValue(activityList.get(i).getName());
            row.createCell(3).setCellValue(activityList.get(i).getStartDate());
            row.createCell(4).setCellValue(activityList.get(i).getEndDate());
            row.createCell(5).setCellValue(activityList.get(i).getCost());
            row.createCell(6).setCellValue(activityList.get(i).getDescription());
            row.createCell(7).setCellValue(activityList.get(i).getCreateTime());
            row.createCell(8).setCellValue(activityList.get(i).getCreateBy());
            row.createCell(9).setCellValue(activityList.get(i).getEditTime());
            row.createCell(10).setCellValue(activityList.get(i).getEditBy());
        }
        wb.write(out);

        out.flush();
        wb.close();
    }


    @RequestMapping("/workbench/activity/insertActivitiesFromExcel.do")
    @ResponseBody
    public Object insertActivitiesFromExcel( MultipartFile activityFile, HttpSession session) throws IOException {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        if (activityFile==null) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }else {
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        }
        HSSFWorkbook workbook = new HSSFWorkbook(activityFile.getInputStream());
        HSSFSheet sheet = workbook.getSheetAt(0);
        List<Activity> activityList = new ArrayList<Activity>();
        LoginReturn aReturn = new LoginReturn();

        try {
            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                Activity activity = new Activity();
                activity.setId(UUIDUtil.getUUID());
                activity.setOwner(user.getId());
                activity.setCreateBy(user.getId());
                activity.setCreateTime(DateUtil.formatDateTime(new Date()));
                HSSFRow row = sheet.getRow(j);
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    String value = HSSFUtil.getCellValueForStr(row.getCell(i));
                    if (i == 0) {
                        activity.setName(value);
                    } else if (i == 1) {
                        activity.setStartDate(value);
                    } else if (i == 2) {
                        activity.setEndDate(value);
                    } else if (i == 3) {
                        activity.setCost(value);
                    } else if (i == 4) {
                        activity.setDescription(value);
                    }
                }
                activityList.add(activity);
            }

            int ret = activityService.insertActivities(activityList);
            aReturn.setRemain(ret);
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            aReturn.setMessage("系统繁忙，请稍后重试...");
        }
        return aReturn;
    }

    @RequestMapping("/workbench/activity/activityDetail.do")
    public String toActivityDetail(String id,HttpServletRequest request){
        Activity activity = activityService.queryActivityById(id);
        List<ActivityRemark> remarkList = activityRemarkService.queryActRemarkByActId(id);
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList",remarkList);
        return "workbench/activity/detail.html";
    }

    @RequestMapping("/workbench/activity/addActivityRemarkAndReturnBack.do")
    @ResponseBody
    public Object addActivityRemarkAndReturnBack(@RequestBody ActivityRemark activityRemark,HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        LoginReturn aReturn = new LoginReturn();

        activityRemark.setId(UUIDUtil.getUUID());
        activityRemark.setCreateBy(user.getId());
        activityRemark.setCreateTime(DateUtil.formatDateTime(new Date()));
        activityRemark.setEditFlag("0");

        try {
            int ret = activityRemarkService.insertActivityRemark(activityRemark);
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            aReturn.setRemain(activityRemark);
        } catch (Exception e) {
            e.printStackTrace();
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            aReturn.setMessage("系统繁忙，请稍后重试...");
        }

        return aReturn;
    }

    @RequestMapping("/workbench/activity/deleteActivityRemarkByRemarkId.do")
    @ResponseBody
    public Object deleteActivityRemarkByRemarkId(String remarkId,HttpSession session){
        LoginReturn aReturn = new LoginReturn();
        User user = (User)session.getAttribute(Constants.SESSION_USER);

        ActivityRemark activityRemark = activityRemarkService.queryActRemarkByRemId(remarkId);
        if (!user.getId().equals(activityRemark.getCreateBy())){
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            aReturn.setMessage("没有权限删除该备注");
        }else{
            try {
                int ret = activityRemarkService.deleteActRemarkByRemId(remarkId);
                aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                aReturn.setMessage("系统繁忙，请稍后重试...");
            }
        }

        return aReturn;
    }

    @RequestMapping("/workbench/activity/editActivityRemarkByRemId.do")
    @ResponseBody
    public Object editActivityRemarkByRemId(String remarkId,String noteContent,HttpSession session){
        LoginReturn aReturn = new LoginReturn();
        User user = (User)session.getAttribute(Constants.SESSION_USER);

        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setId(remarkId);
        activityRemark.setNoteContent(noteContent);
        activityRemark.setEditTime(DateUtil.formatDateTime(new Date()));
        activityRemark.setEditBy(user.getId());
        activityRemark.setEditFlag("1");

        try {
            int ret = activityRemarkService.updateActivityRemarkByRemId(activityRemark);
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            aReturn.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            aReturn.setMessage("系统繁忙，请稍后重试...");
        }

        activityRemark.setEditBy(user.getName());
        aReturn.setRemain(activityRemark);
        return aReturn;
    }
}
