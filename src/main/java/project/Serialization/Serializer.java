package project.Serialization;

import project.Objects.ProductSelling;
import project.Objects.ProductSold;
import project.Objects.Shop;
import project.Objects.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class Serializer {

    public JSONObject serializeUser(User user) {
        JSONObject obj = new JSONObject();
        if (user != null) {
            obj.put("id", user.getId());
            obj.put("name", user.getName());
            obj.put("email", user.getEmail());
            obj.put("shippingAddress", user.getShippingAddress());
            obj.put("admin", user.isAdmin());
            obj.put("seller", user.isSeller());
        } else {
            obj.put("id", -1);
        }

        return obj;
    }

    public JSONArray serializeUserList(List<User> users) {
        JSONArray array = new JSONArray();
        for (User user : users) {
            array.add(serializeUser(user));
        }

        return array;
    }

    public JSONObject serializeProductSelling(ProductSelling productSelling) {
        JSONObject obj = new JSONObject();
        if (productSelling != null) {
            obj.put("id", productSelling.getId());
            obj.put("name", productSelling.getName());
            obj.put("sellerId", productSelling.getSellerId());
            obj.put("price", productSelling.getPrice());
            obj.put("quantity", productSelling.getQuantity());
            obj.put("description", productSelling.getShortDescription());
            obj.put("longdescription", productSelling.getFullDescription());
            obj.put("pictureURL", productSelling.getPictureURL());
            obj.put("Category", productSelling.getCategory());
        } else {
            obj.put("error", "Null Product");
        }

        return obj;
    }

    public JSONArray serializeProductsSelling(List<ProductSelling> productSellingList) {
        JSONArray array = new JSONArray();
        for (ProductSelling productSelling : productSellingList) {
           array.add(serializeProductSelling(productSelling));
        }

        return array;
    }

    public JSONObject serializeProductSold(ProductSold productSold) {
        JSONObject obj = new JSONObject();
        if (productSold != null) {
            obj.put("id", productSold.getId());
            obj.put("name", productSold.getName());
            obj.put("sellerId", productSold.getSellerId());
            obj.put("price", productSold.getPrice());
            obj.put("description", productSold.getShortDescription());
            obj.put("longdescription", productSold.getFullDescription());
            obj.put("pictureURL", productSold.getPictureURL());
            obj.put("Category", productSold.getCategory());
            obj.put("shipped", productSold.isShipped());
            obj.put("received", productSold.isReceived());
        } else {
            obj.put("error", "Null Product");
        }

        return obj;
    }

    public JSONArray serializeProductsSold(List<ProductSold> productSoldList) {
        JSONArray array = new JSONArray();
        for (ProductSold productSold : productSoldList) {
            array.add(serializeProductSold(productSold));
        }

        return array;
    }

    public JSONObject serializeShop(Shop shop) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        if (shop != null) {
            obj.put("name", shop.getName());
            for (String category : shop.getCategories()) {
                JSONObject categoryObj = new JSONObject();
                categoryObj.put("CategoryName", category);
                arr.add(categoryObj);
            }
            obj.put("Categories", arr);
        } else {
            obj.put("error", "Null Shop");
        }

        return obj;
    }
}