package com.crm.workbench.service;

import com.crm.workbench.mapper.ClueMapper;
import com.crm.workbench.pojo.Clue;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author: zr
 * Date: 2023/1/18 16:32
 * Description:
 */
@Service
public interface ClueService {

    public int insertClue(Clue clue);

    public PageInfo<Clue> queryAllClue(int pageNo, int pageSize);

    public Clue queryClueById(String id);

    public void clueConvert(Map<String,Object> map);
}
