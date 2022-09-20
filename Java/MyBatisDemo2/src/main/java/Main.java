import com.jason.dao.IUser;
import com.jason.dao.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("myBatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUser iUser = sqlSession.getMapper(IUser.class);
       ArrayList<User> userArrayList = iUser.selectUser();
        for (User u :
                userArrayList) {
            System.out.println(u);
        }
        sqlSession.close();
    }
}
