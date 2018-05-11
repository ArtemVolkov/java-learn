package ua.lesson.servlets;

import ua.lesson.lessons.*;
import ua.lesson.models.VetClinicHolder;
import ua.lesson.store.UserCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClinicPetViewServlet extends HttpServlet{
    private VetClinic vetClinic= VetClinicHolder.getInstance();
    private UserCache vet=UserCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int petIndex=Integer.valueOf(req.getParameter("id"));
            int clientIndex=Integer.valueOf(req.getParameter("clientid"));

            Client client=vet.getClient(clientIndex);
            Pet pet= client.getPets().get(petIndex);

            req.setAttribute("pet", pet);
            req.setAttribute("proc", pet.getProcedures());
        }catch (IndexOutOfBoundsException e){  //go to error page
            req.setAttribute("error", "Pet with this id does not exist!");
            RequestDispatcher errorDisp=req.getRequestDispatcher("/views/clinic/ErrorPage.jsp");
            errorDisp.forward(req, resp);
        }
        catch(NumberFormatException e){  //go to error page
            req.setAttribute("error", "Query has invalid parameter!");
            RequestDispatcher errorDisp=req.getRequestDispatcher("/views/clinic/ErrorPage.jsp");
            errorDisp.forward(req, resp);
        }

        RequestDispatcher disp=req.getRequestDispatcher("/views/clinic/ClinicPetView.jsp");
        disp.forward(req, resp);
    }
}
