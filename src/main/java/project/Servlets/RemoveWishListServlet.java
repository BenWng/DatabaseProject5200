package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import project.Database.DBMS;
import project.Objects.ProductSelling;
import project.Serialization.Serializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RemoveWishListServlet extends HttpServlet {
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public RemoveWishListServlet() {
        super();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");
        JSONArray obj = new JSONArray();

        if (splitURI.length != 4) {
            System.out.println("Error: Invalid URI in DELETE wish list");
        } else if (StringUtils.isNumeric(splitURI[2]) &&
                StringUtils.isNumeric(splitURI[3])){
            int userId = Integer.parseInt(splitURI[2]);
            int productId = Integer.parseInt(splitURI[3]);
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

}
