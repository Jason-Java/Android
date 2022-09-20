package com.jason;

import com.jason.mapper.UserMapper;
import com.jason.service.MenuService;
import com.jason.service.impl.MenuServiceImpl;
import com.jason.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void contextLoads() {
        String token = jwtUtil.generateToken("张三");
        System.out.println(token);
        String name = jwtUtil.getUsernameFromToken(token);
        System.out.println("name===>"+name);
    }


    @Autowired
    private MenuService menuService;

    @Test
    void testMenuService() {
        System.out.println(menuService.getMenuByUserName("zhangsan"));
    }

}
