package ua.lesson.servlets;

import ua.lesson.models.User;
import ua.lesson.models.UserHolder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserViewServlet extends HttpServlet {

    private final ArrayList<User> users= UserHolder.users;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",this.users);
        RequestDispatcher dispatcher=req.getRequestDispatcher("views/user/UserView.jsp");
        dispatcher.forward(req,resp);
    }
}
