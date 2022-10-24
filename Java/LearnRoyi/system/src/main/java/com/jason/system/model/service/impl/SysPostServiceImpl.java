package com.jason.system.model.service.impl;

import com.jason.system.model.domain.SysPost;
import com.jason.system.model.mapper.SysPostMapper;
import com.jason.system.model.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>描述:
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/24 21:35
 * @see
 */
@Service
public class SysPostServiceImpl implements ISysPostService {


    @Autowired
    private SysPostMapper postMapper;

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostAll() {
        return postMapper.selectPostAll();
    }


    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId) {
        return postMapper.selectPostListByUserId(userId);
    }
}
