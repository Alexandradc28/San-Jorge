/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DatabaseConnection;
import modelo.ProductoDAO;

/**
 *
 * @author rojas
 */
@WebServlet("/SvEliminarProducto")
public class SvEliminarProducto extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_producto = Integer.parseInt(request.getParameter("id_producto"));

        try (Connection connection = DatabaseConnection.connect()) {
            ProductoDAO productoDAO = new ProductoDAO(connection);
            productoDAO.eliminarProducto(id_producto);
            response.sendRedirect("SvListarProductos");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el producto: " + e.getMessage());
        }
    }
}
