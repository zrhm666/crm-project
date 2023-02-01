package com.crm.workbench.service.impl;

import com.crm.workbench.mapper.ClueActivityRelationMapper;
import com.crm.workbench.pojo.ClueActivityRelation;
import com.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: zr
 * Date: 2023/1/20 13:58
 * Description:
 */
@Service
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

    @Autowired
    ClueActivityRelationMapper clueActivityRelationMapper;

    public int connectActivity(List<ClueActivityRelation> relationList){
        return clueActivityRelationMapper.insertRelations(relationList);
    }

    @Override
    public int removeRelationByClueIdAndActId(String clueId, String activityId) {
        return clueActivityRelationMapper.deleteRelationByClueIdAndActId(clueId,activityId);
    }
}
