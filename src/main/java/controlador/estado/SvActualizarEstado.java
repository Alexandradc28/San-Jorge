package controlador.estado;

import modelo.Estado;
import modelo.EstadoDAO;
import modelo.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/SvActualizarEstado")
public class SvActualizarEstado extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_estado = Integer.parseInt(request.getParameter("id_estado"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Estado estadoActualizado = new Estado();
        estadoActualizado.setId_estado(id_estado);
        estadoActualizado.setNombre(nombre);
        estadoActualizado.setDescripcion(descripcion);

        Connection connection = null;
        try {
            connection = DatabaseConnection.connect();
            EstadoDAO estadoDAO = new EstadoDAO(connection);
            estadoDAO.actualizarEstado(estadoActualizado);
            response.sendRedirect("SvListarEstados");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el estado: " + e.getMessage());
        } finally {
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
