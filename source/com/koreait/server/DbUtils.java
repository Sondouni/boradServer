package com.koreait.server;

import java.sql.*;

public class DbUtils {
    public static Connection getCon() throws SQLException,ClassNotFoundException{
        final String URL = "jdbc:mysql://localhost:3308/son";
        final String USERNALE = "root";
        final String PASSWORD = "koreait";
        Class.forName("com.mysql.cj.jdbc.Driver"); //suitable 이유 : 드라이버와 url이 맞지않으면 에러발생((JDK문제))
        Connection con = DriverManager.getConnection(URL,USERNALE,PASSWORD);
        System.out.println("접속 성공");
        return con;
    }
    public static void close(Connection con, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try { rs.close(); }
            catch (SQLException e) {  e.printStackTrace(); }
        }
        if(ps!=null){
            try { ps.close();  }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection con,PreparedStatement ps){
        close(con,ps,null);
    }
}
