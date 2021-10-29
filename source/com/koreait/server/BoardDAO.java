package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoard(BoardVO param){
        int result = 0;
        Connection con  = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_board" +
                    "(title,ctnt,writer)" +
                    "Values"+
                    "(?,?,?)";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,param.getTitle());
            ps.setString(2,param.getCtnt());
            ps.setString(3,param.getWriter());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return result;
    }
    public static int upBoard(BoardVO param){
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " UPDATE t_board "+
                    " SET title=?, writer =? , ctnt = ? "+
                    " where iboard = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,param.getTitle());
            ps.setString(2,param.getWriter());
            ps.setString(3,param.getCtnt());
            ps.setInt(4,param.getIboard());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  finally {
            DbUtils.close(con,ps);
        }
        return result;
    }
    public static int delBoard(BoardVO param){
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " Delete from t_board where iboard = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIboard());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  finally {
            DbUtils.close(con,ps);
        }
        return result;
    }
    public static List<BoardVO> selBoardlist(){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM t_board ";
        try {
            con=DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps,rs);
        }
        return list;
    }
    public static BoardVO selBoard(BoardVO param){
        BoardVO result =null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM t_board where iboard = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIboard());
            rs=ps.executeQuery();
            if(rs.next()){
                result = new BoardVO();
                result.setIboard(rs.getInt("iboard"));
                result.setTitle(rs.getString("title"));
                result.setCtnt(rs.getString("ctnt"));
                result.setWriter(rs.getString("writer"));
                result.setRdt(rs.getString("rdt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return result;

    }
}
