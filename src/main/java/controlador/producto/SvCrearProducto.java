/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.producto;

import modelo.Producto;
import modelo.ProductoDAO;
import modelo.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/SvCrearProducto")
public class SvCrearProducto extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String tamanio = request.getParameter("tamanio");
        String imagen_url = request.getParameter("imagen_url");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int estado_id = Integer.parseInt(request.getParameter("estado_id"));
        int categoria_id = Integer.parseInt(request.getParameter("categoria_id"));

        Producto nuevoProducto = new Producto(0, estado_id, categoria_id, nombre, descripcion, tamanio, imagen_url, precio);

        try (Connection connection = DatabaseConnection.connect()) {
            ProductoDAO productoDAO = new ProductoDAO(connection);
            productoDAO.agregarProducto(nuevoProducto);
            response.sendRedirect("SvListarProductos");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear el producto: " + e.getMessage());
        }
    }
}
