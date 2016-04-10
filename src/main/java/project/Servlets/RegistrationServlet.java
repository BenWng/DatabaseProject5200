package project.Servlets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.Database.DBMS;
import project.Objects.User;
import project.Serialization.Deserializer;
import project.Serialization.Serializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrationServlet {
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();
    private static Deserializer deserializer = new Deserializer();

    public RegistrationServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer sb = new StringBuffer();

        try {
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        JSONObject joUser = null;
        try {
            joUser = (JSONObject) parser.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = deserializer.deserializeUser(joUser);

        JSONObject obj = new JSONObject();
        if (dbms.addUser(user, (String) joUser.get("password")) == -1) {
            obj.put("id", -1);
        } else {
            obj = serializer.serializeUser(user);
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.write(obj.toJSONString());
        out.flush();
        out.close();
    }
}
