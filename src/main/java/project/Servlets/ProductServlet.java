package project.Servlets;

import project.Database.DBMS;
import project.Objects.Product;

import org.json.simple.JSONArray;
import project.Serialization.Serializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductServlet {
    private static final long serialVersionUID = 1L;
    private static Serializer serializer = new Serializer();
    private static DBMS dbms = new DBMS();
    private static final int DEFAULT_NUM_HOME_PAGE_PRODUCTS = 20;

    public ProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = dbms.getRecentProducts(DEFAULT_NUM_HOME_PAGE_PRODUCTS);
        JSONArray jArray = serializer.serializeProducts(productList);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jArray);
        out.flush();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Cannot currently add products");
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
