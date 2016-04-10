package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
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
import java.util.List;

public class UpdateUserServlet {
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();
    private static Deserializer deserializer = new Deserializer();

    public UpdateUserServlet() {
        super();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length != 2) {
            System.out.println("Error: Invalid URI for PUT user");
        } else {
            if (StringUtils.isNumeric(splitURI[1])) {
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
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                User user = deserializer.deserializeUser(joUser);
                dbms.updateUser(user);

            } else {
                System.out.println("Error: Invalid user id for PUT user");
            }
        }

        response.setContentType("text/html");

        JSONArray obj = getAndSerializeAllUsers();

        PrintWriter out = response.getWriter();
        out.write(obj.toJSONString());
        out.flush();
        out.close();
    }

    private JSONArray getAndSerializeAllUsers() {
        List<User> userList = dbms.getUsers();
        return serializer.serializeUserList(userList);
    }
}
