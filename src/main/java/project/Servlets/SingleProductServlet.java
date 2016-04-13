package project.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import project.Database.DBMS;
import project.Objects.ProductSelling;
import project.Objects.ProductSold;
import project.Serialization.Serializer;

public class SingleProductServlet extends HttpServlet {
    private static final long serialVersionUID = 4L;
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public SingleProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String productID = request.getParameter("productID");
        int id = -1;

        if (productID != null &&
                !productID.isEmpty() &&
                StringUtils.isNumeric(productID)) {
            id = Integer.parseInt(productID);
        }
        response.setContentType("text/html");

        ProductSelling productSelling = dbms.getProductSellingById(id);
        ProductSold productSold;
        JSONObject obj;
        if (productSelling == null) {
            productSold = dbms.getProductSoldById(id);
            obj = serializer.serializeProductSold(productSold);
        } else {
            obj = serializer.serializeProductSelling(productSelling);
        }

        PrintWriter out = response.getWriter();
        out.write(obj.toJSONString());
        out.flush();
        out.close();
    }
}