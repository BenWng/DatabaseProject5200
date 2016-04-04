package project.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import project.Database.DBMS;
import project.Objects.ProductSelling;
import project.Serialization.Serializer;

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
        if (category == null) category = "";

        List<ProductSelling> productSellingList = dbms.getProductsBySellerAndCategory(sellerId, category);
        JSONArray list = serializer.serializeProductsSelling(productSellingList);

        response.setContentType("text/html");

        PrintWriter out=response.getWriter();
        out.write(list.toJSONString());
        out.flush();
        out.close();



    }
}