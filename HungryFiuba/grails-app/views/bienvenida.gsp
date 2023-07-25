<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Bienvenido, ${session.cliente.nombre}!</h1>
    <a href="/pedidoCreado">Crear Pedido</a>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
</body>
    
</html>


