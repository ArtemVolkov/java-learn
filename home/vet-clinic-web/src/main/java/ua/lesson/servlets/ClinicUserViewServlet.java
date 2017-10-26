package ua.lesson.servlets;


import ua.lesson.lessons.*;
import ua.lesson.models.VetClinicHolder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClinicUserViewServlet extends HttpServlet{
    private VetClinic vetClinic= VetClinicHolder.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index=Integer.valueOf(req.getParameter("id"));
        try{
            Client client=vetClinic.getClients().get(index);
            req.setAttribute("user", client);
            //give pets list to view
            req.setAttribute("pets", client.getPetsList());
        }catch (IndexOutOfBoundsException e){ //go to error page
            req.setAttribute("error", "User with this id does not exist!");
            RequestDispatcher errorDisp=req.getRequestDispatcher("/views/clinic/ErrorPage.jsp");
            errorDisp.forward(req, resp);
        }
        RequestDispatcher disp=req.getRequestDispatcher("/views/clinic/ClinicUserView.jsp");
        disp.forward(req, resp);
    }
}
