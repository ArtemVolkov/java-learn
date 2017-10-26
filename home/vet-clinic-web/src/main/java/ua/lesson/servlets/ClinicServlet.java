package ua.lesson.servlets;


import ua.lesson.lessons.Pet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class ClinicServlet extends HttpServlet {

    private final List<Pet> pets=new CopyOnWriteArrayList<Pet>();
    private boolean isSearch=false;
    private String searchQuery="";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer=resp.getWriter();
        writer.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Clinic Pets</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action='"+req.getContextPath()+ "/' method='post'>\n" +
                "Name: <input type='text' name='name'>\n" +
                "Type: <input type='text' name='type'>\n" +
                "<input type='submit' value='Submit'>\n" +
                "<form>\n" +
                "\n<form action='"+req.getContextPath()+ "/' method='post'>\n" +
                "Search: <input type='text' name='search'>\n" +
                "<input type='submit' value='Submit'>\n" +
                "<form>\n" +
                this.viewPets()+
                this.searchQuery()+
                "</body>\n" +
                "</html>");
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("search").equals("")){
            if(!req.getParameter("name").equals("")
               && !req.getParameter("type").equals("")){
                Pet p=new Pet(Pet.generateId(),req.getParameter("name"), req.getParameter("type"));
                this.pets.add(p);
            }
            doGet(req,resp);
            isSearch=false;
            return;
        }
        else {
            searchQuery = req.getParameter("search");
            isSearch = true;
            doGet(req, resp);
            return;
        }
    }

    private String viewPets(){
        StringBuilder sb=new StringBuilder("");
        sb.append("<p>Pets " +this.pets.size()+"</p>");
        sb.append("<table style='border: 1px solid black'>");
        for(Pet p:  this.pets){
            sb.append("<tr><td style='border: 1px solid black'>"+p.getName()).append("</td>");
            sb.append("<td style='border: 1px solid black'>"+p.getType()).append("</td></tr>");
        }
        sb.append("</table>");

        return sb.toString();
    }

    private String searchQuery(){
        if(!isSearch){
            return "";
        }else {
            StringBuilder sb = new StringBuilder("");
            sb.append("<p>Search</p>");
            sb.append("<table style='border: 1px solid black'>");
            for (Pet p : this.pets) {
                if (p.getName().equals(searchQuery)) {
                    sb.append("<tr><td style='border: 1px solid black'>" + p.getName()).append("</td>");
                    sb.append("<td style='border: 1px solid black'>" + p.getType()).append("</td></tr>");
                }
            }
            sb.append("</table>");
            return sb.toString();
        }
    }

}
