package com.crm.workbench.service;

import com.crm.workbench.pojo.ActivityRemark;

import java.util.List;

/**
 * Author: zr
 * Date: 2023/1/14 11:47
 * Description:
 */
public interface ActivityRemarkService {

    public List<ActivityRemark> queryActRemarkByActId(String activityId);

    public int insertActivityRemark(ActivityRemark activityRemark);

    public ActivityRemark queryActRemarkByRemId(String remarkId);

    public int deleteActRemarkByRemId(String remarkId);

    public int updateActivityRemarkByRemId(ActivityRemark remark);
}
