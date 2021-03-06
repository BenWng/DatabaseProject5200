package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import project.Database.DBMS;
import project.Objects.ProductSold;
import project.Serialization.Serializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ConfirmShippingServlet extends HttpServlet {
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public ConfirmShippingServlet() {
        super();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length == 3 &&
                StringUtils.isNumeric(splitURI[2])) {
            int productId = Integer.parseInt(splitURI[2]);
            int sellerId = dbms.updateShippedOnProductsSoldById(productId);
            List<ProductSold> soldList = dbms.getProductsSoldBySellerId(sellerId);
            JSONArray obj = serializer.serializeProductsSold(soldList);

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
