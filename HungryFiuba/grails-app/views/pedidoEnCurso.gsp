<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Pedido en curso</h1>
    <h3>Detalles del pedido</h3>
    <p>Cliente: ${session.cliente.nombre} ${session.cliente.apellido}</p>
    <p>Hora de realizacion: ${pedido.momentoDeCreacion.getHour()}:${pedido.momentoDeCreacion.getMinute()} </p>
    <p>Estado del pedido: ${pedido.estado} </p>
    <h3>Lista de articulos</h3>
    <g:each var="articulo" in="${pedido.cesta.articulos}">
        <p>${articulo.nombre}.......................................$${articulo.precio}</p>
    </g:each>
    <h3>Precio total: $${pedido.precioTotal} </h3>
    <g:link controller="pedido" action="eliminarPedido">
        <button>Cancelar pedido</button>
    </g:link>
    <a href="/inicio">Volver a Inicio</a>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
</body>
</html>