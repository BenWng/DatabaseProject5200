package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import project.Database.DBMS;
import project.Objects.ProductSold;
import project.Serialization.Serializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PurchaseServlet {
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public PurchaseServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length == 2) {
            if (StringUtils.isNumeric(splitURI[1])) {
                int userId = Integer.parseInt(splitURI[1]);
                List<ProductSold> purchasedList = dbms.getProductsSoldByPurchaserId(userId);
                JSONArray obj = serializer.serializeProductsSold(purchasedList);

                response.setContentType("text/html");

                PrintWriter out = response.getWriter();
                out.write(obj.toJSONString());
                out.flush();
                out.close();
            } else {
                System.out.println("Error: Invalid id in GET products purchased");
            }
        } else if (splitURI.length == 3 &&
                StringUtils.isNumeric(splitURI[1]) &&
                StringUtils.isNumeric(splitURI[2])) {
            int userId = Integer.parseInt(splitURI[2]);
            int productId = Integer.parseInt(splitURI[1]);
            boolean productPurchased = dbms.isProductPurchased(userId, productId);

            JSONObject obj = new JSONObject();
            obj.put("purchasedFlag", productPurchased);

            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.write(obj.toJSONString());
            out.flush();
            out.close();
        } else {
            System.out.println("Error: Invalid call to GET products purchased");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length != 3) {
            System.out.println("Error: Invalid URI in PUT product");
        } else if (StringUtils.isNumeric(splitURI[2]) &&
                StringUtils.isNumeric(splitURI[1])){
            int userId = Integer.parseInt(splitURI[2]);
            int productId = Integer.parseInt(splitURI[1]);
            dbms.purchaseProduct(productId, userId);
        } else {
            System.out.println("Error: Invalid user id or product id in DELETE wish list");
        }
    }
}
