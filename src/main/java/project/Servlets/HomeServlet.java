package project.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import project.Objects.ProductSelling;
import project.Serialization.Serializer;


@WebServlet("/home")
public class HomeServlet extends HttpServlet
{
    private static final long serialVersionUID = 2L;
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();
    private static final int DEFAULT_NUM_PRODUCTS = 20;

    public HomeServlet(){super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        //* Code for DB interaction
        List<ProductSelling> productSellingList = dbms.getRecentProducts(DEFAULT_NUM_PRODUCTS);
        JSONArray list = serializer.serializeProductsSelling(productSellingList);
        //*/

        /* Test code, delete when DB works
        JSONObject obj = new JSONObject() ;
        obj.put("id",589);
        obj.put("name","Goodie1");
        obj.put("description","description1");

        JSONObject obj2 = new JSONObject() ;
        obj2.put("id",376);
        obj2.put("name","Goodie2");
        obj2.put("description","description2");

        JSONArray list = new JSONArray();
        list.add(obj);
        list.add(obj2);
        //*/

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        out.write(list.toJSONString());
        out.flush();
        out.close();



    }
}