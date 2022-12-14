package data;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import data.DAOconfig;
import data.CampeonatoDAO;

import java.sql.*;
import java.util.*;

public class CircuitosDosCampeonatosDAO implements Map<Integer, Map.Entry<String, String>> {

    private static CircuitosDosCampeonatosDAO singleton = null;

    private CircuitosDosCampeonatosDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS circuitosDosCampeonatos (" +
                    "campeonatoPilotId SERIAL PRIMARY KEY," +
                    "Nomecampeonato varchar(45) NOT NULL,"+
                    "Nomecircuito varchar(45) NOT NULL," +
                    "foreign key(Nomecampeonato) references campeonatos(Nomecampeonato)," +
                    "foreign key(Nomecircuito) references circuitos(Nomecircuito))";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CircuitosDosCampeonatosDAO getInstance() {
        if (CircuitosDosCampeonatosDAO.singleton == null) {
            CircuitosDosCampeonatosDAO.singleton = new CircuitosDosCampeonatosDAO();
        }
        return CircuitosDosCampeonatosDAO.singleton;
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
        Campeonato campeonato = (Campeonato) value;
        Campeonato dbP = this.get(campeonato.getNomeCampeonato());
        return campeonato.equals(dbP);
    }

    @Override
    public Campeonato get(Object key) { //TODO buscar circuitos do campeonato
        List<Circuito> circuitos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM campeonatos WHERE Nomecampeonato='?'")){

            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return new Campeonato(rs.getString(1), rs.getInt(2), circuitos);
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }

    @Override
    public Campeonato put(String key, Campeonato value) {
        Campeonato campeonato = null;
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
    public Campeonato remove(Object key) {
        Campeonato campeonato = this.get(key);
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
    public void putAll(Map<? extends String, ? extends Piloto> m) {
        for(Piloto p : m.values()){
            this.put(p.getNome(),p);
        }
    }

    @Override
    public void clear() {
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            st.executeUpdate("TRUNCATE campeonatos");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> nomescampeonato = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nomecampeonato FROM campeonatos")) {
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
    public Collection<Campeonato> values() {
        Collection<Campeonato> allCampeonatos = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM campeonatos")) {
            while (rs.next()) {
                allCampeonatos.add(new Campeonato(rs.getString(1), rs.getFloat(2), rs.getFloat(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return allCampeonatos;
    }

    @Override
    public Set<Entry<String, Campeonato>> entrySet() {
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
