package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/sel")
public class SelBoardDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardVO vo = new BoardVO();
//        vo.setIboard(Integer.parseInt(req.getParameter("iboard"))); 밑에세줄
        String str = req.getParameter("iboard");
        int wiborad = Integer.parseInt(str);
        vo.setIboard(wiborad); //값 웹에서 받아와서 vo객체에 넣기

        Gson gson = new Gson();
        BoardVO svo = BoardDAO.selBoard(vo); // vo객체를 selBoard를 이용해서 select정보가 담긴 BoardVO객체(svo)에 넣어주기
        String json = gson.toJson(svo); // svo를 json언어로 바꿔주기
//        String json = gson.toJson(BoardDAO.selBoard(vo));
        res.setContentType("text/plain; charset=UTF-8");
//        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.print(svo.getWriter());
        out.println(json); //웹에다 뿌리기
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
