package com.cherry.dao;

import com.cherry.entity.User;

import java.util.List;

/**
 * @author zhangpcxy@163.com
 * @create 2018/7/9 9:59
 * @desc
 */
public interface IUserDao {

    List<User> selectUser(Integer id);

}
