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
    <h3>Cantidad de Artículos: </h3>
    <div class="articulos-container">
        <g:each var="articulo" in="${pedido.cantidadDeArticulos}">
           
        </g:each>
    </div>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
</body>
</html>
