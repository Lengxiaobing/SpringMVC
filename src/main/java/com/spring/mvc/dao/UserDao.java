package com.spring.mvc.dao;

import com.spring.mvc.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户操作接口
 * @Author: ZX
 * @Date: 2019/2/21 15:36
 */
public interface UserDao {

    /**
     * 根据用户名查询用户列表
     *
     * @param username
     * @return
     */
    List<User> queryUserListByUsername(String username);

    /**
     * 根据用户名和性别查询用户列表
     *
     * @param username
     * @param sex
     * @return
     */
    List<User> queryUserListByUsernameAndSex(@Param("username") String username, @Param("sex") int sex);

}