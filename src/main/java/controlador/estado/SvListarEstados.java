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
import java.util.List;

@WebServlet("/SvListarEstados")
public class SvListarEstados extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = DatabaseConnection.connect();
            EstadoDAO estadoDAO = new EstadoDAO(connection);
            List<Estado> estados = estadoDAO.obtenerTodosLosEstados();
            request.setAttribute("estados", estados);
            request.getRequestDispatcher("vista/crear-estado.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al listar los estados: " + e.getMessage());
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
