package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO Producto (estado_id, categoria_id, nombre, descripcion, tamanio, imagen_url, precio) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, producto.getEstado_id());
            statement.setInt(2, producto.getCategoria_id());
            statement.setString(3, producto.getNombre());
            statement.setString(4, producto.getDescripcion());
            statement.setString(5, String.valueOf(producto.getTamanio()));
            statement.setString(6, producto.getImagen_url());
            statement.setDouble(7, producto.getPrecio());
            statement.executeUpdate();
        }
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        String sql = "UPDATE Producto SET estado_id = ?, categoria_id = ?, nombre = ?, descripcion = ?, tamanio = ?, imagen_url = ?, precio = ? WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, producto.getEstado_id());
            statement.setInt(2, producto.getCategoria_id());
            statement.setString(3, producto.getNombre());
            statement.setString(4, producto.getDescripcion());
            statement.setString(5, String.valueOf(producto.getTamanio()));
            statement.setString(6, producto.getImagen_url());
            statement.setDouble(7, producto.getPrecio());
            statement.setInt(8, producto.getId_producto());
            statement.executeUpdate();
        }
    }

    public void eliminarProducto(int id_producto) throws SQLException {
        String sql = "DELETE FROM Producto WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_producto);
            statement.executeUpdate();
        }
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Producto producto = new Producto(
                        resultSet.getInt("id_producto"),
                        resultSet.getInt("estado_id"),
                        resultSet.getInt("categoria_id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("tamanio"),
                        resultSet.getString("imagen_url"),
                        resultSet.getDouble("precio")
                );
                productos.add(producto);
            }
        }
        return productos;
    }
    public List<Producto> obtenerProductosPorCategoria(String categoria) throws SQLException {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM Producto WHERE categoria_id = (SELECT id_categoria FROM Categoria WHERE nombre = ?)";
    
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, categoria);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Producto producto = new Producto(
                        resultSet.getInt("id_producto"),
                        resultSet.getInt("estado_id"),
                        resultSet.getInt("categoria_id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("tamanio"),
                        resultSet.getString("imagen_url"),
                        resultSet.getDouble("precio")
                );
                productos.add(producto);
            }
        }
    }
    return productos;
}

}

