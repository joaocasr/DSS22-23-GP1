package data;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import data.DAOconfig;
import data.CampeonatoDAO;

import java.sql.*;
import java.util.*;

public class CircuitoDAO implements Map<String, Circuito> {

    private static CircuitoDAO singleton = null;

    private CircuitoDAO() {
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

    public static CircuitoDAO getInstance() {
        if (CircuitoDAO.singleton == null) {
            CircuitoDAO.singleton = new CircuitoDAO();
        }
        return CircuitoDAO.singleton;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM campeonatos")) {
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
             PreparedStatement st = conn.prepareStatement("SELECT Nomepiloto FROM pilotos WHERE Nomepiloto ='?'")) {

            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();

            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) { //TODO Check if this is as intended
        Circuito circuito = (Circuito) value;
        Circuito dbP = this.get(circuito.getNomeCampeonato());
        return circuito.equals(dbP);
    }

    @Override
    public Circuito get(Object key) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM campeonatos WHERE Nomecampeonato='?'")){

            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return new Circuito(rs.getString(1), rs.getInt(2), circuitos);
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }

    @Override
    public Circuito put(String key, Circuito value) {
        Circuito campeonato = null;
        String sql;
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){

            if(this.containsKey(key)){
                sql= "UPDATE campeonatos SET Cts='"+value.getSVA()+"',Sva='"+value.getCTS()+"' WHERE username='"+ key +"'";
            }else{
                sql= "INSERT INTO campeonatos VALUES('"+key+"', '"+value.getSVA()+"', '"+value.getCTS()+"')";
            }

            st.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return campeonato;
    }

    @Override
    public Circuito remove(Object key) {
        Circuito campeonato = this.get(key);
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            PreparedStatement st = con.prepareStatement("DELETE FROM campeonatos WHERE Nomecampeonato='?'")) {
            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campeonato;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Circuito> m) {
        for(Circuito circuito : m.values()){
            this.put(circuito.getNomeCircuito(),circuito);
        }
    }

    @Override
    public void clear() {
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            st.executeUpdate("TRUNCATE circuitos");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> nomescircuito = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nomecircuito FROM campeonatos")) {
            while (rs.next()) {
                nomescircuito.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return nomescircuito;
    }

    @Override
    public Collection<Circuito> values() {
        Collection<Circuito> allCircuitos = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM campeonatos")) {
            while (rs.next()) {
                allCircuitos.add(new Campeonato(rs.getString(1), rs.getFloat(2), rs.getFloat(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return allCircuitos;
    }

    @Override
    public Set<Entry<String, Circuito>> entrySet() {
        Map.Entry<String, Campeonato> entry;
        HashSet<Entry<String, Campeonato>> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM campeonatos")) {
            while (rs.next()) {
                entry =  new AbstractMap.SimpleEntry<>(rs.getString(1),
                        new Campeonato(rs.getString(1), rs.getFloat(2), rs.getFloat(3)));
                col.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return col;
    }
}
