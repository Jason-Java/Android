package com.jason;

import com.jason.dao.IUser;
import com.jason.dao.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("hello world");
       /* Reader reader = Resources.getResourceAsReader("MyBatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        List<User> userList = sqlSession.selectList("selectUser");
        for (User u : userList) {
            System.out.println(u.toString());
        }

        User user = new User();
        user.setName("胡猪猪");
        user.setAge(12);
        user.setAge(1);
        int count = sqlSession.insert("insertUser", user);
        if (count != 0) {

            System.out.println("insert succeed");
        }
        userList = sqlSession.selectList("selectUser");
        for (User u : userList) {
            System.out.println(u.toString());
        }

        count = sqlSession.delete("deleteUser", 3);
        sqlSession.commit();
        if (count != 0) {
            System.out.println("delete succeed");
        }*/

        Reader reader = Resources.getResourceAsReader("MyBatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUser user=sqlSession.getMapper(IUser.class);
        for (User usera : user.selectUser()
        ) {
            System.out.println(usera);
        }
    }
}
