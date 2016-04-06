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
}
