package project.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import project.Database.DBMS;
import project.Objects.User;
import project.Serialization.Serializer;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

	public UserServlet() {
		super(); 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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