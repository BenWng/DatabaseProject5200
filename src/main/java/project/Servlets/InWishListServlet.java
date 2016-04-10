package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import project.Database.DBMS;
import project.Serialization.Serializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InWishListServlet {
    private static DBMS dbms = new DBMS();
    private static Serializer serializer = new Serializer();

    public InWishListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length == 3 &&
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
}
