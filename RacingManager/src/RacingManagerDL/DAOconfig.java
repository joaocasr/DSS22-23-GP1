package RacingManagerDL;

public class DAOconfig {
    static final String USERNAME = "g1";                            // Actualizar
    static final String PASSWORD = "g1dss";                         // Actualizar
    private static final String DATABASE = "RacingManagerdb";       // Actualizar
    private static final String DRIVER = "jdbc:mariadb";            // Usar para MariaDB jdbc:mysql://
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;
}
