package com.dbexercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.add();
    }
}
