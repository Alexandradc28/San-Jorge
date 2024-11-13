<%@ page import="java.util.List" %>
<%@ page import="modelo.Producto" %>
<%@ page import="modelo.ProductoDAO" %>
<%@ page import="modelo.DatabaseConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mr. Pizza - Productos</title>
        <link rel="shortcut icon" type="image/png" href="https://i.postimg.cc/nVNpwLWT/logo3.png">
        <link rel="stylesheet" href="vista/css/styles.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <!--bibliotecas para Bootstrap-->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
                crossorigin="anonymous"></script>

        <!-- icons links -->
        <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <div class="all-content">
            <header class="header">
                <nav class="navbar navbar-expand-md" id="navbar">
                    <a class="navbar-brand" href="index.html" id="logo"><img src="https://i.postimg.cc/KvLxX2Sb/mrpizza2.png" alt="" width="100">Mr. Pizza Paita</a>
                    
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                        <span><img src="https://i.postimg.cc/FzMWb8Dg/menu.png" alt="" width="30"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="collapsibleNavbar">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="index.html">Inicio</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a href="SvListarProductos" class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
                                    Productos
                                </a>
                                <div class="dropdown-menu">
                                    <a href="SvListarProductos#clasicas" class="dropdown-item">Clásicas</a>
                                    <a href="SvListarProductos#exclusivas" class="dropdown-item">Exclusivas de Mr.Pizza</a>
                                    <a href="SvListarProductos#tequeños y alitas" class="dropdown-item">Tequeños y Alitas</a>
                                    <a href="SvListarProductos#pan al ajo" class="dropdown-item">Pan al ajo</a>
                                    <a href="SvListarProductos#calzone" class="dropdown-item">Calzone</a>
                                    <a href="SvListarProductos#bebidas" class="dropdown-item">Bebidas</a>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="vista/galeria.html">Galeria</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="vista/nosotros.html">Nosotros</a>
                            </li>
                        </ul>
                    </div>

                    <div class="container-icon">
                        <div class="container-cart-icon">
                            <div class="icons">
                                <a href="#"><i class='bx bxs-user-plus' style="width:43px;"></i></a>
                                <i class='bx bxs-cart-alt' style="width:45px;"></i>
                            </div>
                            <div class="count-products">
                                <span id="contador-productos">0</span>
                            </div>
                        </div>

                        <div class="container-cart-products hidden-cart">
                            <div class="row-product hidden">
                                <div class="cart-product">
                                    <div class="info-cart-product">
                                        <h6 class="cantidad-producto-carrito"></h6>
                                        <h3 class="titulo-producto-carrito"></h3>
                                        <h6 class="precio-producto-carrito"></h6>
                                    </div>
                                    <i class='bx bx-x-circle'></i>
                                </div>
                            </div>

                            <div class="cart-total hidden">
                                <h3 class="total-pagar"></h3>
                                <button id="btn-pay">Pagar</button>
                            </div>
                            <p class="cart-empty">El carrito está vacío</p>
                        </div>
                    </div>
                </nav>
            </header>

            <!-- Products sections here -->
            <section id="product-cards">
                <div class="container">
                    <%
                        Connection connection = null;
                        try {
                            connection = DatabaseConnection.connect();
                            ProductoDAO productoDAO = new ProductoDAO(connection);

                            // Array con nombres de las categorías
                            String[] categorias = {"clasicas", "exclusivas", "tequeños y alitas", "pan al ajo", "calzone", "bebidas"};
                            String[] titulos = {"Clásicas", "Exclusivas de Mr. Pizza", "Tequeños y Alitas", "Pan al Ajo", "Calzone", "Bebidas"};

                            for (int i = 0; i < categorias.length; i++) {
                                List<Producto> productos = productoDAO.obtenerProductosPorCategoria(categorias[i]);
                    %>
                    <div class="container" id="<%= categorias[i] %>">
                        <h1><%= titulos[i] %></h1>
                        <div class="row" style="margin-top:20px;">
                            <%
                                for (Producto producto : productos) {
                            %>
                            <div class="col-md-4 py-3 py-md-0">
                                <div class="card">
                                    <img src="<%= producto.getImagen_url() %>" alt="">
                                    <div class="card-body">
                                        <h3><%= producto.getNombre() %></h3>
                                        <p><%= producto.getDescripcion() %></p>
                                        <h6>S/<%= producto.getPrecio() %></h6>
                                        <button class="btn-add-cart">Agregar</button>
                                    </div>
                                </div>
                            </div>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <%
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    %>
                </div>
            </section>

            <footer id="footer">
                <div class="text-center">
                    <img src="https://i.postimg.cc/qvxrRxvZ/mrpizza.png" alt="" width="100px">
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 py-3 py-md-0">
                            <h3 class="text-center">Contáctanos</h3>
                            <p class="text-center">No dudes en contactarnos para recibir asesoramiento personalizado.</p>
                            <div class="icons text-center">
                                <a href="https://wa.me/c/51983736194"><i class='bx bxs-phone'></i></a>
                                <a href="mailto:mrpizza@outlook.com"><i class='bx bxs-envelope'></i></a>
                            </div>
                        </div>
                        <div class="col-md-4 py-3 py-md-0">
                            <h3 class="text-center">Síguenos</h3>
                            <p class="text-center">Únete a nuestra comunidad de amantes de las pizzas auténticas.</p>
                            <div class="icons text-center">
                                <a href="https://www.facebook.com/Mr.PizzaPaita/?locale=es_LA"><i class='bx bxl-facebook'></i></a>
                                <a href="https://www.instagram.com/mr.pizza.paita/"><i class="bx bxl-instagram"></i></a>
                                <a href="https://www.tiktok.com/@mr.pizza.paita"><i class='bx bxl-tiktok'></i></a>
                            </div>
                        </div>
                        <div class="col-md-4 py-3 py-md-0">
                            <h3 class="text-center">Horario de atención</h3>
                            <p class="text-center">Lunes a Domingo</p>
                            <p class="text-center">5:00 PM - 11:00 PM</p>
                        </div>
                    </div>
                </div>


                <!-- Nuevo pie de página -->
                  <div class="footer">
                    <div class="container">
                        <div class="marquee">
                            <p>© 2024 Mr. Pizza Paita. Todos los derechos reservados.</p>
                        </div>
                    </div>
                </div>
            </footer>

            <!--Pare regresar al inicio de la pagina-->
            <a href="#" class="arrow"><i><img src="https://i.postimg.cc/X7wSHkd7/pizzaup.png" alt="" width="50px"></i></a>


        </div>

        <script src="vista/js/carrito.js"></script>
    </body>
</html>