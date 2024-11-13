<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Productos</title>
    <script>
        function llenarFormulario(producto) {
            document.getElementById("id_producto").value = producto.id_producto;
            document.getElementById("nombre").value = producto.nombre;
            document.getElementById("descripcion").value = producto.descripcion;
            document.getElementById("tamanio").value = producto.tamanio;
            document.getElementById("imagen_url").value = producto.imagen_url;
            document.getElementById("precio").value = producto.precio;
            document.getElementById("estado_id").value = producto.estado_id;
            document.getElementById("categoria_id").value = producto.categoria_id;
        }
    </script>
</head>
<body>
    <h1>Crear/Actualizar Producto</h1>
    <form action="SvActualizarProducto" method="POST">
        <input type="hidden" id="id_producto" name="id_producto" value="0">
        <label>Nombre:</label><input type="text" id="nombre" name="nombre" required><br>
        <label>Descripción:</label><input type="text" id="descripcion" name="descripcion" required><br>
        <label>Tamaño:</label><input type="text" id="tamanio" name="tamanio" required><br>
        <label>Imagen URL:</label><input type="text" id="imagen_url" name="imagen_url" required><br>
        <label>Precio:</label><input type="text" id="precio" name="precio" required><br>
        <label>ID Estado:</label><input type="text" id="estado_id" name="estado_id" required><br>
        <label>ID Categoría:</label><input type="text" id="categoria_id" name="categoria_id" required><br>
        <input type="submit" value="Crear/Actualizar Producto">
    </form>

    <h2>Lista de Productos</h2>
    <table border="1">
        <tr><th>ID</th><th>Nombre</th><th>Descripción</th><th>Tamaño</th><th>Imagen</th><th>Precio</th><th>Acciones</th></tr>
        <%
            List<Producto> productos = (List<Producto>) request.getAttribute("productos");
            if (productos != null) {
                for (Producto producto : productos) {
        %>
        <tr>
            <td><%= producto.getId_producto() %></td>
            <td><%= producto.getNombre() %></td>
            <td><%= producto.getDescripcion() %></td>
            <td><%= producto.getTamanio() %></td>
            <td><%= producto.getImagen_url() %></td>
            <td><%= producto.getPrecio() %></td>
            <td>
                <button type="button" onclick='llenarFormulario({
                    id_producto: <%= producto.getId_producto() %>,
                    nombre: "<%= producto.getNombre() %>",
                    descripcion: "<%= producto.getDescripcion() %>",
                    tamanio: "<%= producto.getTamanio() %>",
                    imagen_url: "<%= producto.getImagen_url() %>",
                    precio: <%= producto.getPrecio() %>,
                    estado_id: <%= producto.getEstado_id() %>,
                    categoria_id: <%= producto.getCategoria_id() %>
                })'>Editar</button>
                <form action="SvEliminarProducto" method="POST" style="display:inline;">
                    <input type="hidden" name="id_producto" value="<%= producto.getId_producto() %>">
                    <input type="submit" value="Eliminar">
                </form>
            </td>
        </tr>
        <% 
                }
            } 
        %>
    </table>
</body>
</html>
