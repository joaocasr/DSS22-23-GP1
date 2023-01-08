package RacingManagerDL;

import RacingManagerLN.SubGestaoCP.Carro.*;

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
                    "PotenciaEletrica int(10) DEFAULT 0,"+
                    "PAC float(5) NOT NULL," +
                    "TipoPneu varchar(45) NOT NULL," +
                    "Downforce float(5) NOT NULL," +
                    "Categoria varchar(15) NOT NULL," +
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
             PreparedStatement st = conn.prepareStatement("SELECT IdCarro FROM carros WHERE IdCarro =?")) {
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
        Carro c = (Carro) value;
        Carro dbc = this.get(c.getIdCarro());
        return c.equals(dbc);
    }

    @Override
    public Carro get(Object key) {
        Carro c = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             PreparedStatement st = conn.prepareStatement("SELECT * FROM carros WHERE IdCarro=?")) {

            st.setString(1, key.toString());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String categoria = rs.getString(10);
                if(categoria.equals("C1")) c = new C1(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(7),Carro.converteStringPneu(rs.getString(8)),rs.getFloat(9),Carro.converteStringMotor(rs.getString(11)));
                else if(categoria.equals("C2")) c = new C2(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(7),Carro.converteStringPneu(rs.getString(8)),rs.getFloat(9),Carro.converteStringMotor(rs.getString(11)));
                else if(categoria.equals("GT")) c = new GT(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(7),Carro.converteStringPneu(rs.getString(8)),rs.getFloat(9),Carro.converteStringMotor(rs.getString(11)));
                else if(categoria.equals("SC")) c = new SC(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(7),Carro.converteStringPneu(rs.getString(8)),rs.getFloat(9),Carro.converteStringMotor(rs.getString(11)));

                else if(categoria.equals("C1Hibrido")) c = new C1Hibrido(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getFloat(7),Carro.converteStringPneu(rs.getString(8)),rs.getFloat(9),Carro.converteStringMotor(rs.getString(11)));
                else if(categoria.equals("C2Hibrido")) c = new C2Hibrido(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getFloat(7),Carro.converteStringPneu(rs.getString(8)),rs.getFloat(9),Carro.converteStringMotor(rs.getString(11)));
                else if(categoria.equals("GTHibrido")) c = new GTHibrido(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getFloat(7),Carro.converteStringPneu(rs.getString(8)),rs.getFloat(9),Carro.converteStringMotor(rs.getString(11)));

            }
            rs.close();
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
            String categoria = value.getCategoria();
            System.out.println("categor");
            if(this.containsKey(key)){
                if(categoria.equals("C1Hibrido"))  sql= "UPDATE carros SET Marca='"+value.getMarca()+"',Modelo='"+value.getModelo()+"',Cilindrada='"+value.getCilindrada()+"',PotenciaCombustao='"+value.getPotenciaCombustao()+"',PotenciaEletrica='"+((C1Hibrido) value).getPotenciaEletrica()+"',PAC='"+value.getPac()+"',TipoPneu='"+value.getTipoPneu().toString()+"',Downforce='"+value.getDownforce()+"',Categoria='"+value.getCategoria()+"',Motor='"+value.getMotor().toString()+"' WHERE IdCarro='"+key+"'";
                else if(categoria.equals("C2Hibrido"))  sql= "UPDATE carros SET Marca='"+value.getMarca()+"',Modelo='"+value.getModelo()+"',Cilindrada='"+value.getCilindrada()+"',PotenciaCombustao='"+value.getPotenciaCombustao()+"',PotenciaEletrica='"+((C2Hibrido) value).getPotenciaEletrica()+"',PAC='"+value.getPac()+"',TipoPneu='"+value.getTipoPneu().toString()+"',Downforce='"+value.getDownforce()+"',Categoria='"+value.getCategoria()+"',Motor='"+value.getMotor().toString()+"' WHERE IdCarro='"+key+"'";
                else if(categoria.equals("GTHibrido"))  sql= "UPDATE carros SET Marca='"+value.getMarca()+"',Modelo='"+value.getModelo()+"',Cilindrada='"+value.getCilindrada()+"',PotenciaCombustao='"+value.getPotenciaCombustao()+"',PotenciaEletrica='"+((GTHibrido) value).getPotenciaEletrica()+"',PAC='"+value.getPac()+"',TipoPneu='"+value.getTipoPneu().toString()+"',Downforce='"+value.getDownforce()+"',Categoria='"+value.getCategoria()+"',Motor='"+value.getMotor().toString()+"' WHERE IdCarro='"+key+"'";
                else  sql= "UPDATE carros SET Marca='"+value.getMarca()+"',Modelo='"+value.getModelo()+"',Cilindrada='"+value.getCilindrada()+"',PotenciaCombustao='"+value.getPotenciaCombustao()+"',PotenciaEletrica='"+0+"',PAC='"+value.getPac()+"',TipoPneu='"+value.getTipoPneu().toString()+"',Downforce='"+value.getDownforce()+"',Categoria='"+value.getCategoria()+"',Motor='"+value.getMotor().toString()+"' WHERE IdCarro='"+key+"'";

            }else{
                if(categoria.equals("C1Hibrido")) sql= "INSERT INTO carros VALUES('"+value.getIdCarro()+"', '"+value.getMarca()+"', '"+value.getModelo()+"', '"+value.getCilindrada()+"', '"+value.getPotenciaCombustao()+"', '"+((C1Hibrido) value).getPotenciaEletrica()+"', '"+value.getPac()+"', '"+value.getTipoPneu().toString()+"', '"+value.getDownforce()+"', '"+value.getCategoria()+"', '"+value.getMotor().toString()+"')";
                else if(categoria.equals("C2Hibrido")) sql= "INSERT INTO carros VALUES('"+value.getIdCarro()+"', '"+value.getMarca()+"', '"+value.getModelo()+"', '"+value.getCilindrada()+"', '"+value.getPotenciaCombustao()+"', '"+((C2Hibrido) value).getPotenciaEletrica()+"', '"+value.getPac()+"', '"+value.getTipoPneu().toString()+"', '"+value.getDownforce()+"', '"+value.getCategoria()+"', '"+value.getMotor().toString()+"')";
                else if(categoria.equals("GTHibrido")) sql= "INSERT INTO carros VALUES('"+value.getIdCarro()+"', '"+value.getMarca()+"', '"+value.getModelo()+"', '"+value.getCilindrada()+"', '"+value.getPotenciaCombustao()+"', '"+((GTHibrido) value).getPotenciaEletrica()+"', '"+value.getPac()+"', '"+value.getTipoPneu().toString()+"', '"+value.getDownforce()+"', '"+value.getCategoria()+"', '"+value.getMotor().toString()+"')";
                else sql= "INSERT INTO carros VALUES('"+value.getIdCarro()+"', '"+value.getMarca()+"', '"+value.getModelo()+"', '"+value.getCilindrada()+"', '"+value.getPotenciaCombustao()+"', '"+0+"', '"+value.getPac()+"', '"+value.getTipoPneu().toString()+"', '"+value.getDownforce()+"', '"+value.getCategoria()+"', '"+value.getMotor().toString()+"')";
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
            PreparedStatement st = con.prepareStatement("DELETE FROM carros WHERE IdCarro=?")) {

            st.setString(1, c.getIdCarro());
            ResultSet rs = st.executeQuery();
            rs.close();

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
             /*   allCarros.add(new Carro(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getInt(5),
                        rs.getFloat(6), Carro.tipoPneu.valueOf(rs.getString(7)),
                        rs.getFloat(8), Carro.modoMotor.valueOf(rs.getString(9))));
            */
                allCarros.add(get(rs.getString(1)));
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
                entry =  new AbstractMap.SimpleEntry<>(rs.getString(1),get(rs.getString(1)));
                /*
                * new Carro(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getInt(5), rs.getFloat(6), Carro.tipoPneu.valueOf(rs.getString(7)),
                        rs.getFloat(8), Carro.modoMotor.valueOf(rs.getString(9)))
                * */
                col.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }
}