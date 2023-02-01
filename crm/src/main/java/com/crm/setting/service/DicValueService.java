package com.crm.setting.service;

import com.crm.setting.pojo.DicValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: zr
 * Date: 2023/1/18 15:09
 * Description:
 */
public interface DicValueService {

    List<DicValue> queryDicValueByDivType(String typeCode);
}
