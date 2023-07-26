<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Pedido Creado con Exito</h1>
    <g:each var="pedido" in="${pedidos}">
    <h2>Detalles: N°${pedido.id}</h2>
        <p>Precio total: $${pedido.precioTotal} </p>
        <p>Estado del pedido: ------- </p>
        <h2>Lista de articulos</h2>
        <g:each var="articulo" in="${pedido.cesta.articulos}">
        <p>articulo: ${articulo.nombre}</p>
        <p>precio unidad: $${articulo.precio}</p>
        </g:each>
    </g:each>
    <a href="/inicio">Cancelar Pedido</a>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
</body>
</html>