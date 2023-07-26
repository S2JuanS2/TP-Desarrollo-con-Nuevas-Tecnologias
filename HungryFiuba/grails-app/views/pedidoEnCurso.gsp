<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Pedido en curso</h1>
    <g:each var="pedido" in="${pedidos}">
    <h2>Detalles: N°${pedido.id}</h2>
        <p>Precio total: $${pedido.precioTotal} </p>
        <p>Estado del pedido: ${pedido.estado} </p>
        <h2>Lista de articulos</h2>
        <g:each var="articulo" in="${pedido.cesta.articulos}">
        <p>${articulo.nombre}.......................................$${articulo.precio}</p>
        </g:each>
    </g:each>
    <a href="/inicio">Cancelar Pedido</a>
    <a href="/inicio">Volver a Inicio</a>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
</body>
</html>