package com.jason.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {


    @Pointcut("execution(* com.jason.dao.BookDao.*(..))")
    private void pointCut() {

    }


    @Around("pointCut()")
    public String method(ProceedingJoinPoint pjp) throws Throwable {

        Object object = null;
        long starTime = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
            object = pjp.proceed();
//        }
        long endTime=System.currentTimeMillis();

        Object[] args = pjp.getArgs(); //获取方法里面的形参
        for (int i = 0; i < args.length; i++) {
            System.out.println("args  "+args[i]);
        }

        System.out.println("execution time " + (endTime - starTime));
        return (String) object;
    }

}
