package ua.lesson.servlets;

import ua.lesson.models.UserHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import ua.lesson.models.*;

public class UserDeleteServlet extends HttpServlet {

    private ArrayList<User> users=UserHolder.users;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index=Integer.valueOf(req.getParameter("id"));
        UserHolder.delete(index);
        //redirecting to view page
        resp.sendRedirect(String.format("%s%s",req.getContextPath(),"/view"));
    }
}
