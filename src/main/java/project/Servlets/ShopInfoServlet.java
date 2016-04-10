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
import project.Serialization.Serializer;
import project.Objects.Shop;

public class ShopInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 4L;
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public ShopInfoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String shopOwner = request.getParameter("shopOwner");
        int sellerId = -1;
        if (shopOwner != null ||
                !shopOwner.isEmpty() ||
                StringUtils.isNumeric(shopOwner)) {
            sellerId = Integer.parseInt(shopOwner);
        }

        Shop shop = dbms.getShopBySellerId(sellerId);
        JSONObject obj = serializer.serializeShop(shop);

        response.setContentType("text/html");

        PrintWriter out=response.getWriter();
        out.write(obj.toJSONString());
        out.flush();
        out.close();
    }
}