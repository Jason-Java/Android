package com.jason;

import com.jason.config.SpringConfig;
import com.jason.dao.BookDaoImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

public class Main {
    public static void main(String[] args) {
       /* ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml.abc");
        BookDaoImpl bookDao = (BookDaoImpl) context.getBean("bookDaoImpl");
        bookDao.save();
        System.out.println("hello word");*/

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDaoImpl bookDao = (BookDaoImpl) context.getBean(BookDaoImpl.class);
        bookDao.save();
        System.out.println("hello word");
    }

}
