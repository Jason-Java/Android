package com.jason.service;

import com.jason.domain.Menu;

import java.util.ArrayList;
import java.util.List;

public interface MenuService {

    /**
     * 根据用户名称获取相应的权限
     * @param userName
     * @return
     */
     List<Menu> getMenuByUserName(String userName) ;
}
