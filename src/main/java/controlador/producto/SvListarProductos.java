/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DatabaseConnection;
import modelo.Producto;
import modelo.ProductoDAO;

/**
 *
 * @author rojas
 */
@WebServlet("/SvListarProductos")
public class SvListarProductos extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseConnection.connect()) {
            ProductoDAO productoDAO = new ProductoDAO(connection);
            List<Producto> productos = productoDAO.obtenerTodosLosProductos();
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("vista/productos.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al listar los productos: " + e.getMessage());
        }
    }
}
