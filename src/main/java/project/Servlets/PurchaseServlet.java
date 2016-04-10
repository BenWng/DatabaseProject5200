package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
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

        if (splitURI.length == 2 &&
            StringUtils.isNumeric(splitURI[1])) {
            int userId = Integer.parseInt(splitURI[1]);
            List<ProductSold> purchasedList = dbms.getProductsSoldByPurchaserId(userId);
            JSONArray obj = serializer.serializeProductsSold(purchasedList);

            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.write(obj.toJSONString());
            out.flush();
            out.close();
        } else {
            System.out.println("Error: Invalid call to GET products purchased");
        }
    }
}
