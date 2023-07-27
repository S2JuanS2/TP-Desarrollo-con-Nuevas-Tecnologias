<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Bienvenido, ${session.cliente.nombre}!</h1>
    <g:link controller="Pedido" action="crearPedido">
        <button>Crear Pedido</button>
    </g:link>
        <g:link controller="Articulo" action="mostrarArticulos">
        <button>Ver articulos</button>
    </g:link>
    <g:link controller="Cesta" action="mostrarCesta">
        <button>Ver cesta</button>
    </g:link>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
</body>
    
</html>


