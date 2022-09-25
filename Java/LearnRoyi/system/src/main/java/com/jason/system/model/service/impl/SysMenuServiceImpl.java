package com.jason.system.model.service.impl;

import com.jason.system.model.domain.SysUser;
import com.jason.system.model.mapper.SysMenuMapper;
import com.jason.system.model.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;


    /**
     * 根据角色id获取权限信息
     *
     * @param userId 用户Id
     * @return 权限信息集合
     */
    @Override
    public List<String> selectMenuPermsByUserId(Long userId) {

        List<String> perms = new ArrayList<>();
        if (SysUser.isAdmin(userId)) {
            perms.add("*:*:*");
            return perms;
        }

        perms.addAll(menuMapper.selectMenuPermsByUserId(userId));
        return perms;
    }
}
