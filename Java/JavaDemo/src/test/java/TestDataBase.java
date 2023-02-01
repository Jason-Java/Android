import com.alibaba.fastjson.JSONObject;
import com.example.javademo.domain.SqlHelp;
import com.example.javademo.util.StringUtil;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDataBase {

     public static void main(String[] args) throws SQLException {

        /*String  sql="select id,name,age,gmt_create,gmt_modified,leader_id from user where is_delete=0 and ( name= ? or id = ? )";
         SqlHelp.parameterSubstitution(sql.toString(),123,"123","123");


         sql="select id,name,age,gmt_create,gmt_modified,leader_id from user where is_delete=? and age=?";*/

        /* String mysqlUrl = "jdbc:mysql://localhost:3306/unite?characterEncoding=utf8&useSSL=false";
         MysqlDataSource dataSource = new MysqlDataSource();
         dataSource.setURL(mysqlUrl);
         dataSource.setUser("root");
         dataSource.setPassword("123456");
         Connection connection = dataSource.getConnection();
         String sql = "select * from user";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()) {
             int id = resultSet.getInt("id");
             String name = resultSet.getString("name");
             System.out.println("id : " + id + "    name  : " + name);
         }*/


         int[] arr=new int[]{43,32,76,-98,0,64,33,-21,32,99};

         for(int i=0;i<arr.length-1;i++){
             for(int j=0;j<arr.length-1-i;j++){
                 if(arr[j]>arr[j+1]){
                     int temp=arr[j];
                     arr[j]=arr[j+1];
                     arr[j+1]=temp;

                 }
             }
         }

         for(int i=0;i<arr.length;i++){
             System.out.println(arr[i]);
         }



     }



    private static void testLinkDataBase() throws SQLException {
         String mysqlUrl = "jdbc:mysql://localhost:3306/unite?characterEncoding=utf8&useSSL=false";
         MysqlDataSource dataSource = new MysqlDataSource();
         dataSource.setURL(mysqlUrl);
         dataSource.setUser("root");
         dataSource.setPassword("123456");
         Connection connection = dataSource.getConnection();
         String sql = "select * from user";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()) {
             int id = resultSet.getInt("id");
             String name = resultSet.getString("name");
             System.out.println("id : " + id + "    name  : " + name);
         }
     }
}
