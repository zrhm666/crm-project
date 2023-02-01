package com.crm.workbench.service.impl;

import com.crm.commons.constants.Constants;
import com.crm.setting.mapper.UserMapper;
import com.crm.workbench.mapper.ActivityMapper;
import com.crm.workbench.pojo.Activity;
import com.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Author: zr
 * Date: 2022/12/31 17:01
 * Description:
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    UserMapper userMapper;

    @Transactional
    public int insertActivity(Activity activity){
        return activityMapper.insertActivity(activity);
    }

    @Override
    public PageInfo<Activity> queryActivityByConditionsForPage(Map<String, Object> map) {
        int pageNo = (Integer) map.get("pageNo");
        int pageSize = (Integer) map.get("pageSize");
        PageHelper.startPage( pageNo,pageSize);
        List<Activity> activityList = activityMapper.queryActivityByConditionsForPage(map);
        PageInfo<Activity> activityPageInfo = new PageInfo<Activity>(activityList);
        return activityPageInfo;
    }

    @Transactional
    public int deleteActivitiesByIds(String[] ids){
        return activityMapper.deleteActivitiesByIds(ids);
    }

    @Override
    public Activity queryActivityById(String id) {
        return activityMapper.queryActivityById(id);
    }

    @Override
    @Transactional
    public int editActivityByConditions(Activity activity) {
//        activity.setName(userMapper.queryUserIdByName(activity.getOwner()));
        return activityMapper.editActivityByConditions(activity);
    }

    @Override
    public List<Activity> queryAllActivities() {
        return activityMapper.queryAllActivities();
    }

    @Override
    @Transactional
    public int insertActivities(List<Activity> list) {
        int a = activityMapper.insertActivities(list);
        return a;
    }


    @Override
    public List<Activity> queryActivitiesByIds(List<String> ids) {
        return activityMapper.selectActivitiesByIds(ids);
    }

    @Override
    public List<Activity> queryAllActivityIsNotConnect(String clueId) {
        return activityMapper.selectAllActivityIsNotConnect(clueId);
    }

    @Override
    public List<Activity> queryActivityOfClue(String clueId) {
        return activityMapper.selectActivityOfClue(clueId);
    }
}
