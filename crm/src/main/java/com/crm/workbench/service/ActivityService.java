package com.crm.workbench.service;

import com.crm.workbench.pojo.Activity;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Author: zr
 * Date: 2022/12/31 17:12
 * Description:
 */
public interface ActivityService {

    public int insertActivity(Activity activity);

    public PageInfo<Activity> queryActivityByConditionsForPage(Map<String, Object> map);

    public int deleteActivitiesByIds(String[] ids);

    public Activity queryActivityById(String id);

    public int editActivityByConditions(Activity activity);

    public List<Activity> queryAllActivities();

    public int insertActivities(List<Activity> list);

    public List<Activity> queryActivitiesByIds(List<String> ids);

    public List<Activity> queryAllActivityIsNotConnect(String clueId);

    public List<Activity> queryActivityOfClue(String clueId);
}
