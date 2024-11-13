package controlador.estado;

import modelo.Estado;
import modelo.EstadoDAO;
import modelo.DatabaseConnection;  // Importa tu clase DatabaseConnection

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/SvCrearEstado")
public class SvCrearEstado extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Estado nuevoEstado = new Estado();
        nuevoEstado.setNombre(nombre);
        nuevoEstado.setDescripcion(descripcion);

        Connection connection = null;
        try {
            // Obtén la conexión usando tu clase DatabaseConnection que utiliza JNDI
            connection = DatabaseConnection.connect();

            if (connection == null) {
                throw new SQLException("No se pudo establecer la conexión a la base de datos.");
            }

            EstadoDAO estadoDAO = new EstadoDAO(connection);
            estadoDAO.agregarEstado(nuevoEstado);
            response.sendRedirect("SvListarEstados");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear el estado: " + e.getMessage());
        } finally {
            // Asegúrate de cerrar la conexión
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
