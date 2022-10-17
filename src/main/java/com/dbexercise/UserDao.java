package com.dbexercise;

import com.dbexercise.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {
    public void add() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);  //db연결
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES (?, ?, ?)");
        ps.setString(1, "1");
        ps.setString(2, "SuIn");
        ps.setString(3, "1026");

        ps.executeUpdate(); //ctrl + enter
        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);  //db연결
        PreparedStatement ps = conn.prepareStatement("SELECT id, name, password FROM `users` WHERE id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"),
                rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
//            userDao.add();
        User user = userDao.get("1");
        System.out.printf("#id: %s\n#name: %s\n#password: %s", user.getId(), user.getName(), user.getPassword());
    }
}
