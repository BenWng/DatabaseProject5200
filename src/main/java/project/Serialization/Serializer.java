package project.Serialization;

import project.Objects.Product;
import project.Objects.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class Serializer {

    public JSONObject serializeUser(User user) {
        JSONObject obj = new JSONObject();
        obj.put("id", user.getId());
        obj.put("name", user.getName());
        obj.put("email", user.getEmail());
        obj.put("pnumber", user.getRole());

        return obj;
    }

    public JSONObject serializeProduct(Product product) {
        JSONObject obj = new JSONObject();
        obj.put("id", product.getId());
        obj.put("name", product.getName());
        obj.put("price", product.getPrice());
        obj.put("quantity", product.getQuantity());
        obj.put("description", product.getDescription());
        obj.put("pictureURL", product.getPictureURL());

        return obj;
    }

    public JSONArray serializeProducts(List<Product> productList) {
        JSONArray array = new JSONArray();
        for (Product product : productList) {
           array.add(serializeProduct(product));
        }

        return array;
    }
}