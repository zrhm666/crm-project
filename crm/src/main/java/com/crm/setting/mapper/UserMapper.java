package com.crm.setting.mapper;

import com.crm.commons.pojo.LoginReqParam;
import com.crm.setting.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Sat Dec 17 20:57:11 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Sat Dec 17 20:57:11 CST 2022
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Sat Dec 17 20:57:11 CST 2022
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Sat Dec 17 20:57:11 CST 2022
     */
    User selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Sat Dec 17 20:57:11 CST 2022
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Sat Dec 17 20:57:11 CST 2022
     */
    int updateByPrimaryKey(User record);

    User queryUserByActAndPwd(LoginReqParam loginParam);

    List<User> queryAllUsers();

    String queryUserIdByName(@Param("name") String name);
}