package project.Objects;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private int sellerId;
    private String name;
    private List<String> categories = new ArrayList<String>();

    public Shop(int sellerId,
                String name,
                List<String> categories) {
        this.sellerId = sellerId;
        this.name = name;
        this.categories = categories;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category) {
        categories.add(category);
    }
}
