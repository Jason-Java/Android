package com.jason.system.model.domain;

import java.util.HashMap;
import java.util.List;

/**
 * <p>描述:分页数据模型
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/12 22:43
 * @see
 */
public class DataTableInfo {


    /**
     * 数据
     */
    private HashMap<String, Object> data;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;

    public DataTableInfo() {
        this.data =new HashMap<>();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setTotal(long total) {
        data.put("total", total);
    }

    public void setData(List<?> list) {
        data.put("row", list);
    }

    public HashMap<String, Object> getData() {
        return this.data;
    }
}
