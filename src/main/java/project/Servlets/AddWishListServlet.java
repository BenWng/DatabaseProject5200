package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import project.Database.DBMS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddWishListServlet extends HttpServlet {
    private static DBMS dbms = new DBMS();

    public AddWishListServlet() {
        super();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length != 4) {
            System.out.println("Error: Invalid URI in PUT wish list");
        } else if (StringUtils.isNumeric(splitURI[3]) &
                StringUtils.isNumeric(splitURI[2])){
            int userId = Integer.parseInt(splitURI[3]);
            int productId = Integer.parseInt(splitURI[2]);
            dbms.addToWishList(userId, productId);
        } else {
            System.out.println("Error: Invalid user id or product id in PUT wish list");
        }
    }
}
