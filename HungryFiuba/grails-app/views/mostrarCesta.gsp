<!DOCTYPE html>
<html>
    <head>
        <title>Cesta de Compras</title>
        <asset:stylesheet src="mostrarArticulos.css"/>
    </head>
    <body>

    <h1>CESTA DE COMPRAS</h1>

    <div class="cesta-container">
        <h2>Cliente: ${cliente.nombre} ${cliente.apellido}</h2>
        <h3>Cantidad de Artículos: ${cesta.cantidadDeArticulos}</h3>
        <h3>Precio Total: ${cesta.precioTotal}</h3>

        <div class="articulos-container">
            <g:each var="articulo" in="${cesta.articulos}">
                <div class="articulo-container">
                    <div class="articulo-nombre">${articulo.nombre}</div>
                    <div class="articulo-precio">Precio: ${articulo.precio}</div>
                    <div class="articulo-stock">Stock disponible: ${articulo.stock}</div>
                    <img class="articulo-imagen" src="${articulo.imagenUrl}" alt="Imagen del artículo"/>
                </div>
            </g:each>
        </div>
    </div>

    </body>
</html>
