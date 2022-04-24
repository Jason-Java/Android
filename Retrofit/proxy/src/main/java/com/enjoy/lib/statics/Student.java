package com.enjoy.lib.statics;

import com.enjoy.lib.service.Lance;
import com.enjoy.lib.service.NDK;

public class Student {

    public static void main(String[] args) throws Exception {
        /**
         * 静态代理
         * 优点：对于使用者只需要知道代理-简单即可，不需要知道实现类是什么，怎么做的（解耦）
         * 缺点：需求增加会逐渐臃肿，且不符合软件工程的开闭原则（对于扩展是开放的，对于修改是封闭的）
         */
        NDK lance = new Lance();
        Simple simple = new Simple(lance);
        simple.ndk();

        /**
         * 同学们有什么问题，都可以问我们享学的老师，但是Lance老师只会NDK，有UI的问题怎么办？
         * 静态代理去扩展代理功能需要：
         * 1、创建UI的接口
         * 2、创建擅长UI的Alvin类，和代理简单都要实现UI接口
         * 3、同学们可以访问简单，让简单找Alvin老师问UI问题
         * 面对新的需求时，需要修改代理类-简单，增加实现新的接口和方法，导致代理类简单越来越庞大，变得难以维护。
         */
//        UI alvin = new Alvin();
//        simple = new Simple(alvin);
//        simple.ui();
    }


}
