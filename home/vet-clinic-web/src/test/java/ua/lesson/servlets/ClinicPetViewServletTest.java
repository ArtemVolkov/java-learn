package ua.lesson.servlets;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

public class ClinicPetViewServletTest extends Mockito {

    @Test (expected = NullPointerException.class)
    public void viewPetTest() throws IOException,ServletException{

        HttpServletRequest req=mock(HttpServletRequest.class);
        HttpServletResponse resp=mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("clientid")).thenReturn("0");

        //cant redirrectng
        new ClinicPetViewServlet().doGet(req,resp);
    }


}