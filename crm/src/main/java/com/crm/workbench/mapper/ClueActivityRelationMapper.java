package com.crm.workbench.mapper;

import com.crm.workbench.pojo.ClueActivityRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClueActivityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int insert(ClueActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int insertSelective(ClueActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    ClueActivityRelation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int updateByPrimaryKeySelective(ClueActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Wed Jan 18 14:58:54 CST 2023
     */
    int updateByPrimaryKey(ClueActivityRelation record);

    int insertRelations(List<ClueActivityRelation> relationList);

    int deleteRelationByClueIdAndActId(@Param("clueId") String clueId,@Param("activityId") String activityId);

    List<String> selectActIdsByClueId(@Param("clueId") String clueId);

    int deleteClueActRelationsByClueId(@Param("clueId") String clueId);
}