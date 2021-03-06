package project.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import project.Objects.*;

public class DBMS {
    private static final String dbName = "test";
    private static final String dbConn = "jdbc:mysql://localhost/" + dbName + "?";
    private static final String dbUser = "abc";
    private static final String dbPwd = "abc";

    public List<ProductSelling> getRecentProducts(int numProducts) {
        List<ProductSelling> products = new ArrayList<>();
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * " +
                    "FROM ProductsSelling ps " +
                    "LIMIT ?");
            st.setInt(1, numProducts);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                int id =  rs.getInt(1);
                String name =  rs.getString(2);
                Double price =  rs.getDouble(3);
                String shortDescription =  rs.getString(4);
                String longDescription = rs.getString(5);
                int sellerId = rs.getInt(6);
                String pCategory = rs.getString(7);
                int quantity =  rs.getInt(8);
                String pictureURL =  rs.getString(9);
                ProductSelling p = new ProductSelling(id,
                        sellerId,
                        name,
                        price,
                        quantity,
                        shortDescription,
                        longDescription,
                        pictureURL,
                        pCategory);
                products.add(p);
            }

            conn.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    public User getUserByNameAndPassword(String username, String password) {
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * " +
                    "FROM Users u " +
                    "WHERE u.name = ? " +
                    "AND u.password= ?");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                int id =  rs.getInt(1);
                String email = rs.getString(2);
                String name = rs.getString(3);
                String shippingAddress =  rs.getString(5);
                boolean seller = rs.getBoolean(6);
                boolean admin = rs.getBoolean(8);
                return new User(id,
                        name,
                        email,
                        shippingAddress,
                        admin,
                        seller);
            }

            conn.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<ProductSelling> getProductsBySellerAndCategory(int sellerId, String category) {
        List<ProductSelling> products = new ArrayList<>();
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * " +
                    "FROM ProductsSelling ps " +
                    "WHERE ps.sellerId = ? " +
                    "AND ps.category = ?");
            st.setInt(1, sellerId);
            st.setString(2, category);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                int id =  rs.getInt(1);
                String name =  rs.getString(2);
                Double price =  rs.getDouble(3);
                String shortDescription =  rs.getString(4);
                String longDescription = rs.getString(5);
                String pCategory = rs.getString(7);
                int quantity =  rs.getInt(8);
                String pictureURL =  rs.getString(9);
                ProductSelling p = new ProductSelling(id,
                        sellerId,
                        name,
                        price,
                        quantity,
                        shortDescription,
                        longDescription,
                        pictureURL,
                        pCategory);
                products.add(p);
            }

            conn.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    public ProductSelling getProductSellingById(int productId) {
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * " +
                    "FROM ProductsSelling ps " +
                    "WHERE ps.id = ?");
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                int id =  rs.getInt(1);
                String name =  rs.getString(2);
                Double price =  rs.getDouble(3);
                String shortDescription =  rs.getString(4);
                String longDescription = rs.getString(5);
                int sellerId = rs.getInt(6);
                String pCategory = rs.getString(7);
                int quantity =  rs.getInt(8);
                String pictureURL =  rs.getString(9);
                return new ProductSelling(id,
                        sellerId,
                        name,
                        price,
                        quantity,
                        shortDescription,
                        longDescription,
                        pictureURL,
                        pCategory);
            }

            conn.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public ProductSold getProductSoldById(int productId) {
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * " +
                    "FROM ProductsSold ps " +
                    "WHERE ps.id = ?");
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                int id =  rs.getInt(1);
                String name =  rs.getString(2);
                Double price =  rs.getDouble(3);
                String shortDescription =  rs.getString(4);
                String longDescription = rs.getString(5);
                int sellerId = rs.getInt(6);
                int purchaserId = rs.getInt(7);
                String pCategory = rs.getString(8);
                int quantity =  rs.getInt(9);
                String pictureURL =  rs.getString(10);
                return new ProductSold(id,
                        sellerId,
                        purchaserId,
                        name,
                        price,
                        shortDescription,
                        longDescription,
                        pictureURL,
                        pCategory,
                        false,
                        false);
            }

            conn.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Shop getShopBySellerId(int sellerId) {
        try {
            Connection conn = establishConnection();
            List<String> shopCategories = new ArrayList<>();
            String shopName = "";
            PreparedStatement st = conn.prepareStatement("SELECT * " +
                    "FROM Users " +
                    "WHERE id = ?");
            st.setInt(1, sellerId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                shopName = rs.getString("shopName");
                PreparedStatement shopSt = conn.prepareStatement("SELECT category " +
                        "FROM ShopCategories " +
                        "WHERE sellerId = ?");
                shopSt.setInt(1, sellerId);
                ResultSet shopRS = shopSt.executeQuery();
                while (shopRS.next()) {
                    shopCategories.add(shopRS.getString(1));
                }
            }

            conn.close();
            return new Shop(sellerId, shopName, shopCategories);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private Connection establishConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(dbConn, dbUser, dbPwd);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = establishConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Users");
            while (rs.next()) {
                int id = rs.getInt(1);
                String email = rs.getString(2);
                String name = rs.getString(3);
                boolean seller = rs.getBoolean(6);
                boolean admin = rs.getBoolean(8);
                User u1 = new User(id,
                        name,
                        email,
                        null,
                        admin,
                        seller);
                users.add(u1);
            }
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void deleteUser(int userId) {
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM Users WHERE id =  ?");
            st.setInt(1, userId);
            st.executeUpdate();
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(User u1) {
        int id = u1.getId();
        String name = u1.getName();
        String email = u1.getEmail();
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("UPDATE Users SET name = ?, email = ? WHERE id = ?");
            st.setString(1, name);
            st.setString(2, email);
            st.setInt(3, id);
            st.executeUpdate();
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int addUser(User u1, String password) {
        int to_return = -1;
        String name = u1.getName();
        String email = u1.getEmail();
        boolean admin = u1.isAdmin();
        boolean seller = u1.isSeller();

        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Users WHERE name = ? AND password = ?");
            st.setString(1, name);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                PreparedStatement st1 = conn.prepareStatement("INSERT INTO Users (email, name, password, " +
                        "shippingAddress, seller, shopName, admin) VALUES(?, ?, ?, ?, ?, ?, ?)");
                st1.setString(1, email);
                st1.setString(2, name);
                st1.setString(3, password);
                st1.setString(4, null);
                st1.setBoolean(5, seller);
                st1.setString(6, "Default name");
                st1.setBoolean(7, admin);
                st1.executeUpdate();
                st1.close();

                PreparedStatement st2 = conn.prepareStatement("SELECT * FROM Users WHERE name = ? AND password = ?");
                st2.setString(1, name);
                st2.setString(2, password);
                ResultSet rs2 = st2.executeQuery();
                if (rs2.next()) to_return = rs2.getInt("id");
            } else {
                to_return = rs.getInt("id");
            }
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return to_return;
    }

    public List<ProductSold> getProductsSoldByPurchaserId(int purchaserId) {
        List<ProductSold> products_sold = new ArrayList<>();
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM ProductsSold WHERE purchaserId = ?");
            st.setInt(1, purchaserId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Double price = rs.getDouble(3);
                String shortDescription = rs.getString(4);
                String longDescription = rs.getString(5);
                int sellerId = rs.getInt(6);
                String category = rs.getString(8);
                boolean shipped = rs.getBoolean(9);
                boolean received = rs.getBoolean(10);
                String picture_url = rs.getString(11);
                ProductSold p = new ProductSold(id,
                        sellerId,
                        purchaserId,
                        name,
                        price,
                        shortDescription,
                        longDescription,
                        picture_url,
                        category,
                        shipped,
                        received);
                products_sold.add(p);
            }
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return products_sold;
    }

    public int updateShippedOnProductsSoldById(int productId) {
        int ret = -1;
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT shipped, sellerId " +
                    "FROM ProductsSold WHERE id = ?");
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ret = rs.getInt(2);
                if (rs.getBoolean(1)) return ret;
                PreparedStatement st1 = conn.prepareStatement("UPDATE ProductsSold SET shipped = ? WHERE id = ?");
                st1.setBoolean(1, true);
                st1.setInt(2, productId);
                st1.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    public List<ProductSold> getProductsSoldBySellerId(int sellerId) {
        List<ProductSold> products_sold = new ArrayList<>();
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM ProductsSold WHERE sellerId = ?");
            st.setInt(1, sellerId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Double price = rs.getDouble(3);
                String shortDescription = rs.getString(4);
                String longDescription = rs.getString(5);
                int purchaserId = rs.getInt(7);
                String category = rs.getString(8);
                boolean shipped = rs.getBoolean(9);
                boolean received = rs.getBoolean(10);
                String picture_url = rs.getString(11);
                ProductSold p = new ProductSold(id,
                        sellerId,
                        purchaserId,
                        name,
                        price,
                        shortDescription,
                        longDescription,
                        picture_url,
                        category,
                        shipped,
                        received);
                products_sold.add(p);
            }
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return products_sold;
    }

    public List<ProductSelling> getWishListByUserId(int userId) {
        List<ProductSelling> wishlist = new ArrayList<>();
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Wishlist WHERE userId = ?");
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("productId");
                PreparedStatement st1 = conn.prepareStatement("SELECT * FROM ProductsSelling WHERE id = ?");
                st1.setInt(1, id);
                ResultSet rs1 = st1.executeQuery();
                while (rs1.next()) {
                    String name = rs1.getString(2);
                    Double price = rs1.getDouble(3);
                    String shortDescription = rs1.getString(4);
                    String longDescription = rs1.getString(5);
                    int sellerId = rs1.getInt(6);
                    String category = rs1.getString(7);
                    String picture_url = rs1.getString(8);
                    ProductSelling p = new ProductSelling(id,
                            sellerId,
                            name,
                            price,
                            1,
                            shortDescription,
                            longDescription,
                            picture_url,
                            category);
                    wishlist.add(p);
                }
                st1.close();
            }
            conn.close();
            st.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return wishlist;
    }

    public void removeFromWishList(int userId, int productSellingId) {
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM Wishlist WHERE userId = ? AND productID = ?");
            st.setInt(1, userId);
            st.setInt(2, productSellingId);
            st.executeUpdate();
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToWishList(int userId, int productSellingId) {
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("INSERT INTO Wishlist (userId, productId) VALUES (?, ?)");
            st.setInt(1, userId);
            st.setInt(2, productSellingId);
            st.executeUpdate();
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductSelling(ProductSelling product) {
        int id = product.getId();
        String name = product.getName();
        Double price = product.getPrice();
        String shortDescription = product.getShortDescription();
        String longDescription = product.getFullDescription();
        String pictureURL = product.getPictureURL();
        String category = product.getCategory();

        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("UPDATE ProductsSelling " +
                    "SET name = ?, price = ?, shortDescription = ?, longDescription = ?, pictureURL = ?, " +
                    "category = ? WHERE id = ?");
            st.setString(1, name);
            st.setDouble(2, price);
            st.setString(3, shortDescription);
            st.setString(4, longDescription);
            st.setString(5, pictureURL);
            st.setString(6, category);
            st.setInt(7, id);
            st.executeUpdate();
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProductSelling(int productId) {
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM ProductsSelling WHERE id =  ?");
            st.setInt(1, productId);
            st.executeUpdate();
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void purchaseProduct(int productSellingId, int purchaserId) {
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM ProductsSelling WHERE id =  ?");
            st.setInt(1, productSellingId);
            ResultSet rs1 = st.executeQuery();
            if (rs1.next()) {
                String name = rs1.getString("name");
                Double price = rs1.getDouble("price");
                String shortDescription = rs1.getString("shortDescription");
                String fullDescription = rs1.getString("longDescription");
                String picture_url = rs1.getString("pictureURL");
                int sellerId = rs1.getInt("sellerId");
                String category = rs1.getString("category");
                PreparedStatement st1 = conn.prepareStatement("DELETE FROM ProductsSelling WHERE id = ?");
                st1.setInt(1, productSellingId);
                int rs = st1.executeUpdate();
                if (rs > 0) {
                    PreparedStatement st2 = conn.prepareStatement("INSERT INTO ProductsSold " +
                            "(name, price, shortDescription, longDescription, sellerId, purchaserId, category, " +
                            "shipped, received, pictureURL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    st2.setString(1, name);
                    st2.setDouble(2, price);
                    st2.setString(3, shortDescription);
                    st2.setString(4, fullDescription);
                    st2.setInt(5, sellerId);
                    st2.setInt(6, purchaserId);
                    st2.setString(7, category);
                    st2.setBoolean(8, false);
                    st2.setBoolean(9, false);
                    st2.setString(10, picture_url);
                    st2.executeUpdate();
                }
            }
            conn.close();
            st.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isProductInWishList(int userId, int productId) {
        boolean ret = false;
        try {
            Connection conn = establishConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Wishlist " +
                    "WHERE userId = ? AND productId = ?");
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                ret = true;
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    public boolean isProductPurchased(int userId, int productId) {
        boolean ret = false;
        try {
            Connection conn = establishConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM ProductsSold " +
                    "WHERE purchaserId = ? AND id = ?");
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                ret = true;
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

}