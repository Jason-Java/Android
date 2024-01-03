package com.jason.jasonPackage;

import android.os.SystemClock;

import com.jason.jasontools.util.LogUtil;

import java.util.concurrent.Callable;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月30日
 */
public class MyTestCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        LogUtil.i("callable 进入睡眠");
        SystemClock.sleep(3000);
        LogUtil.i("callable 睡眠结束");
        return 100;
    }

}
