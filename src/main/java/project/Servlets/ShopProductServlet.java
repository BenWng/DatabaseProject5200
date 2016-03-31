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

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import project.Database.DBMS;
import project.Objects.ProductSelling;
import project.Serialization.Serializer;

//The servlet returns all the products of a shop,
// the request contains the name of the shop
@WebServlet("/shop/products")
public class ShopProductServlet extends HttpServlet
{
    private static final long serialVersionUID = 3L;
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public ShopProductServlet(){super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String shopOwner = request.getParameter("shopOwner");
        int sellerId = -1;
        if (shopOwner != null |
                !shopOwner.isEmpty() |
                StringUtils.isNumeric(shopOwner)) {
            sellerId = Integer.parseInt(shopOwner);
        }
        String category = request.getParameter("category");

        /* Code for DB interaction
        List<ProductSelling> productSellingList = dbms.getProductsBySellerAndCategory(sellerId, category);
        JSONArray list = serializer.serializeProductsSelling(productSellingList);
        //*/

        response.setContentType("text/html");


        //* Test code, delete when DB works
        JSONArray list = new JSONArray();

        if (category == null) {
            JSONObject obj = new JSONObject();
            obj.put("id", 123);
            obj.put("price", 18);
            obj.put("name", "First ProductSelling");
            obj.put("description", "description1");

            JSONObject obj2 = new JSONObject();
            obj2.put("id", 234);
            obj2.put("price", 18);
            obj2.put("name", "Second ProductSelling");
            obj2.put("description", "description2");


            list.add(obj);
            list.add(obj2);
        } else if(category.equals("Food")){
            JSONObject obj = new JSONObject();
            obj.put("id",345);
            obj.put("price",18);
            obj.put("name", "Sandwich");
            obj.put("description", "description1");

            JSONObject obj2 = new JSONObject();
            obj2.put("id",456);
            obj2.put("price",18);
            obj2.put("name", "Hamburger");
            obj2.put("description", "description2");


            list.add(obj);
            list.add(obj2);
        } else if (category.equals("Clothes")){
            JSONObject obj = new JSONObject();
            obj.put("id",567);
            obj.put("price",18);
            obj.put("name", "Suit");
            obj.put("description", "description1");

            JSONObject obj2 = new JSONObject();
            obj2.put("id",678);
            obj2.put("price",18);
            obj2.put("name", "Dress");
            obj2.put("description", "description2");


            list.add(obj);
            list.add(obj2);
        }
        //*/




        //Test: shopOwner is successfully returned or not

        /*
        JSONObject obj=new JSONObject();
        obj.put("owner",shopOwner);


        PrintWriter out=response.getWriter();
        out.write(obj.toJSONString());
        out.flush();
        out.close();
        */

        PrintWriter out=response.getWriter();
        out.write(list.toJSONString());
        out.flush();
        out.close();



    }
}