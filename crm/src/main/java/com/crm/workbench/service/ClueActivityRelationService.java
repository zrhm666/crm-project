package com.crm.workbench.service;

import com.crm.workbench.pojo.ClueActivityRelation;

import java.util.List;

/**
 * Author: zr
 * Date: 2023/1/20 13:58
 * Description:
 */
public interface ClueActivityRelationService {

    public int connectActivity(List<ClueActivityRelation> relationList);

    public int removeRelationByClueIdAndActId(String clueId,String activityId);
}
