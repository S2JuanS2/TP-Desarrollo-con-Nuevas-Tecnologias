<!DOCTYPE html>
<html>
<head>
    <title>Pedido en curso</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Pedido en curso</h1>
    <h3>Detalles del pedido</h3>
    <p>Cliente: ${session.cliente.nombre} ${session.cliente.apellido}</p>
    <p>Hora de realizacion: ${pedido.momentoDeCreacion.getHour()}:${pedido.momentoDeCreacion.getMinute()} </p>
    <p><strong>Estado del pedido:</strong> <em>${pedido.estado}</em> </p>
    <p><strong>Estado del pago:</strong> <em>${pedido.estadoPago}</em></p>
    <h3>Lista de articulos</h3>
    <g:each var="articulo" in="${pedido.cesta.articulos}">
        <p>${articulo.nombre}.......................................$${articulo.precio}</p>
    </g:each>
    <h3>Precio total: $${pedido.precioTotal} </h3>
    <g:link controller="pedido" action="cancelarPedido">Cancelar pedido</g:link>
    <g:link controller="pedido" action="pagarPedido">Pagar pedido</g:link>
    <a href="/inicio">Volver a Inicio</a>
    <g:link controller="Administrador" action="logout">Cerrar sesión</g:link>
    <footer>
        <p>HungryFIUBA</p>
    </footer>
</body>
</html>