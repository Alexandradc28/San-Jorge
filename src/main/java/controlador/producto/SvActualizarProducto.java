package controlador.producto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DatabaseConnection;
import modelo.Producto;
import modelo.ProductoDAO;

@WebServlet("/SvActualizarProducto")
public class SvActualizarProducto extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener y validar cada parámetro
        String idProductoStr = request.getParameter("id_producto");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String tamanio = request.getParameter("tamanio");
        String imagenUrl = request.getParameter("imagen_url");
        String precioStr = request.getParameter("precio");
        String estadoIdStr = request.getParameter("estado_id");
        String categoriaIdStr = request.getParameter("categoria_id");
        
        try {
            int idProducto = (idProductoStr != null && !idProductoStr.isEmpty()) ? Integer.parseInt(idProductoStr) : 0;
            double precio = (precioStr != null && !precioStr.isEmpty()) ? Double.parseDouble(precioStr) : 0.0;
            int estadoId = (estadoIdStr != null && !estadoIdStr.isEmpty()) ? Integer.parseInt(estadoIdStr) : 0;
            int categoriaId = (categoriaIdStr != null && !categoriaIdStr.isEmpty()) ? Integer.parseInt(categoriaIdStr) : 0;

            // Crear el objeto Producto
            Producto productoActualizado = new Producto(idProducto, estadoId, categoriaId, nombre, descripcion, tamanio, imagenUrl, precio);

            // Actualizar en la base de datos
            try (Connection connection = DatabaseConnection.connect()) {
                ProductoDAO productoDAO = new ProductoDAO(connection);
                productoDAO.actualizarProducto(productoActualizado);
                response.sendRedirect("SvListarProductos");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el producto: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro numérico inválido: " + e.getMessage());
        }
    }
}
