package com.jason;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>描述:程序环境变量
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/9 21:14
 * @see
 */
@Component
public final class ApplicationContext implements ApplicationContextAware {

    private static org.springframework.context.ApplicationContext context;


    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        ApplicationContext.context = applicationContext;
    }


    /**
     * 获取对象
     *
     * @param beanName
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName) {
        T bean = (T) context.getBean(beanName);
        return bean;
    }

    public static <T> T getBean(Class cla)
    {
       return (T) context.getBean(cla);
    }
}
