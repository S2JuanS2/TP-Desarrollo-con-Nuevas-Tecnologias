<!DOCTYPE html>
<html>
<head>
    <title>Bienvenida</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <div class="conteiner">
        <h1>Bienvenido, ${session.cliente.nombre}!</h1>
        <g:link controller="Pedido" action="crearPedido">Crear o ver pedido</g:link>
        <g:link controller="Articulo" action="mostrarArticulos">Ver articulos</g:link>
        <g:link controller="Cesta" action="mostrarCesta">Ver cesta</g:link>
        <g:link controller="Administrador" action="logout">Cerrar sesión</g:link>
    <div class="estado">
            <p>Estado de cuenta: ${session.cliente.estado}</p>
            <p>Deuda: $${session.cliente.deuda} </p>
        </div>
        <asset:image src="logo.png" class="imagen" alt="Logo de la empresa"></asset:image>
    </div>
    <div>
        <p class="term" >Términos y condiciones</p>
        <p>Sólo podrás crear un pedido en simultáneo, podrás cancelar el pedido sólo cuando se encuentre en estado de confirmación.</p>
        <p>El limite máximo de compra son $5000, no hay limite de articulos.</p>
    </div>

    <footer>
        <p>HungryFIUBA</p>
    </footer>
</body>
    
</html>


