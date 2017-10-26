package ua.lesson.servlets;

import ua.lesson.lessons.VetClinic;
import ua.lesson.models.VetClinicHolder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClinicViewServlet extends HttpServlet{
    private VetClinic vetClinic= VetClinicHolder.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //set attributes to view

        req.setAttribute("clinic" ,this.vetClinic.getClients());
        //dispatch
        RequestDispatcher disp=req.getRequestDispatcher("views/clinic/ClinicView.jsp");
        disp.forward(req,resp);
    }
}
