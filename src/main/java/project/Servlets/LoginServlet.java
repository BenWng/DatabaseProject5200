package project.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import project.Database.DBMS;
import project.Objects.User;
import project.Serialization.Serializer;

public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 5L;
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public LoginServlet(){super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        StringBuffer sb = new StringBuffer();

        try{
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (Exception e) { e.printStackTrace(); }

        JSONParser parser = new JSONParser();
        JSONObject joUser = null;
        try
        {
            joUser = (JSONObject) parser.parse(sb.toString());
        } catch (ParseException e) { e.printStackTrace(); }

        String username = (String) joUser.get("name");
        String password = (String) joUser.get("password");

        User user = dbms.getUserByNameAndPassword(username, password);
        JSONObject obj = serializer.serializeUser(user);

        response.setContentType("text/html");

        PrintWriter out=response.getWriter();
        out.write(obj.toJSONString());
        out.flush();
        out.close();


    }
}