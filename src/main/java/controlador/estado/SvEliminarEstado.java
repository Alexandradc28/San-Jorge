package controlador.estado;

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

@WebServlet("/SvEliminarEstado")
public class SvEliminarEstado extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_estado = Integer.parseInt(request.getParameter("id_estado"));

        Connection connection = null;
        try {
            connection = DatabaseConnection.connect();
            EstadoDAO estadoDAO = new EstadoDAO(connection);
            estadoDAO.eliminarEstado(id_estado);
            response.sendRedirect("SvListarEstados");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el estado: " + e.getMessage());
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
