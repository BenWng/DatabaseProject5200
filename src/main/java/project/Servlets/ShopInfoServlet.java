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
import project.Serialization.Serializer;
import project.Objects.Shop;


//The servlet returns the info (name and categories) of a shop,
// the request contains the name of the shop
@WebServlet("/shop/info")
public class ShopInfoServlet extends HttpServlet
{
    private static final long serialVersionUID = 4L;
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public ShopInfoServlet(){super();}

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

        /* Code for DB interaction
        Shop shop = dbms.getShopBySellerId(sellerId);
        JSONObject obj = serializer.serializeShop(shop);
        //*/

        response.setContentType("text/html");


        //* Test code, delete when DB works
        JSONObject obj = new JSONObject() ;
        obj.put("ShopName", "Macy's");


        JSONObject category1 = new JSONObject();
        category1.put("CategoryName", "Food");

        JSONObject category2 = new JSONObject();
        category2.put("CategoryName", "Clothes");


        JSONArray list = new JSONArray();
        list.add(category1);
        list.add(category2);

        obj.put("Categories", list);
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
        out.write(obj.toJSONString());
        out.flush();
        out.close();



    }
}