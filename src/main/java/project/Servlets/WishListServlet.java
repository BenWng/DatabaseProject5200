package project.Servlets;


import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import project.Database.DBMS;
import project.Objects.ProductSelling;
import project.Serialization.Serializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WishListServlet {
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public WishListServlet(){super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length == 2) {
            if (StringUtils.isNumeric(splitURI[1])) {
                List<ProductSelling> wishList = dbms.getWishListByUserId(Integer.parseInt(splitURI[1]));
                JSONArray obj = serializer.serializeProductsSelling(wishList);

                response.setContentType("text/html");

                PrintWriter out = response.getWriter();
                out.write(obj.toJSONString());
                out.flush();
                out.close();
            } else {
                System.out.println("Error: Invalid id in GET wish list");
            }
        } else if (splitURI.length == 3 &&
                StringUtils.isNumeric(splitURI[1]) &&
                StringUtils.isNumeric(splitURI[2])) {
            int userId = Integer.parseInt(splitURI[2]);
            int productId = Integer.parseInt(splitURI[1]);
            boolean productFromWishList = dbms.isProductInWishList(userId, productId);

            JSONObject obj = new JSONObject();
            obj.put("wishFlag", productFromWishList);

            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.write(obj.toJSONString());
            out.flush();
            out.close();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");
        JSONArray obj = new JSONArray();

        if (splitURI.length != 3) {
            System.out.println("Error: Invalid URI in DELETE wish list");
        } else if (StringUtils.isNumeric(splitURI[1]) &&
                StringUtils.isNumeric(splitURI[2])){
            int userId = Integer.parseInt(splitURI[1]);
            int productId = Integer.parseInt(splitURI[2]);
            dbms.removeFromWishList(userId, productId);
            List<ProductSelling> wishList = dbms.getWishListByUserId(userId);
            obj = serializer.serializeProductsSelling(wishList);
        } else {
            System.out.println("Error: Invalid user id or product id in DELETE wish list");
        }

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        out.write(obj.toJSONString());
        out.flush();
        out.close();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length != 3) {
            System.out.println("Error: Invalid URI in PUT wish list");
        } else if (StringUtils.isNumeric(splitURI[2]) &
                StringUtils.isNumeric(splitURI[1])){
            int userId = Integer.parseInt(splitURI[2]);
            int productId = Integer.parseInt(splitURI[1]);
            dbms.addToWishList(userId, productId);
        } else {
            System.out.println("Error: Invalid user id or product id in PUT wish list");
        }
    }
}
