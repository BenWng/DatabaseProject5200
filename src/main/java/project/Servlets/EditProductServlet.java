package project.Servlets;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.Database.DBMS;
import project.Objects.ProductSelling;
import project.Serialization.Deserializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class EditProductServlet extends HttpServlet {
    private static DBMS dbms = new DBMS();
    private static Deserializer deserializer = new Deserializer();

    public EditProductServlet() {
        super();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = request.getRequestURI();
        String[] splitURI = URI.split("/");

        if (splitURI.length != 3) {
            System.out.println("Error: Invalid URI in PUT product");
        } else if (StringUtils.isNumeric(splitURI[2])){
            StringBuffer sb = new StringBuffer();

            try {
                BufferedReader reader = request.getReader();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONParser parser = new JSONParser();
            JSONObject joProduct = null;
            try {
                joProduct = (JSONObject) parser.parse(sb.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println(joProduct.toJSONString());

            ProductSelling productSelling = deserializer.deserializeProductSelling(joProduct);
            dbms.updateProductSelling(productSelling);
        } else {
            System.out.println("Error: Invalid API call in PUT product");
        }
    }
}
