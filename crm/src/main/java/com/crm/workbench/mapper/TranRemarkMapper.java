package com.crm.workbench.mapper;

import com.crm.workbench.pojo.CustomerRemark;
import com.crm.workbench.pojo.TranRemark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TranRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Jan 27 19:55:03 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Jan 27 19:55:03 CST 2023
     */
    int insert(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Jan 27 19:55:03 CST 2023
     */
    int insertSelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Jan 27 19:55:03 CST 2023
     */
    TranRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Jan 27 19:55:03 CST 2023
     */
    int updateByPrimaryKeySelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Jan 27 19:55:03 CST 2023
     */
    int updateByPrimaryKey(TranRemark record);

    int insertTranRemarks(@Param("list") List<TranRemark> TranRemarkList);
}