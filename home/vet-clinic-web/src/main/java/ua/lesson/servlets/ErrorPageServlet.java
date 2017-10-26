package ua.lesson.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorReason=req.getParameter("error");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/ErrorPage.jsp");
        dispatcher.forward(req, resp);
    }
}
