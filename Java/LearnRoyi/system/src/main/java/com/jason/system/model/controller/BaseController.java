package com.jason.system.model.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jason.system.model.domain.DataTableInfo;
import com.jason.system.util.PageUtil;
import com.jason.system.util.SecurityUtil;

import java.util.List;

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

    protected void startPage() {
        PageUtil.startPage();
    }

    protected DataTableInfo getDateTable(List<?> list)
    {
        DataTableInfo tableInfo = new DataTableInfo();
        tableInfo.setCode(200);
        tableInfo.setMsg("查询成功");
        Page page= (Page) list;
        tableInfo.setTotal(page.getTotal());
        tableInfo.setData(list);
        return tableInfo;
    }


}
