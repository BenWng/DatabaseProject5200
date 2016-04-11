package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import project.Database.DBMS;
import project.Objects.User;
import project.Serialization.Serializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DeleteUserServlet extends HttpServlet {
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public DeleteUserServlet() {
        super();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length != 3) {
            System.out.println("Error: Invalid URI for DELETE user");
        } else {
            if (StringUtils.isNumeric(splitURI[2])) dbms.deleteUser(Integer.parseInt(splitURI[2]));
            else System.out.println("Error: Invalid user id for DELETE user");
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
