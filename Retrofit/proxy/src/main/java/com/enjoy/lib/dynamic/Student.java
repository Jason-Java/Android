package com.enjoy.lib.dynamic;

import com.enjoy.lib.service.Alvin;
import com.enjoy.lib.service.Lance;
import com.enjoy.lib.service.NDK;
import com.enjoy.lib.service.UI;

import java.lang.reflect.Proxy;


public class Student {

    public static void main(String[] args) {
        NDK lance = new Lance();
        // JAVA动态代理 NDK
        /**
         * 1、类加载器
         * 2、要代理的接口
         * 3、回调
         */
        NDK ndk = (NDK) Proxy.newProxyInstance(lance.getClass().getClassLoader(),
                new Class[]{NDK.class}, new ProxyInvokeHandler(lance));

        ndk.ndk();


        //现在要能否解决UI的问题
//        UI alvin = new Alvin();
//        UI ui = (UI) Proxy.newProxyInstance(alvin.getClass().getClassLoader(),
//                alvin.getClass().getInterfaces(), new ProxyInvokeHandler(alvin));
//        ui.ui();

    }


}
