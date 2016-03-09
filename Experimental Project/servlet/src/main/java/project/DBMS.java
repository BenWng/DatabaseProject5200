package project;

import java.sql.*;

/**
 *
 * @author Kartik
 */

public class DBMS {

    /**
     * @param args the command line arguments
     */

    public User getUserById(int uid)
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
        User u1= new User(id,name,address,number);
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
                    User u1= new User(id,name,address,number);
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
                    User u1= new User(id,name,address,number);
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
}
