package com.cherry.service;

import com.cherry.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * @author zhangpcxy@163.com
 * @create 2018/7/9 10:21
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class IUserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void selectUser() {
        Integer id = 1;
        List<User> student = userService.selectUser(id);
        System.out.println(student.toString());
    }
}