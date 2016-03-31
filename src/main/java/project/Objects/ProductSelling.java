package project.Objects;

public class ProductSelling {
    private int id;
    private int sellerId;
    private String name;
    private Double price;
    private int quantity;
    private String shortDescription;
    private String fullDescription;
    private String pictureURL;
    private String category;

    public ProductSelling(int id,
                          int sellerId,
                          String name,
                          Double price,
                          int quantity,
                          String shortDescription,
                          String fullDescription,
                          String pictureURL,
                          String category) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.pictureURL = pictureURL;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId(){
        return sellerId;
    }

    public void setSellerId(int sellerId){
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
