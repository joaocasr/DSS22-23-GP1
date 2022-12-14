package data;

import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoUsers.User;

import java.sql.*;
import java.util.*;

public class UserDAO implements Map<String, User> {
    private static UserDAO singleton = null;

    private UserDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "Username varchar(45) NOT NULL PRIMARY KEY," +
                    "Password varchar(45) NOT NULL," +
                    "Admin int(1) DEFAULT 0," +
                    "Score int(10) NOT NULL,"+
                    "Versao varchar(30) NOT NULL)";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UserDAO getInstance() {
        if (UserDAO.singleton == null) {
            UserDAO.singleton = new UserDAO();
        }
        return UserDAO.singleton;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM users")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Username FROM users WHERE Username ='"+ key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        User u = (User) value;
        User dbU = this.get(u.getUsername());
        return u.equals(dbU);
    }

    @Override
    public User get(Object key) {
        User u = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM users WHERE Username='" + key.toString() + "'")) {
            if (rs.next()) {
                boolean admin=false;
                if(rs.getInt(3)==1) admin=true ;
                u = new User(rs.getString(1), rs.getString(2),admin,rs.getInt(4),rs.getString(5));
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return u;
    }

    @Override
    public User put(String key, User value) {
        User user = null;
        String sql="";
        int isadmin=0;
        boolean admin = value.getIsAdmin();
        if(admin) isadmin=1;
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            if(this.containsKey(key)){
                sql= "UPDATE users SET Password='"+value.getPassword()+"',Admin='"+isadmin+"',Score='"+value.getScore()+"',Versao='"+value.getVersao()+"' WHERE username='"+key.toString()+"'";
            }else{
                sql= "INSERT INTO users VALUES('"+key.toString()+"', '"+value.getPassword()+"', '"+isadmin+"', '"+value.getScore()+"', '"+value.getVersao()+"')";
            }
            st.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return user;
    }

    @Override
    public User remove(Object key) {
        User u = this.get(key);
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("DELETE FROM users WHERE Username='"+u.getUsername()+"'")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void putAll(Map<? extends String, ? extends User> m) {
        for(User u : m.values()){
            this.put(u.getUsername(),u);
        }
    }

    @Override
    public void clear() {
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            st.executeUpdate("TRUNCATE users");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> usernames = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Username FROM users")) {
            while (rs.next()) {
                usernames.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return usernames;
    }

    @Override
    public Collection<User> values() {
        Collection<User> allUsers = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Username FROM users")) {
            while (rs.next()) {
                allUsers.add(this.get(rs.getString(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return allUsers;

    }

    @Override
    public Set<Entry<String, User>> entrySet() {
        Map.Entry<String, User> entry;
        HashSet<Entry<String, User>> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Username FROM users")) {
            while (rs.next()) {
                entry =  new AbstractMap.SimpleEntry<>(rs.getString(1),this.get(rs.getString(1)));
                col.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }
}