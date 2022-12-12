package data;

import RacingManagerLN.SubGestaoCP.Piloto;
import RacingManagerLN.SubGestaoUsers.User;

import java.sql.*;
import java.util.*;

public class PilotoDAO implements Map<String, Piloto> {

    private static PilotoDAO singleton = null;

    private PilotoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS pilotos (" +
                    "Nomepiloto varchar(45) NOT NULL PRIMARY KEY," +
                    "Cts float NOT NULL," +
                    "Sva flaot NOT NULL)";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static PilotoDAO getInstance() {
        if (PilotoDAO.singleton == null) {
            PilotoDAO.singleton = new PilotoDAO();
        }
        return PilotoDAO.singleton;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM pilotos")) {
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
             ResultSet rs = stm.executeQuery("SELECT Nomepiloto FROM pilotos WHERE Nomepiloto ='"+ key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Piloto p = (Piloto) value;
        return this.containsKey(p.getNome());
    }

    @Override
    public Piloto get(Object key) {
        Piloto p = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM pilotos WHERE Nomepiloto='" + key.toString() + "'")) {
            if (rs.next()) {
                boolean admin=false;
                if(rs.getInt(3)==1) admin=true ;
                p = new Piloto(rs.getString(1), rs.getFloat(2),rs.getFloat(3));
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public Piloto put(String key, Piloto value) {
        Piloto piloto = null;
        String sql="";
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            if(this.containsKey(key)){
                sql= "UPDATE pilotos SET Cts='"+value.getCTS()+"',Sva='"+value.getSVA()+"' WHERE username='"+ key +"'";
            }else{
                sql= "INSERT INTO pilotos VALUES('"+key+"', '"+value.getCTS()+"', '"+value.getSVA()+"')";
            }
            st.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return piloto;
    }

    @Override
    public Piloto remove(Object key) {
        Piloto p = this.get(key);
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("DELETE FROM pilotos WHERE Nomepiloto='"+p.getNome()+"'")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Piloto> m) {
        for(Piloto p : m.values()){
            this.put(p.getNome(),p);
        }
    }

    @Override
    public void clear() {
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            st.executeUpdate("TRUNCATE pilotos");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> nomespiloto = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nomepiloto FROM pilotos")) {
            while (rs.next()) {
                nomespiloto.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return nomespiloto;
    }

    @Override
    public Collection<Piloto> values() {
        Collection<Piloto> allPilotos = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nomepiloto FROM pilotos")) {
            while (rs.next()) {
                allPilotos.add(this.get(rs.getString(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return allPilotos;

    }

    @Override
    public Set<Entry<String, Piloto>> entrySet() {
        Map.Entry<String, Piloto> entry;
        HashSet<Entry<String, Piloto>> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nomepiloto FROM pilotos")) {
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