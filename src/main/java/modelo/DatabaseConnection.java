package modelo;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() {
        Connection connection = null;
        try {
            // Obtén el DataSource desde el contexto JNDI
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MrPizza");
            connection = ds.getConnection();
            System.out.println("Conexión exitosa.");
        } catch (NamingException e) {
            System.out.println("Error de configuración del DataSource: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection conn = connect();
        // Aquí puedes hacer más pruebas con la conexión si lo deseas.

        // No olvides cerrar la conexión después de usarla.
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
