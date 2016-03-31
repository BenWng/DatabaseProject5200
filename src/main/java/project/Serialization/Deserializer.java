package project.Serialization;

import project.Objects.ProductSelling;
import project.Objects.ProductSold;
import project.Objects.User;
import org.json.simple.JSONObject;

public class Deserializer {

    public User deserializeUser(JSONObject jsonObject) {
        return new User((Integer) jsonObject.get("id"),
                (String) jsonObject.get("name"),
                (String) jsonObject.get("email"),
                (String) jsonObject.get("role"));
    }

    public ProductSelling deserializeProductSelling(JSONObject jsonObject) {
        return new ProductSelling((Integer) jsonObject.get("id"),
                (Integer) jsonObject.get("sellerId"),
                (String) jsonObject.get("name"),
                (Double) jsonObject.get("price"),
                (Integer) jsonObject.get("quantity"),
                (String) jsonObject.get("description"),
                (String) jsonObject.get("longdescription"),
                (String) jsonObject.get("pictureURL"),
                (String) jsonObject.get("Category"));
    }

    public ProductSold deserializeProductSold(JSONObject jsonObject) {
        return new ProductSold((Integer) jsonObject.get("id"),
                (Integer) jsonObject.get("sellerId"),
                (Integer) jsonObject.get("purchaserId"),
                (String) jsonObject.get("name"),
                (Double) jsonObject.get("price"),
                (String) jsonObject.get("description"),
                (String) jsonObject.get("longdescription"),
                (String) jsonObject.get("pictureURL"),
                (String) jsonObject.get("Category"),
                (Boolean) jsonObject.get("shippedFlag"),
                (Boolean) jsonObject.get("receivedFlag"));
    }
}
