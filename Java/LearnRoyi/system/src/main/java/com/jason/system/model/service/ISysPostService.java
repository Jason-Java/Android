package com.jason.system.model.service;

import com.jason.system.model.domain.SysPost;

import java.util.List;

public interface ISysPostService {


    /**
     * 查询所有的岗位
     * @return
     */
    List<SysPost> selectPostAll();

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    List<Long> selectPostListByUserId(Long userId);
}
