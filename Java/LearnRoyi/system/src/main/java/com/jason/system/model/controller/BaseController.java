package com.jason.system.model.controller;

import com.jason.system.util.SecurityUtil;

/**
 * <p>描述:
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/10 22:05
 * @see
 */
public class BaseController {

    /**
     * 用户Id
     * @return
     */
    protected Long getUserId() {
       return SecurityUtil.getUserId();
    }

    /**
     * 获取用户名
     * @return
     */
    protected String getUserName() {
        return SecurityUtil.getUserName();
    }


}
