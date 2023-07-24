<!-- grails-app/views/cliente/mostrarCesta.gsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Cesta de Compras</title>
    <asset:stylesheet src="mostrarCesta.css"/>
</head>
<body>
    <h1>CESTA DE COMPRAS</h1>
    <h2>Cliente: ${session.cliente.nombre} ${session.cliente.apellido}</h2>
    <h3>Cantidad de Artículos: ${session.cliente.cesta?.cantidadDeArticulos ?: 0}</h3>

    <div class="articulos-container">
        <g:each var="articulo" in="${session.cliente.cesta?.articulos ?: []}">
            <div class="articulo">
                <h2>${articulo.nombre}</h2>
                <img src="${articulo.imagenUrl}" alt="${articulo.nombre}">
                <p>Precio: ${articulo.precio}</p>
                <p>Stock: ${articulo.stock}</p>
            </div>
        </g:each>
    </div>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
</body>
</html>
