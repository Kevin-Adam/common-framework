package com.cherry.service.impl;

import com.cherry.dao.IUserDao;
import com.cherry.entity.User;
import com.cherry.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangpcxy@163.com
 * @create 2018/7/9 10:01
 * @desc
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao studentDao;

    public List<User> selectUser(Integer id) {

        return this.studentDao.selectUser(id);
    }
}
