package com.jason.system.model.domain;

import com.jason.system.constant.HttpStatus;
import com.jason.system.util.StringUtils;

import java.util.HashMap;

public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回自定义成功消息
     *
     * @param msg 自定义成功消息
     * @return 返回成功消息
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);

    }

    /**
     * 返回成功消息
     *
     * @param data 返回的数据内容
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 自定义消息内容
     * @param data 返回的数据内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {

        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

}
