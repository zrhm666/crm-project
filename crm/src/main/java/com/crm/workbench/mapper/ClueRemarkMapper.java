package com.crm.workbench.mapper;

import com.crm.workbench.pojo.ClueRemark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClueRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int insert(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int insertSelective(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    ClueRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int updateByPrimaryKeySelective(ClueRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_remark
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int updateByPrimaryKey(ClueRemark record);

    List<ClueRemark> selectByClueId(@Param("clueId") String clueId);

    int deleteClueRemarksByClueId(@Param("clueId")String clueId);
}