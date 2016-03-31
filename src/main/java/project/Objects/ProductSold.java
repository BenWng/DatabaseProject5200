package project.Objects;

public class ProductSold {
    private int id;
    private int sellerId;
    private int purchaserId;
    private String name;
    private Double price;
    private String shortDescription;
    private String fullDescription;
    private String pictureURL;
    private String category;
    private boolean shipped;
    private boolean received;

    public ProductSold(int id,
                       int sellerId,
                       int purchaserId,
                       String name,
                       Double price,
                       String shortDescription,
                       String fullDescription,
                       String pictureURL,
                       String category,
                       boolean shipped,
                       boolean received) {
        this.id = id;
        this.sellerId = sellerId;
        this.purchaserId = purchaserId;
        this.name = name;
        this.price = price;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.pictureURL = pictureURL;
        this.category = category;
        this.shipped = shipped;
        this.received = received;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(int purchaserId) {
        this.purchaserId = purchaserId;
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

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }
}
