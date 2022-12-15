package data;

import RacingManagerLN.SubGestaoCC.Campeonato;
import RacingManagerLN.SubGestaoCC.Circuito.Chicane;
import RacingManagerLN.SubGestaoCC.Circuito.Circuito;
import RacingManagerLN.SubGestaoCC.Circuito.Curva;
import RacingManagerLN.SubGestaoCC.Circuito.Reta;
import RacingManagerLN.SubGestaoCP.Piloto;

import java.sql.*;
import java.util.*;

public class CampDAO implements Map<String, Campeonato> {
    private CampDAO(){
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sqlCamp = "CREATE TABLE IF NOT EXISTS campeonato (" +
                    "NomeCampeonato varchar(45) NOT NULL PRIMARY KEY," +
                    "participantes int NOT NULL);";
            String sqlCirc = "CREATE TABLE IF NOT EXISTS circuito (" +
                    "`nomeCircuito` VARCHAR(45) NOT NULL," +
                    "`distancia` DOUBLE NOT NULL," +
                    "`voltas` INT NOT NULL," +
                    "`numRetas` INT NOT NULL," +
                    "`numCurvas` INT NOT NULL," +
                    "`numChicanes` INT NOT NULL," +
                    "`NomeCampeonato` VARCHAR(45) NOT NULL," +
                    "PRIMARY KEY (`nomeCircuito`, `NomeCampeonato`)," +
                    "INDEX `fk_circuito_Camp_idx` (`NomeCampeonato` ASC) VISIBLE," +
                    "CONSTRAINT `fk_circuito_Camp`" +
                    "  FOREIGN KEY (`NomeCampeonato`)" +
                    "  REFERENCES campeonato (`NomeCampeonato`)" +
                    "  ON DELETE CASCADE" +
                    "  ON UPDATE NO ACTION);";
            String sqlReta = "CREATE TABLE IF NOT EXISTS reta (" +
                    "`idReta` VARCHAR(20) NOT NULL," +
                    "`gduReta` INT NOT NULL," +
                    "`nomeCircuito` VARCHAR(45) NOT NULL," +
                    "`NomeCampeonato` VARCHAR(45) NOT NULL," +
                    "PRIMARY KEY (`idReta`, `nomeCircuito`, `NomeCampeonato`)," +
                    "INDEX `fk_reta_circuito1_idx` (`nomeCircuito` ASC, `NomeCampeonato` ASC) VISIBLE," +
                    "CONSTRAINT `fk_reta_circuito1`" +
                    "  FOREIGN KEY (`nomeCircuito` , `NomeCampeonato`)" +
                    "  REFERENCES circuito (`nomeCircuito` , `NomeCampeonato`)" +
                    "  ON DELETE CASCADE" +
                    "  ON UPDATE NO ACTION);";
            String sqlChicane = "CREATE TABLE IF NOT EXISTS chicane (" +
                    "`idChicane` VARCHAR(20) NOT NULL," +
                    "`gduChicane` INT NOT NULL," +
                    "`nomeCircuito` VARCHAR(45) NOT NULL," +
                    "`NomeCampeonato` VARCHAR(45) NOT NULL," +
                    "PRIMARY KEY (`idChicane`, `nomeCircuito`, `NomeCampeonato`)," +
                    "INDEX `fk_Chicane_circuito1_idx` (`nomeCircuito` ASC, `NomeCampeonato` ASC) VISIBLE," +
                    "CONSTRAINT `fk_Chicane_circuito1`" +
                    "  FOREIGN KEY (`nomeCircuito` , `NomeCampeonato`)" +
                    "  REFERENCES circuito (`nomeCircuito` , `NomeCampeonato`)" +
                    "  ON DELETE CASCADE" +
                    "  ON UPDATE NO ACTION);";
            String sqlCurva = "CREATE TABLE IF NOT EXISTS curva (" +
                    "  `idCurva` VARCHAR(20) NOT NULL," +
                    "  `gduCurva` INT NOT NULL," +
                    "  `nomeCircuito` VARCHAR(45) NOT NULL," +
                    "  `NomeCampeonato` VARCHAR(45) NOT NULL," +
                    "  PRIMARY KEY (`idCurva`, `nomeCircuito`, `NomeCampeonato`)," +
                    "  INDEX `fk_Curva_circuito1_idx` (`nomeCircuito` ASC, `NomeCampeonato` ASC) VISIBLE," +
                    "  CONSTRAINT `fk_Curva_circuito1`" +
                    "    FOREIGN KEY (`nomeCircuito` , `NomeCampeonato`)" +
                    "    REFERENCES circuito (`nomeCircuito` , `NomeCampeonato`)" +
                    "    ON DELETE CASCADE" +
                    "    ON UPDATE NO ACTION)";
            stm.executeUpdate(sqlCamp + sqlCirc + sqlReta + sqlChicane + sqlCurva);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
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
        Campeonato c;
        ArrayList<Circuito> cir = new ArrayList<>();
        ArrayList<Reta> retas;
        ArrayList<Curva> curvas;
        ArrayList<Chicane> chicanes;

        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM circuito WHERE NomeCampeonato=?");
             PreparedStatement stCamp = conn.prepareStatement("SELECT * FROM campeonato WHERE NomeCampeonato=?")){

            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                retas = new ArrayList<>();
                curvas = new ArrayList<>();
                chicanes = new ArrayList<>();

                try (PreparedStatement stR = conn.prepareStatement("SELECT * FROM reta WHERE nomeCircuito = ? AND NomeCampeonato=?");
                     PreparedStatement stC = conn.prepareStatement("SELECT * FROM curva WHERE nomeCircuito = ? AND NomeCampeonato=?");
                     PreparedStatement stCh = conn.prepareStatement("SELECT * FROM chicane WHERE nomeCircuito = ? AND NomeCampeonato=?")){

                    stR.setString(1, rs.getString(1));
                    stR.setString(2, key.toString());
                    ResultSet rsR = stR.executeQuery();

                    while(rsR.next())
                        retas.add(new Reta(rsR.getString(1), rsR.getInt(2)));

                    rsR.close();

                    stC.setString(1, rs.getString(1));
                    stC.setString(2, key.toString());
                    ResultSet rsC = stC.executeQuery();

                    while(rsC.next())
                        curvas.add(new Curva(rsC.getString(1), rsC.getInt(2)));

                    rsC.close();

                    stCh.setString(1, rs.getString(1));
                    stCh.setString(2, key.toString());
                    ResultSet rsCh = stCh.executeQuery();

                    while(rsCh.next())
                        chicanes.add(new Chicane(rsCh.getString(1), rsCh.getInt(2)));

                    rsCh.close();
                }

                cir.add(new Circuito(rs.getString(1), rs.getDouble(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6), retas, curvas, chicanes));
            }

            stCamp.setString(1, key.toString());
            ResultSet rsCamp = stCamp.executeQuery();

            c = new Campeonato(rsCamp.getString(1), rsCamp.getInt(2), cir);

            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return c;
    }

    @Override
    public Campeonato put(String key, Campeonato value) {
        Campeonato c = null;
        List<Circuito> cirs = value.getCircuitos();
        StringBuilder sql;

        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){

            if(this.containsKey(key)){
                sql = new StringBuilder("UPDATE campeonato SET NomeCampeonato='" + value.getNomeCampeonato()
                        + "',participantes='" + value.getParticipantes() + "' WHERE NomeCampeonato='" + key + "';");
                for (Circuito cir : cirs) {
                    sql.append("UPDATE circuito SET nomeCircuito='").append(cir.getNomeCircuito())
                            .append("',distancia='").append(cir.getDistancia()).append("',voltas='").append(cir.getVoltas())
                            .append("',numRetas='").append(cir.getNumRetas()).append("',numCurvas='").append(cir.getNumCurvas())
                            .append("',numChicanes='").append(cir.getNumChicanes()).append("',NomeCampeonato='").append(key)
                            .append("' WHERE nomeCircuito='").append(cir.getNomeCircuito()).append("';");

                    for (Reta r : cir.getAllRetas())
                        sql.append("UPDATE reta SET idReta='").append(r.getId())
                                .append("',gduReta='").append(r.getGdu())
                                .append("' WHERE nomeCircuito='").append(cir.getNomeCircuito())
                                .append("' AND NomeCampeonato='").append(key)
                                .append("';");

                    for (Curva cu : cir.getAllCurvas())
                        sql.append("UPDATE curva SET idCurva='").append(cu.getId())
                                .append("',gduCurva='").append(cu.getGdu())
                                .append("' WHERE nomeCircuito='").append(cir.getNomeCircuito())
                                .append("' AND NomeCampeonato='").append(key)
                                .append("';");

                    for (Chicane ch : cir.getAllChicanes())
                        sql.append("UPDATE chicane SET idChicane='").append(ch.getIdChicane())
                                .append("',gduChicane='").append(ch.getGdu())
                                .append("' WHERE nomeCircuito='").append(cir.getNomeCircuito())
                                .append("' AND NomeCampeonato='").append(key)
                                .append("';");
                }
            }else{
                sql = new StringBuilder("INSERT INTO campeonato VALUES('" + key + "', '" + value.getNomeCampeonato() + "', '"
                        + value.getParticipantes() + "');");

                for (Circuito cir : cirs) {
                    sql = new StringBuilder("INSERT INTO circuito VALUES('");

                    sql.append(cir.getNomeCircuito()).append("','").append(cir.getDistancia())
                            .append("','").append(cir.getVoltas()).append("','").append(cir.getNumRetas())
                            .append("','").append(cir.getNumCurvas()).append("','")
                            .append(cir.getNumChicanes()).append("','").append(key).append("');");

                    for (Reta r : cir.getAllRetas())
                        sql.append("INSERT INTO reta VALUES('").append(r.getId())
                                .append("','").append(r.getGdu())
                                .append("','").append(cir.getNomeCircuito())
                                .append("','").append(key)
                                .append("');");

                    for (Curva cu : cir.getAllCurvas())
                        sql.append("INSERT INTO curva VALUES('").append(cu.getId())
                                .append("','").append(cu.getGdu())
                                .append("','").append(cir.getNomeCircuito())
                                .append("','").append(key)
                                .append("');");

                    for (Chicane ch : cir.getAllChicanes())
                        sql.append("INSERT INTO chicane VALUES('").append(ch.getIdChicane())
                                .append("','").append(ch.getGdu())
                                .append("','").append(cir.getNomeCircuito())
                                .append("','").append(key)
                                .append("');");
                }
            }

            st.executeUpdate(sql.toString());
        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        return c;
    }

    @Override
    public Campeonato remove(Object key) {
        Campeonato c = this.get(key);
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            PreparedStatement st = con.prepareStatement("DELETE FROM campeonato WHERE NomeCampeonato=?")) {
            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();
            rs.close();
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
