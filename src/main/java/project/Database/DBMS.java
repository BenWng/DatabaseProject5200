package project.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import project.Objects.*;

public class DBMS {

    public List<ProductSelling> getRecentProducts(int numProducts) {
        List<ProductSelling> products = new ArrayList<>();
        try {
            Connection conn = establishConnection();
            PreparedStatement st = conn.prepareStatement("Select * from ProductsSelling ps"
                    + "limit ?");
            st.setInt(1, numProducts);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
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
            PreparedStatement st = conn.prepareStatement("Select * from User u"
                    + "where u.name = ?"
                    + "AND u.password= ?");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
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
            PreparedStatement st = conn.prepareStatement("Select * from ProductsSelling ps"
                    + "where ps.sellerId = ?"
                    + "and ps.category = ?");
            st.setInt(1, sellerId);
            st.setString(2, category);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
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
            PreparedStatement st = conn.prepareStatement("Select * from ProductsSelling ps"
                    + "where ps.id = ?");
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
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
            PreparedStatement st = conn.prepareStatement("select * from Users where id = ?");
            st.setInt(1, sellerId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                shopName = rs.getString("shopName");
                PreparedStatement shopSt = conn.prepareStatement("select category from ShopCategories where " +
                        "sellerId = ?");
                shopSt.setInt(1, sellerId);
                ResultSet shopRS = shopSt.executeQuery();
                while (shopRS.next()) {
                    shopCategories.add(rs.getString(1));
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
        return DriverManager.getConnection("jdbc:mysql://localhost/test?", "abc", "abc");
    }
    /*public user get_all_users(int uid)
    {
        int user_id = uid;
        Connection conn = null;
        int id=0;
        String name="";
        String address="";
        String number="";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                                   "user=abc&password=abc");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Users WHERE uid= user_id");
            if(rs.next())
            {
                id=rs.getInt(1);
                name= rs.getString(2);
                address= rs.getString(3);
                number= rs.getString(4);   
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        user u1= new user(id,name,address,number);
        return u1;
    }
    public Object[] add_user(int uid)
    {
        int user_id = uid;
        Connection conn = null;
        int id=0;
        String name="";
        String address="";
        String number="";
        int success=0;
        int i=0;
        Object[] users = new Object[10]; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                                   "user=abc&password=abc");
            Statement st = conn.createStatement();
            success = st.executeUpdate("INSERT INTO users VALUES(23, 'Dave', '321 Huntington Ave', '617654897')");
            if(success>0)
            {
                ResultSet rs = st.executeQuery("Select * from Users");
                while(rs.next())
                {
                    id=rs.getInt(1);
                    name= rs.getString(2);
                    address= rs.getString(3);
                    number= rs.getString(4);  
                    user u1= new user(id,name,address,number);
                    users[i] = u1;
                    i++;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return users;
    }
    public Object[] delete_user(int uid)
    {
        int user_id = uid;
        Connection conn = null;
        int success=0;
        int id=0;
        String name="";
        String address="";
        String number="";
        int i=0;
        Object[] users = new Object[10]; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                                   "user=abc&password=abc");
            Statement st = conn.createStatement();
            success = st.executeUpdate("DELETE FROM Users WHERE uid= user_id");
            if(success>0)
            {
                ResultSet rs = st.executeQuery("Select * from Users");
                while(rs.next())
                {
                    id=rs.getInt(1);
                    name= rs.getString(2);
                    address= rs.getString(3);
                    number= rs.getString(4);  
                    user u1= new user(id,name,address,number);
                    users[i] = u1;
                    i++;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return users;
    }*/
}
