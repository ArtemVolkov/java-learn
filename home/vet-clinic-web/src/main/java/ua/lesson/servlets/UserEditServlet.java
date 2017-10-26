package ua.lesson.servlets;

import ua.lesson.models.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class UserEditServlet extends HttpServlet {

    private ArrayList<User> users=UserHolder.users;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index=Integer.valueOf(req.getParameter("id"));
        //установка параметров для передачи запроса
        req.setAttribute("user", users.get(index));
        //перенаправл запрооса
        RequestDispatcher disp=req.getRequestDispatcher("/views/user/UserEdit.jsp");
        disp.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index=Integer.valueOf(req.getParameter("id"));
        UserHolder.edit(index,req.getParameter("name"), req.getParameter("email"));
        //перенаправл ответа на страницу просмотра польщзователей
        resp.sendRedirect(String.format("%s%s", req.getContextPath(),"/view"));
    }
}
