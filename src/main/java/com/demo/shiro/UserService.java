package com.demo.shiro;

import com.demo.shiro.bean.UserBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
/**
 * @Auther: ZHANG.HAO
 * @Date: 2019-02-21 14:59
 * @Description:
 */
@Service
public class UserService {

    DataSource dataSource;

    public UserBean findByName(String username) {
        // 没有此用户直接返回null

        UserBean user = new UserBean();
        Map<String, String> detail = new HashMap<>();

        user.setUsername("123");
        user.setPassword("123");
        user.setRole("user");
        user.setPermission("view");
        return user;

    }
}
