<!-- grails-app/views/cliente/mostrarCesta.gsp -->
<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Articulos</title>
        <asset:stylesheet src="mostrarArticulos.css"/>
        <asset:stylesheet src="registroExitoso.css"/>
    </head>
    <body>

    <h1>Cesta de compras</h1>
    <h2>Cliente: ${session.cliente.nombre} ${session.cliente.apellido}</h2>
                <div class="articulo-stock">Cantidad de articulos: ${cesta.cantidadDeArticulos}</div>
                <div class="articulos-container">
                <g:each var="articulo" in="${cesta.articulos}">
                    <div class="articulo-container">
                        <div class="articulo-nombre">${articulo.nombre}</div>
                        <div class="articulo-precio">Precio: $${articulo.precio}</div>
                        <img class="articulo-imagen" src="${articulo.imagenUrl}" alt="Imagen del artículo"/>
                    </div>
                </g:each>
                </div>
                <h2 class="articulo-precio">Monto TOTAL: $${cesta.montoTotal} </h2>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
    <a href="/inicio">Volver</a>
    </body>
</html>