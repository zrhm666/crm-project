package com.crm.setting.mapper;

import com.crm.setting.pojo.DicType;

public interface DicTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Wed Jan 18 14:57:42 CST 2023
     */
    int deleteByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Wed Jan 18 14:57:42 CST 2023
     */
    int insert(DicType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Wed Jan 18 14:57:42 CST 2023
     */
    int insertSelective(DicType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Wed Jan 18 14:57:42 CST 2023
     */
    DicType selectByPrimaryKey(String code);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Wed Jan 18 14:57:42 CST 2023
     */
    int updateByPrimaryKeySelective(DicType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_type
     *
     * @mbggenerated Wed Jan 18 14:57:42 CST 2023
     */
    int updateByPrimaryKey(DicType record);
}