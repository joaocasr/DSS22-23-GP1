package data;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCC.Circuito.Chicane;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import RacingManagerLN.SubGestaoCC.Circuito.Curva;
import RacingManagerLN.SubGestaoCC.Circuito.Reta;
import RacingManagerLN.SubGestaoCP.Piloto;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class CampDAO implements Map<String, Campeonato> {
    private static CampDAO singleton = null;


    private CampDAO(){
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sqlCamp = "CREATE TABLE IF NOT EXISTS campeonato (" +
                    "NomeCampeonato varchar(45) NOT NULL PRIMARY KEY," +
                    "participantes int(10) NOT NULL);";
            stm.executeUpdate(sqlCamp);
            String sqlCirc = "CREATE TABLE IF NOT EXISTS circuito ("+
                    "nomeCircuito VARCHAR(45) NOT NULL PRIMARY KEY,"+
                    "distancia float(10) NOT NULL,"+
                    "voltas int(10) NOT NULL,"+
                    "numRetas int(10) NOT NULL,"+
                    "numChicanes int(10) NOT NULL,"+
                    "numCurvas int(10) NOT NULL,"+
                    "fk_campeonato VARCHAR(45) DEFAULT NULL);";
            stm.executeUpdate(sqlCirc);
            String sqlReta = "CREATE TABLE IF NOT EXISTS reta (" +
                    "idReta VARCHAR(20) NOT NULL PRIMARY KEY," +
                    "gduReta int(10) NOT NULL," +
                    "fk_circuitoRT VARCHAR(45) NOT NULL," +
                    "CONSTRAINT fk_reta_circuito1" +
                    "  FOREIGN KEY (fk_circuitoRT)" +
                    "  REFERENCES circuito (nomeCircuito)" +");";
            stm.executeUpdate(sqlReta);
            String sqlChicane = "CREATE TABLE IF NOT EXISTS chicane (" +
                    "idChicane VARCHAR(20) NOT NULL PRIMARY KEY," +
                    "gduChicane int(10) NOT NULL," +
                    "fk_circuitoCH VARCHAR(45) NOT NULL," +
                    "CONSTRAINT fk_Chicane_circuito1" +
                    "  FOREIGN KEY (fk_circuitoCH)" +
                    "  REFERENCES circuito(nomeCircuito)" + ");";
            stm.executeUpdate(sqlChicane);
            String sqlCurva = "CREATE TABLE IF NOT EXISTS curva (" +
                    "idCurva VARCHAR(20) NOT NULL PRIMARY KEY," +
                    "gduCurva int(10) NOT NULL," +
                    "fk_circuitoCR VARCHAR(45) NOT NULL," +
                    "  CONSTRAINT fk_Curva_circuito" +
                    "    FOREIGN KEY (fk_circuitoCR)" +
                    "    REFERENCES circuito (nomeCircuito)" +")";
            stm.executeUpdate(sqlCurva);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CampDAO getInstance() {
        if (CampDAO.singleton == null) {
            CampDAO.singleton = new CampDAO();
        }
        return CampDAO.singleton;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM campeonato")) {
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
             PreparedStatement st = conn.prepareStatement("SELECT NomeCampeonato FROM campeonato WHERE NomeCampeonato =?")) {

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
        Campeonato c = (Campeonato) value;
        Campeonato dbC = this.get(c.getNomeCampeonato());
        return c.equals(dbC);
    }

    @Override
    public Campeonato get(Object key) {
        Circuito circuito = null;

        Set<String> circuitos = new HashSet<>();
        Set<Campeonato> campeonatos=new HashSet<>();

        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement st = conn.createStatement()) {


            String sql1 = "SELECT circuito.nomeCircuito,campeonato.participantes FROM campeonato "+
                    "INNER JOIN circuito ON circuito.fk_campeonato='"+key.toString()+"';";
            ResultSet rs = st.executeQuery(sql1);
            while(rs.next()) {
                campeonatos.add(new Campeonato(key.toString(),rs.getInt(2)));
                circuitos.add(rs.getString(1));
            }
            rs.close();

            Campeonato c = (Campeonato) campeonatos.toArray()[0];
            for(String cir : circuitos) {
                String sql2 = "SELECT * FROM circuito" +
                        " INNER JOIN reta ON circuito.nomeCircuito=reta.fk_circuitoRT" +
                        " INNER JOIN chicane ON circuito.nomeCircuito=chicane.fk_circuitoCH" +
                        " INNER JOIN curva ON circuito.nomeCircuito=curva.fk_circuitoCR WHERE circuito.nomeCircuito='" + cir + "';";
                ResultSet rs2 = st.executeQuery(sql2);
                int n = 0;
                while (rs2.next()) {
                    if (n == 0) circuito = new Circuito(rs2.getString(1), rs2.getDouble(2), rs2.getInt(3), rs2.getInt(4), rs2.getInt(5), rs2.getInt(6));
                    circuito.addReta(new Reta(rs2.getString(8), rs2.getInt(9)));
                    circuito.addChicane(new Chicane(rs2.getString(11), rs2.getInt(12)));
                    circuito.addCurva(new Curva(rs2.getString(14), rs2.getInt(15)));
                    n++;
                }
                c.addCircuito(circuito);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return (Campeonato) campeonatos.toArray()[0];
    }

    @Override
    public Campeonato put(String key, Campeonato value) {
        Campeonato c = null;
        List<Circuito> cirs = value.getCircuitos();

        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()) {
            String sql = "INSERT INTO campeonato  VALUES('"+ value.getNomeCampeonato() + "', '" + value.getParticipantes()+"');";

            st.executeUpdate(sql);
            for(String circuito: cirs.stream().map(Circuito::getNomeCircuito).toList()){
                String circuitosql = "UPDATE circuito SET fk_campeonato='"+ value.getNomeCampeonato() +"' WHERE nomeCircuito='"+circuito+"';";
                st.executeUpdate(circuitosql);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    @Override
    public Campeonato remove(Object key) {
        Campeonato c = this.get(key);
        List<Circuito> cirs = c.getCircuitos();
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()) {
            for(String circuito: cirs.stream().map(Circuito::getNomeCircuito).toList()){
                String sqlcircuito = "UPDATE circuito SET fk_campeonato='"+ null +"' WHERE nomeCircuito='"+circuito+"';";
                st.executeUpdate(sqlcircuito);
            }
            String sqlcamp = "DELETE FROM campeonato WHERE NomeCampeonato='"+c.getNomeCampeonato()+"';";
            st.executeUpdate(sqlcamp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> m) {
        for(Campeonato c : m.values()){
            this.put(c.getNomeCampeonato(),c);
        }
    }

    @Override
    public void clear() {
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            st.executeUpdate("UPDATE circuito SET fk_campeonato=NULL");
            st.executeUpdate("TRUNCATE campeonato");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> nomescamp = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT NomeCampeonato FROM campeonato")) {
            while (rs.next()) {
                nomescamp.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return nomescamp;
    }

    @Override
    public Collection<Campeonato> values() {
        Collection<Campeonato> allCamp = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT NomeCampeonato FROM campeonato")) {
            while (rs.next()) {
                allCamp.add(this.get(rs.getString(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return allCamp;
    }

    @Override
    public Set<Entry<String, Campeonato>> entrySet() {
        Map.Entry<String, Campeonato> entry;
        HashSet<Entry<String, Campeonato>> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM campeonato")) {
            while (rs.next()) {
                entry =  new AbstractMap.SimpleEntry<>(rs.getString(1),
                        new Campeonato(this.get(rs.getString(1))));
                col.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return col;
    }
}
