package com.jason;

import com.jason.config.SpringConfig;
import com.jason.dao.BookDao;
import com.jason.dao.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = context.getBean(BookDao.class);

        User user = new User("冯家振", "12");
        System.out.println(bookDao.save(100, "123456", user));

    }
}
