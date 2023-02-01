package com.crm.workbench.service.impl;

import com.crm.workbench.mapper.ActivityMapper;
import com.crm.workbench.mapper.ActivityRemarkMapper;
import com.crm.workbench.pojo.ActivityRemark;
import com.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: zr
 * Date: 2023/1/14 11:48
 * Description:
 */
@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> queryActRemarkByActId(String activityId) {
        List<ActivityRemark> actRemList = activityRemarkMapper.queryActRemarkByActId(activityId);
        return actRemList;
    }

    public int insertActivityRemark(ActivityRemark activityRemark){
        return activityRemarkMapper.insertActivityRemark(activityRemark);
    }

    @Override
    public ActivityRemark queryActRemarkByRemId(String remarkId) {
        return activityRemarkMapper.selectByPrimaryKey(remarkId);
    }

    @Override
    public int deleteActRemarkByRemId(String remarkId) {
        return activityRemarkMapper.deleteByPrimaryKey(remarkId);
    }

    @Override
    public int updateActivityRemarkByRemId(ActivityRemark remark) {
        return activityRemarkMapper.updateActivityRemarkByRemId(remark);
    }
}
