package com.jason.exampelrxjava;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "晏传利";
    }
}
