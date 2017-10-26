package ua.lesson.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import ua.lesson.lessons.UserException;
import ua.lesson.lessons.VetClinic;
import ua.lesson.models.VetClinicHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;


public class ClinicUserViewServletTest extends Mockito {
    VetClinic vet= VetClinicHolder.getInstance();

    @Test(expected = NullPointerException.class)
    public void userViewTest() throws IOException, ServletException  {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        assertTrue(vet.getClients().isEmpty());

        try{
            vet.addNewClient("John");
        }catch (UserException e){
            ///
        }
       assertFalse(vet.getClients().isEmpty());

        when(req.getParameter("id")).thenReturn("0");

        new ClinicUserViewServlet().doGet(req,resp);

    }

}