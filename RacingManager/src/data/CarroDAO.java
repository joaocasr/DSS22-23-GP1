package data;

import RacingManagerLN.SubGestaoCP.Carro.Carro;
import RacingManagerLN.SubGestaoUsers.User;

import java.sql.*;
import java.util.*;

public class CarroDAO implements Map<String, Carro> {
    private static CarroDAO singleton = null;

    private CarroDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS carros (" +
                    "IdCarro varchar(45) NOT NULL PRIMARY KEY," +
                    "Marca varchar(45) NOT NULL," +
                    "Modelo varchar(45) NOT NULL," +
                    "Cilindrada int(5) NOT NULL," +
                    "PotenciaCombustao int(10) NOT NULL,"+
                    "PAC float(5) NOT NULL," +
                    "TipoPneu varchar(45) NOT NULL," +
                    "Downforce float(5) NOT NULL," +
                    "Motor varchar(30) NOT NULL)";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CarroDAO getInstance() {
        if (CarroDAO.singleton == null) {
            CarroDAO.singleton = new CarroDAO();
        }
        return CarroDAO.singleton;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM carros")) {
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
             ResultSet rs = stm.executeQuery("SELECT IdCarro FROM carros WHERE IdCarro ='"+ key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Carro c = (Carro) value;
        return this.containsKey(c.getIdCarro());
    }

    @Override
    public Carro get(Object key) {
        Carro c = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM carros WHERE IdCarro='" + key.toString() + "'")) {
            if (rs.next()) {
                c = new Carro(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(6),Carro.converteStringPneu(rs.getString(7)),rs.getFloat(8),Carro.converteStringMotor(rs.getString(9)));
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    @Override
    public Carro put(String key, Carro value) {
        Carro carro = null;
        String sql="";
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            if(this.containsKey(key)){
                sql= "UPDATE carros SET Marca='"+value.getMarca()+"',Modelo='"+value.getModelo()+"',Cilindrada='"+value.getCilindrada()+"',PotenciaCombustao='"+value.getPotenciaCombustao()+"',PAC='"+value.getPac()+"',TipoPneu='"+value.getTipoPneu().toString()+"',Downforce='"+value.getDownforce()+"',Motor='"+value.getMotor().toString()+"' WHERE IdCarro='"+key+"'";
            }else{
                sql= "INSERT INTO carros VALUES('"+value.getIdCarro()+"', '"+value.getMarca()+"', '"+value.getModelo()+"', '"+value.getCilindrada()+"', '"+value.getPotenciaCombustao()+"', '"+value.getPac()+"', '"+value.getTipoPneu().toString()+"', '"+value.getDownforce()+"', '"+value.getMotor().toString()+"')";
            }
            st.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return carro;
    }

    @Override
    public Carro remove(Object key) {
        Carro c = this.get(key);
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("DELETE FROM carros WHERE IdCarro='"+c.getIdCarro()+"'")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Carro> m) {
        for(Carro c : m.values()){
            this.put(c.getIdCarro(),c);
        }
    }

    @Override
    public void clear() {
        try(Connection con = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
            Statement st = con.createStatement()){
            st.executeUpdate("TRUNCATE carros");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> carros = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT IdCarro FROM carros")) {
            while (rs.next()) {
                carros.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return carros;
    }

    @Override
    public Collection<Carro> values() {
        Collection<Carro> allCarros = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT IdCarro FROM carros")) {
            while (rs.next()) {
                allCarros.add(this.get(rs.getString(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return allCarros;

    }

    @Override
    public Set<Map.Entry<String, Carro>> entrySet() {
        Map.Entry<String, Carro> entry;
        HashSet<Map.Entry<String, Carro>> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT IdCarro FROM carros")) {
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
