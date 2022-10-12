package com.jason.system.util;

import com.github.pagehelper.PageHelper;

/**
 * <p>描述:分页帮助类
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/12 21:15
 * @see
 */
public class PageUtil extends PageHelper {

    public static void startPage() {
        int pageNum = getPageNum();
        int pageSize = getPageSize();
        boolean reasonable = reasonable();
        PageHelper.startPage(pageNum,pageSize)
                .setReasonable(reasonable)
                .pageSizeZero(true)
                ;
    }


    /**
     * 第几页
     * @return
     */
    private static int getPageNum() {
        Integer pageNum = 0;
        try {
            pageNum = StringUtils.toInt(ServletUtil.getRequestParameter("pageNum"));
        } catch (Exception ignore) {
        }
        return pageNum;
    }

    /**
     * 每页有多少条数据
     * @return
     */
    private static int getPageSize() {
        Integer pageSize = 0;
        try {
            pageSize = StringUtils.toInt(ServletUtil.getRequestParameter("pageSize"));
        } catch (Exception ignore) {
        }
        return pageSize;
    }

    /**
     * 分页是否合理化
     *
     * @return
     */
    private static boolean reasonable()
    {
        String value = ServletUtil.getRequestParameter("reasonable");
        if(StringUtils.isEmpty(value))
        {
            return true;
        }
        return Boolean.parseBoolean(value);
    }



}
