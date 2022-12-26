package RacingManagerDL;

import RacingManagerLN.SubGestaoCP.Piloto;

import java.sql.*;
import java.util.*;

public class PilotoDAO implements Map<String, Piloto> {

    private static PilotoDAO singleton = null;

    private PilotoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS pilotos (" +
                    "Nomepiloto varchar(45) NOT NULL PRIMARY KEY," +
                    "Sva float NOT NULL," +
                    "Cts float NOT NULL)";
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
             PreparedStatement st = conn.prepareStatement("SELECT Nomepiloto FROM pilotos WHERE Nomepiloto =?")) {

            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();

            r = rs.next();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Piloto p = (Piloto) value;
        Piloto dbP = this.get(p.getNome());
        return p.equals(dbP);
    }

    @Override
    public Piloto get(Object key) {
        Piloto p = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM pilotos WHERE Nomepiloto=?")){

            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                p = new Piloto(rs.getString(1), rs.getFloat(2),rs.getFloat(3));
            }

            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public Piloto put(String key, Piloto value) {
        Piloto piloto = null;
        String sql;
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){

            if(this.containsKey(key)){
                sql= "UPDATE pilotos SET Cts='"+value.getCTS()+"',Sva='"+value.getSVA()+"' WHERE Nomepiloto='"+ value.getNome() +"'";
            }else{
                sql= "INSERT INTO pilotos VALUES('"+value.getNome()+"', '"+value.getSVA()+"', '"+value.getCTS()+"')";
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
            PreparedStatement st = con.prepareStatement("DELETE FROM pilotos WHERE Nomepiloto=?")) {
            st.setString(1, p.getNome());
            ResultSet rs = st.executeQuery();
            rs.close();
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
             ResultSet rs = stm.executeQuery("SELECT * FROM pilotos")) {
            while (rs.next()) {
                allPilotos.add(new Piloto(rs.getString(1), rs.getFloat(2), rs.getFloat(3)));
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
             ResultSet rs = stm.executeQuery("SELECT * FROM pilotos")) {
            while (rs.next()) {
                entry =  new AbstractMap.SimpleEntry<>(rs.getString(1),
                        new Piloto(rs.getString(1), rs.getFloat(2), rs.getFloat(3)));
                col.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return col;
    }
}
