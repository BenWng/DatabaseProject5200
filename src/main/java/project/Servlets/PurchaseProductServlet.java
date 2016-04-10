package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import project.Database.DBMS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PurchaseProductServlet {
    private static DBMS dbms = new DBMS();

    public PurchaseProductServlet() {
        super();
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
            System.out.println("Error: Invalid user id or product id in PUT product");
        }
    }
}
