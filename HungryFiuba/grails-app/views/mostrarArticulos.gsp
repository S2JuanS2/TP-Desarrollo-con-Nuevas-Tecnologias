<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Articulos</title>
        <asset:stylesheet src="mostrarArticulos.css"/>
    </head>
    <body>

    <h1>LISTADO DE ARTICULOS</h1>
    <h2>Bienvenido, ${session.cliente.nombre}!</h2>
    <div class="articulos-container">
        <g:each var="articulo" in="${articulos}">
            <div class="articulo-container">
                <div class="articulo-nombre">${articulo.nombre}</div>
                <div class="articulo-precio">Precio: ${articulo.precio}</div>
                <div class="articulo-stock">Stock disponible: ${articulo.stock}</div>
                <img class="articulo-imagen" src="${articulo.imagenUrl}" alt="Imagen del artículo"/>
                <g:link> 
                    <button class="agregar-carrito-btn">Agregar al carrito</button>
                </g:link>
            </div>
        </g:each>
    </div>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
    </body>
</html>
