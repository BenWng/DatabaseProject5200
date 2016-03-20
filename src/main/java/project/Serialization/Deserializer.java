package project.Serialization;

import project.Objects.Product;
import project.Objects.User;
import org.json.simple.JSONObject;

public class Deserializer {

    public User deserializeUser(JSONObject jsonObject) {
        return new User((Integer) jsonObject.get("id"),
                (String) jsonObject.get("name"),
                (String) jsonObject.get("email"),
                (String) jsonObject.get("role"));
    }

    public Product deserializeProduct(JSONObject jsonObject) {
        return new Product((Integer) jsonObject.get("id"),
                (String) jsonObject.get("name"),
                (Double) jsonObject.get("price"),
                (Integer) jsonObject.get("quantity"),
                (String) jsonObject.get("description"),
                (String) jsonObject.get("pictureURL"));
    }
}
