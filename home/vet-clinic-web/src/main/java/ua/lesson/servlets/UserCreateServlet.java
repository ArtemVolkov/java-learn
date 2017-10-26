package ua.lesson.servlets;

import ua.lesson.models.User;
import ua.lesson.models.UserHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class UserCreateServlet extends HttpServlet {

    private final ArrayList<User> users= UserHolder.users;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.users.add(new User(UserHolder.generateId(),
                                req.getParameter("name"),
                                req.getParameter("email")));
        resp.sendRedirect(String.format("%s%s",req.getContextPath(),"/view"));
    }
}
