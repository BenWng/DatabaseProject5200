package project.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Database.DBMS;
import project.Objects.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.Serialization.Deserializer;
import project.Serialization.Serializer;


@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Serializer serializer = new Serializer();
    private static Deserializer deserializer = new Deserializer();
    private static DBMS dbms = new DBMS();

    public UserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        User user = dbms.getUserById(id);
        JSONObject joUser = serializer.serializeUser(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(joUser);
        out.flush();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer sb = new StringBuffer();

        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) { e.printStackTrace(); }

        JSONParser parser = new JSONParser();
        JSONObject joUser = null;
        try {
            joUser = (JSONObject) parser.parse(sb.toString());
        } catch (ParseException e) { e.printStackTrace(); }

        User user = deserializer.deserializeUser(joUser);
        //Update project.project.Objects.User in database

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(joUser);
        out.flush();
    }
}