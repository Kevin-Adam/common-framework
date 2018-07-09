package com.cherry.service;

import com.cherry.entity.User;

import java.util.List;

/**
 * @author zhangpcxy@163.com
 * @create 2018/7/9 10:00
 * @desc
 */
public interface IUserService {

    List<User> selectUser(Integer id);

}
