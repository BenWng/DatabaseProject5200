package project.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import project.Database.DBMS;
import project.Objects.ProductSelling;
import project.Serialization.Serializer;
//import java.util.*;

@WebServlet("/singleproduct")
public class SingleProductServlet extends HttpServlet
{
    private static final long serialVersionUID = 4L;
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public SingleProductServlet(){super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String productID = request.getParameter("productID");
        int id = -1;

        if (productID != null |
                !productID.isEmpty() |
                StringUtils.isNumeric(productID)) {
            id = Integer.parseInt(productID);
        }
        response.setContentType("text/html");

        //* Code for DB interaction
        ProductSelling productSelling = dbms.getProductSellingById(id);
        JSONObject obj = serializer.serializeProductSelling(productSelling);
        //*/

        /* Test Code, delete when DB works
        JSONObject obj = new JSONObject();

        obj.put("id", id);
        obj.put("name", "iphone");
        obj.put("description", "The next big thing is here");
        obj.put("longdescription", "Mobile Phone's revolution. 4 speakers, 5.5 inch, ID Touch");
        obj.put("price", 649);
        obj.put("owner", 345);
        obj.put("Category", "Food");
        //*/

        PrintWriter out = response.getWriter();
        out.write(obj.toJSONString());
        out.flush();
        out.close();

    }
}