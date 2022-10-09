package com.jason.system.aspectj;

import com.alibaba.fastjson.JSON;
import com.jason.system.model.body.LoginUser;
import com.jason.system.model.domain.SysOperLog;
import com.jason.system.util.SecurityUtil;
import com.jason.system.util.ServletUtil;
import com.jason.system.util.ip.IpUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import java.util.Map;

@Component
@Aspect
public class LogAspectj {

    @Pointcut("@annotation(com.jason.system.aspectj.Log)")
    private void pointCut() {
    }

    @Around("pointCut()")
    private Object method(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Log log = methodSignature.getMethod().getAnnotation(Log.class);
        SysOperLog operLog = new SysOperLog();
        operLog.setStatus(0);

        Object obj = null;
        try {
            obj = pjp.proceed();
        } catch (Exception e) {
            operLog.setStatus(1);
            operLog.setErrorMsg(e.getMessage());
            throw new ServletException(e.getMessage());
        }


        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        operLog.setMethod(className + "  " + methodName + "()");
        operLog.setOperIp(IpUtils.getIpAddr(ServletUtil.getRequest()));
        operLog.setOperUrl(ServletUtil.getURI());
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            operLog.setOperName(loginUser.getUsername());
            operLog.setDeptName(loginUser.getUser().getDept().getDeptName());
        }

        operLog.setRequestMethod(ServletUtil.getMethod());
        getAnnotationDescription(log, operLog, pjp, obj);
        System.out.println(operLog.toString());

        return obj;
    }


    /**
     * 获取注解描述
     *
     * @param log     日志注解
     * @param operLog 日志类
     * @param pjp
     */
    private void getAnnotationDescription(Log log, SysOperLog operLog, ProceedingJoinPoint pjp, Object object) {
        operLog.setBusinessType(log.action());
        //设置模块
        operLog.setTitle(log.title());
        if (log.isSaveRequestData()) {
            setRequestValue(operLog, pjp);
        }
        if (log.isSaveResponseData() && object != null) {
            String jsonString = JSON.toJSONString(object);
            if (jsonString.length() > 2000) {
                jsonString = jsonString.substring(0, 2000);
            }
            operLog.setJsonResult(jsonString);
        }
    }

    /**
     * 获取URL的请求参数
     * @param operLog
     * @param pjp
     */
    private void setRequestValue(SysOperLog operLog, ProceedingJoinPoint pjp) {
        String requestMethod = operLog.getRequestMethod();

        if ("POST".equalsIgnoreCase(requestMethod) || "PUT".equalsIgnoreCase(requestMethod)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Object obj : pjp.getArgs()) {
                if (stringBuilder.length() >= 2000) {
                    break;
                }
                stringBuilder.append(JSON.toJSONString(obj));
                stringBuilder.append(",");
            }
            if (stringBuilder.length() >= 2000) {
                stringBuilder.delete(2000, stringBuilder.length());
            } else {
                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            }
            operLog.setOperParam(stringBuilder.toString());
        }
        else{
            Map<String, String[]> parameterMap = ServletUtil.getRequest().getParameterMap();
            if (parameterMap.isEmpty()) {
                return;
            }
            String value = parameterMap.toString();
            if (value.length() > 2000) {
                value = value.substring(0, 2000);
            }
            operLog.setOperParam(value);
        }
    }
}
