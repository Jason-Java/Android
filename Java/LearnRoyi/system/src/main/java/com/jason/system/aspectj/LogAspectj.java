package com.jason.system.aspectj;

import com.jason.system.model.domain.SysOperLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class LogAspectj {

    @Pointcut("@annotation(com.jason.system.aspectj.Log)")
    private void pointCut() {
    }

    @Around("pointCut()")
    private Object method(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("我是切面  已经执行");
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Log log = methodSignature.getMethod().getAnnotation(Log.class);
        SysOperLog operLog = new SysOperLog();


       // operLog.setMethod();
        operLog.setStatus(0);

        return pjp.proceed();
    }
}
