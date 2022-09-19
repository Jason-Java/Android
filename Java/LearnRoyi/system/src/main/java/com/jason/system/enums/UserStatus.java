package com.jason.system.enums;

/**
 * <p>描述: 用户状态类型
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/9/19 21:06
 * @see
 */
public enum UserStatus {

    NORMAL("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private String code;
    private String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
