package ua.lesson.servlets;

import static org.junit.Assert.*;
import org.junit.*;
import org.mockito.Mockito;
import ua.lesson.lessons.VetClinic;
import ua.lesson.models.VetClinicHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClinicViewServletTest extends Mockito {

    VetClinic vetClinic= VetClinicHolder.getInstance();

    @Test(expected = NullPointerException.class)
    public void getTest() throws ServletException, IOException{
        HttpServletRequest req=mock(HttpServletRequest.class);
        HttpServletResponse resp=mock(HttpServletResponse.class);

        new ClinicViewServlet().doGet(req,resp);

        verify(req, atLeast(1)).setAttribute("clinic" ,this.vetClinic.getClients());

    }




}