package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import project.Database.DBMS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet{
    private static DBMS dbms = new DBMS();

    public DeleteProductServlet() {
        super();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length != 3) {
            System.out.println("Error: Invalid URI in DELETE product");
        } else if (StringUtils.isNumeric(splitURI[2])){
            int productId = Integer.parseInt(splitURI[2]);
            dbms.deleteProductSelling(productId);
        } else {
            System.out.println("Error: Invalid API call in DELETE product");
        }
    }
}
