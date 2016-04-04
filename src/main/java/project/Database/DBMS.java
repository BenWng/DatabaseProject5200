package project.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import project.Objects.*;

public class DBMS {
    private static Connection conn;

    public DBMS(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                    "user=abc&password=abc");
        } catch (Exception e) {
            System.out.println("Cannot connect to database");
        }
    }

    public List<ProductSelling> getRecentProducts(int numProducts)
    {
        List<ProductSelling> products = new ArrayList<>();
        try
        {
            PreparedStatement st = conn.prepareStatement("Select * from ProductsSelling ps"
                    + "where ps.id = ?");
            st.setInt(1, numProducts);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                ProductSelling p = populateProductSellingFromDB(rs);
                products.add(p);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return products;
    }
    
    public User getUserByNameAndPassword(String username, String password)
    {
        try
        {
            PreparedStatement st = conn.prepareStatement("Select * from User u"
                    + "where u.username = ?"
                    + "AND u.password= ?");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                return populateUserFromDB(rs);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    public List<ProductSelling> getProductsBySellerAndCategory(int sellerId, String category)
    {
        List<ProductSelling> products = new ArrayList<>();
        try
        {
            PreparedStatement st = conn.prepareStatement("Select * from ProductsSelling ps"
                    + "where ps.id = ?"
                    + "and ps.category = ?");
            st.setInt(1, sellerId);
            st.setString(2, category);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                ProductSelling p = populateProductSellingFromDB(rs);
                products.add(p);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return products;
    }

    private ProductSelling populateProductSellingFromDB(ResultSet rs) throws SQLException {
        int id =  rs.getInt(1);
        int sellerId = rs.getInt(2);
        String name =  rs.getString(3);
        Double price =  rs.getDouble(4);
        int quantity =  rs.getInt(5);
        String shortDescription =  rs.getString(6);
        String longDescription = rs.getString(7);
        String pictureURL =  rs.getString(8);
        String pCategory = rs.getString(9);
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

    private User populateUserFromDB(ResultSet rs) throws SQLException {
        int id =  rs.getInt(1);
        String name = rs.getString(2);
        String email = rs.getString(3);
        String shippingAddress =  rs.getString(4);
        boolean admin = rs.getBoolean(5);
        boolean seller = rs.getBoolean(6);
        return new User(id,
                name,
                email,
                shippingAddress,
                admin,
                seller);
    }

    public ProductSelling getProductSellingById(int productId)
    {
        ProductSelling p;
        try
        {
            PreparedStatement st = conn.prepareStatement("Select * from ProductsSelling ps"
                    + "where ps.id = ?");
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                return populateProductSellingFromDB(rs);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public Shop getShopBySellerId(int sellerId) {
        //Stub code for testing purposes
        String shopName = "Macy's";
        List<String> shopCategories = new ArrayList<>();
        shopCategories.add("Food");
        shopCategories.add("Clothes");

        return new Shop(sellerId, shopName, shopCategories);
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
