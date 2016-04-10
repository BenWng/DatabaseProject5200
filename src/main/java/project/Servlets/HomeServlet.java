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
import project.Objects.ProductSelling;
import project.Serialization.Serializer;

public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 2L;
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();
    private static final int DEFAULT_NUM_PRODUCTS = 20;

    public HomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        List<ProductSelling> productSellingList = dbms.getRecentProducts(DEFAULT_NUM_PRODUCTS);
        JSONArray list = serializer.serializeProductsSelling(productSellingList);

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        out.write(list.toJSONString());
        out.flush();
        out.close();
    }
}