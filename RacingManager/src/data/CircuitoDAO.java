package data;
import RacingManagerLN.SubGestaoCC.Circuito.Chicane;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import RacingManagerLN.SubGestaoCC.Circuito.Curva;
import RacingManagerLN.SubGestaoCC.Circuito.Reta;

import java.sql.*;
import java.util.*;

public class CircuitoDAO implements Map<String, Circuito> {
    private static CircuitoDAO singleton = null;


    private CircuitoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
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
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM circuito")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;    }

    @Override
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT nomeCircuito FROM circuito WHERE nomeCircuito =?")) {

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
        Circuito c = (Circuito) value;
        Circuito dbC = this.get(c.getNomeCircuito());
        return c.equals(dbC);
    }

    @Override //6 3 3 3
    public Circuito get(Object key) {
        Circuito c = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM circuito"+
                     " INNER JOIN reta ON circuito.nomeCircuito=reta.fk_circuitoRT"+
                     " INNER JOIN chicane ON circuito.nomeCircuito=chicane.fk_circuitoCH"+
                     " INNER JOIN curva ON circuito.nomeCircuito=curva.fk_circuitoCR WHERE circuito.nomeCircuito=?;")){
            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();
            int n=0;
            while (rs.next()) {
                if(n==0) c = new Circuito(rs.getString(1),(double) rs.getFloat(2),rs.getInt(3),rs.getInt(4),rs.getInt(5), rs.getInt(6));
                c.addReta(new Reta(rs.getString(8),rs.getInt(9)));
                c.addChicane(new Chicane(rs.getString(11),rs.getInt(12)));
                c.addCurva(new Curva(rs.getString(14),rs.getInt(15)));
                n++;
            }
            }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return c;
    }

    @Override
    public Circuito put(String key, Circuito value) {
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()) {

                String sqlCirc = "INSERT INTO circuito VALUES('"+ value.getNomeCircuito() + "', '" + (float)value.getDistancia() + "', '" +
                        + value.getVoltas() + "', '" +value.getNumRetas()+ "', '" + value.getNumChicanes() + "', '" + value.getNumCurvas() + "', '" +null+"');";
                st.executeUpdate(sqlCirc);
                for(Reta r : value.getAllRetas()){
                    String sqlreta = "INSERT INTO reta VALUES('"+ r.getId()+ "', '" +r.getGdu()+ "', '" +value.getNomeCircuito()+"') "+
                            "ON DUPLICATE KEY UPDATE gduReta='"+r.getGdu() +"';";
                    st.executeUpdate(sqlreta);
                }
                for(Chicane chicane : value.getAllChicanes()){
                    String sqlchicane ="INSERT INTO chicane VALUES('"+ chicane.getIdChicane()+ "', '" +chicane.getGdu()+ "', '" +value.getNomeCircuito()+"') "+
                            "ON DUPLICATE KEY UPDATE gduChicane='"+chicane.getGdu() +"';";
                    st.executeUpdate(sqlchicane);
                }
                for(Curva curva : value.getAllCurvas()){
                    String sqlcurva = "INSERT INTO curva VALUES('"+ curva.getId()+ "', '" +curva.getGdu()+ "', '" +value.getNomeCircuito()+"') "+
                            "ON DUPLICATE KEY UPDATE gduCurva='"+curva.getGdu() +"';";
                    st.executeUpdate(sqlcurva);
                }

        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }

    @Override
    public Circuito remove(Object key) {
        Circuito c = this.get(key);

        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()) {

            String sqlreta= "DELETE FROM reta WHERE fk_circuitoRT='"+c.getNomeCircuito()+"';";
            st.executeUpdate(sqlreta);
            String sqlchicane= "DELETE FROM chicane WHERE fk_circuitoCH='"+c.getNomeCircuito()+"';";
            st.executeUpdate(sqlchicane);
            String sqlcurva= "DELETE FROM curva WHERE fk_circuitoCR='"+c.getNomeCircuito()+"';";
            st.executeUpdate(sqlcurva);
            String sqlcircuito = "DELETE FROM circuito WHERE nomeCircuito='"+c.getNomeCircuito()+"';";
            st.executeUpdate(sqlcircuito);

        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
            return c;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Circuito> m) {
        for(Circuito c : m.values()){
            this.put(c.getNomeCircuito(),c);
        }
    }

    @Override
    public void clear() {
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            st.executeUpdate("UPDATE reta SET fk_circuitoRT=NULL");
            st.executeUpdate("UPDATE chicane SET fk_circuitoCH=NULL");
            st.executeUpdate("UPDATE curva SET fk_circuitoCR=NULL");
            st.executeUpdate("TRUNCATE circuito");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> circuitos = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT nomeCircuito FROM circuito")) {
            while (rs.next()) {
                circuitos.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return circuitos;
    }

    @Override
    public Collection<Circuito> values() {
        Collection<Circuito> allCircuitos = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT nomeCircuito FROM circuito")) {
            while (rs.next()) {
                allCircuitos.add(this.get(rs.getString(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return allCircuitos;
    }

    @Override
    public Set<Entry<String, Circuito>> entrySet() {
        Map.Entry<String, Circuito> entry;
        HashSet<Entry<String, Circuito>> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM circuito")) {
            while (rs.next()) {
                entry =  new AbstractMap.SimpleEntry<>(rs.getString(1),
                        new Circuito(this.get(rs.getString(1))));
                col.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return col;
    }
}
