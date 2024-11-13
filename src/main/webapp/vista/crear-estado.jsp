<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Estado"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Estados</title>
        <script>
            function llenarFormularioEditar(nombre, descripcion, id_estado) {
                document.getElementById('nombreEditar').value = nombre;
                document.getElementById('descripcionEditar').value = descripcion;
                document.getElementById('idEstadoEditar').value = id_estado;
            }
        </script>
    </head>
    <body>
        <h1>Crear Estado</h1>
        <form action="SvCrearEstado" method="POST">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
            <br>
            <label for="descripcion">Descripción:</label>
            <input type="text" id="descripcion" name="descripcion" required>
            <br>
            <input type="submit" value="Crear Estado">
        </form>

        <h2>Lista de Estados</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Estado> estados = (List<Estado>) request.getAttribute("estados");
                    if (estados != null && !estados.isEmpty()) {
                        for (Estado estado : estados) {
                %>
                <tr>
                    <td><%= estado.getId_estado() %></td>
                    <td><%= estado.getNombre() %></td>
                    <td><%= estado.getDescripcion() %></td>
                    <td>
                        <form action="SvEliminarEstado" method="POST" style="display:inline;">
                            <input type="hidden" name="id_estado" value="<%= estado.getId_estado() %>">
                            <input type="submit" value="Eliminar">
                        </form>
                        <button onclick="llenarFormularioEditar('<%= estado.getNombre() %>', '<%= estado.getDescripcion() %>', <%= estado.getId_estado() %>)">Editar</button>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4">No hay estados disponibles.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <h2>Editar Estado</h2>
        <form action="SvActualizarEstado" method="POST">
            <input type="hidden" id="idEstadoEditar" name="id_estado">
            <label for="nombreEditar">Nombre:</label>
            <input type="text" id="nombreEditar" name="nombre" required>
            <br>
            <label for="descripcionEditar">Descripción:</label>
            <input type="text" id="descripcionEditar" name="descripcion" required>
            <br>
            <input type="submit" value="Actualizar Estado">
        </form>
    </body>
</html>
