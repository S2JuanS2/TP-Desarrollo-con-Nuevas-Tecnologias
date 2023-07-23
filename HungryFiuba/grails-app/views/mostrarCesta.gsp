<!-- grails-app/views/cliente/mostrarCesta.gsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Cesta de Compras</title>
    <asset:stylesheet src="mostrarCesta.css"/>
</head>
<body>
    <h1>CESTA DE COMPRAS</h1>
    <h2>Cliente: ${cliente.nombre} ${cliente.apellido}</h2>
    <h3>Cantidad de Artículos: ${cliente.cesta?.cantidadDeArticulos ?: 0}</h3>

    <div class="articulos-container">
        <g:each var="articulo" in="${cliente.cesta?.articulos ?: []}">
            <div class="articulo">
                <h2>${articulo.nombre}</h2>
                <img src="${articulo.imagenUrl}" alt="${articulo.nombre}">
                <p>Precio: ${articulo.precio}</p>
                <p>Stock: ${articulo.stock}</p>
            </div>
        </g:each>
    </div>
</body>
</html>
