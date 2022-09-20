package com.example.javademo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.javademo.domain.SqlHelp;
import com.example.javademo.domain.UserDomain;
import com.example.javademo.util.Message;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class User extends HttpServlet {

    private Connection connection;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(getServletContext().getInitParameter("mysqlUrl"));
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * name 用户姓名
     * id 用户id
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");


        StringBuilder sql = new StringBuilder();

        sql.append("select id,name,age,gmt_create,gmt_modified,leader_id from user where is_delete=0 and ( name= ? or id = ? )");
        sql.append("and ( name= ? or id = ? )");

        sql.append("select id,name,age,gmt_create,gmt_modified,leader_id from user where is_delete=?");
//        SqlHelp.parameterSubstitution(sql.toString(),123,"123","123");




        if (name != null) {
            sql.append("and ");
            sql.append("( ");
            sql.append("name = " + name);
        }
        if (name != null || id != null) {
//            sql.append(" and" (name= ");
            sql.append("\'" + name + "\'");
            sql.append(" or id=");
            sql.append(id == null ? "\'\'" : id);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
            ArrayList<UserDomain> userArrayList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            UserDomain user;
            while (resultSet.next()) {
                user = new UserDomain();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));

                user.setGmt_create(dateFormat.format(resultSet.getDate("gmt_create"))+" "+timeFormat.format(resultSet.getTime("gmt_create")));
                user.setGmt_modified(dateFormat.format(resultSet.getDate("gmt_modified"))+" "+timeFormat.format(resultSet.getTime("gmt_modified")));
                user.setLeader_id(resultSet.getInt("leader_id"));

                userArrayList.add(user);
            }
            Message<ArrayList<UserDomain>> message = new Message<>();
            if (userArrayList.size() <= 0) {
                message.setState(false);
                message.setMessage("无内容");
                message.setStateCode(204);
            } else {
                message.setState(true);
                message.setStateCode(200);
                message.setResult(userArrayList);
            }
            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONObject.toJSONString(message));
            writer.close();

        } catch (SQLException e) {
            resp.sendError(403, "找不到资源");
            e.printStackTrace();
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=  req.getParameter("id");
        Message<UserDomain> message = new Message<>();
        if (id == null) {
            message.setState(false);
            message.setMessage("未找到删除的内容");
            message.setStateCode(404);
        }else{
            String sql="detele from user where id=";
            try(PreparedStatement preparedStatement=connection.prepareStatement(sql)) {
//                preparedStatement.execute()
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        super.destroy();
    }
}
