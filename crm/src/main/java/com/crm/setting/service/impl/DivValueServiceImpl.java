package com.crm.setting.service.impl;

import com.crm.setting.mapper.DicValueMapper;
import com.crm.setting.pojo.DicValue;
import com.crm.setting.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: zr
 * Date: 2023/1/18 15:10
 * Description:
 */
@Service
public class DivValueServiceImpl implements DicValueService {
    @Autowired
    DicValueMapper dicValueMapper;

    @Override
    public List<DicValue> queryDicValueByDivType(String typeCode) {
        return dicValueMapper.selectDicValueByDivType(typeCode);
    }
}
