package com.enjoy.lib.statics;

import com.enjoy.lib.service.NDK;
import com.enjoy.lib.service.UI;

/**
 * 代理对象：马杀鸡经纪人
 */
public class Simple implements NDK {

    private final NDK ndk;

    public Simple(NDK ndk) {
        this.ndk = ndk;
    }

    //....前置处理
    public void before() {
        System.out.println("入学辅导");
    }

    //....后置处理
    public void after() {
        System.out.println("答疑，面试辅导，简历辅导");
    }

    @Override
    public void ndk() {
        before();
        ndk.ndk();
        after();
    }


}
