<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Articulos</title>
        <asset:stylesheet src="mostrarArticulos.css"/>
    </head>
    <body>

    <h1>LISTADO DE ARTICULOS</h1>

    <div class="articulos-container">
        <g:each var="articulo" in="${articulos}">
            <div class="articulo-container">
                <div class="articulo-nombre">${articulo.nombre}</div>
                <div class="articulo-precio">Precio: ${articulo.precio}</div>
                <div class="articulo-stock">Stock disponible: ${articulo.stock}</div>
                <img class="articulo-imagen" src="${articulo.imagenUrl}" alt="Imagen del artículo"/>
            </div>
        </g:each>
    </div>

    </body>
</html>
