package com.jason.service.impl;

import com.jason.domain.Menu;
import com.jason.mapper.MenuMapper;
import com.jason.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper mapper;
    /**
     * 根据用户名称获取相应的权限
     *
     * @param userName
     * @return
     */
    @Override
    public List<Menu> getMenuByUserName(String userName) {
        if (userName == null || userName == "") {
            return null;
        }

        return  mapper.selectByUserName(userName);
    }
}
