package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    private Connection connection;

    public EstadoDAO(Connection connection) {
        this.connection = connection;
    }

    // Crear un nuevo estado
    public void agregarEstado(Estado estado) throws SQLException {
        if (connection == null) {
            throw new SQLException("La conexión es nula");
        }
        String sql = "INSERT INTO Estado (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, estado.getNombre());
            statement.setString(2, estado.getDescripcion());
            statement.executeUpdate();
        }
    }

    // Leer un estado por ID
    public Estado obtenerEstado(int id_estado) throws SQLException {
        Estado estado = null;
        String sql = "SELECT * FROM Estado WHERE id_estado = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_estado);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                estado = new Estado(resultSet.getInt("id_estado"),
                                    resultSet.getString("nombre"),
                                    resultSet.getString("descripcion"));
            }
        }
        return estado;
    }

    // Leer todos los estados
    public List<Estado> obtenerTodosLosEstados() throws SQLException {
        List<Estado> estados = new ArrayList<>();
        String sql = "SELECT * FROM Estado";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Estado estado = new Estado(resultSet.getInt("id_estado"),
                                            resultSet.getString("nombre"),
                                            resultSet.getString("descripcion"));
                estados.add(estado);
            }
        }
        return estados;
    }

    // Actualizar un estado
    public void actualizarEstado(Estado estado) throws SQLException {
        String sql = "UPDATE Estado SET nombre = ?, descripcion = ? WHERE id_estado = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, estado.getNombre());
            statement.setString(2, estado.getDescripcion());
            statement.setInt(3, estado.getId_estado());
            statement.executeUpdate();
        }
    }

    // Eliminar un estado
    public void eliminarEstado(int id_estado) throws SQLException {
        String sql = "DELETE FROM Estado WHERE id_estado = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id_estado);
            statement.executeUpdate();
        }
    }
}
