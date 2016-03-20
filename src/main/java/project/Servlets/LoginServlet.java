package project.Servlets;

import org.json.simple.JSONObject;
import project.Database.DBMS;
import project.Serialization.Serializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet {
    private static final long serialVersionUID = 1L;
    private static Serializer serializer = new Serializer();
    private static DBMS dbms = new DBMS();

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user_name");
        String pwd = request.getParameter("pwd");
        //project.Objects.User user = dbms.getUserByNameAndPwd(userName, pwd);
        JSONObject joUser = new JSONObject();
        //joUser.put("id", user.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(joUser);
        out.flush();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Registration not yet implemented");
        /*StringBuffer sb = new StringBuffer();

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

        project.Objects.User user = serializer.jsonToUser(joUser);
        //Update project.project.Objects.User in database

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(joUser);
        out.flush();*/
    }
}
