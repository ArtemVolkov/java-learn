package ua.lesson.servlets;

import ua.lesson.lessons.*;
import ua.lesson.models.VetClinicHolder;
import ua.lesson.store.UserCache;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ClinicCreateUserServlet extends HttpServlet {

    private UserCache vet =UserCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher disp=req.getRequestDispatcher("/views/clinic/ClinicCreateUser.jsp");
        disp.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.setCharacterEncoding("utf-8");
            Client client=new Client(Client.generateId(), req.getParameter("name"), "123");
            //vet.addNewClient(req.getParameter("name"));
            resp.sendRedirect(String.format("%s%s",req.getContextPath(),"/view"));

        }catch (Exception e){ //go to error page
            req.setAttribute("error", "Can`t create user, maybe username is invalid!");
            RequestDispatcher errorDisp=req.getRequestDispatcher("/views/clinic/ErrorPage.jsp");
            errorDisp.forward(req,resp);
        }

    }
}
