package ua.lesson.servlets;


import org.junit.Test;
import org.mockito.Mockito;
import ua.lesson.models.VetClinicHolder;
import ua.lesson.lessons.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

public class ClinicCreateUserServletTest extends Mockito {

    final VetClinic vet = VetClinicHolder.getInstance();

    @Test
    public void createUserTest() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        //Симулируем возврат значений для теста
        when(req.getParameter("name")).thenReturn("Test");

        assertTrue( vet.getClients().isEmpty());

        new ClinicCreateUserServlet().doPost(req,resp);

        //тест-проверка на  какминимум 1 вызов
        verify(req , atLeast(1)).getParameter("name");

        assertFalse(vet.getClients().isEmpty());


    }
}