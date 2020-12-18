package com.xys.timemgr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xys.timemgr.entity.User;
import com.xys.timemgr.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class TimemgrApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

    }

    @Test
    void testLogin() {
        String userName = "admin";
        String password = "admin";
        String wrongPassword = "error admin";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("user_account", userName);
        User user = userMapper.selectOne(wrapper);
        assertEquals(password, user.getPassword());
        assertNotEquals(wrongPassword, user.getPassword());
    }

}
