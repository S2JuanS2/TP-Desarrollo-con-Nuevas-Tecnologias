<!DOCTYPE html>
<html>
<head>
    <title>Bienvenida</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <div class="conteiner">
        <h1>Bienvenido ${session.cliente.nombre}!</h1>
        <g:link controller="Pedido" action="crearPedido">Crear o ver pedido</g:link>
        <g:link controller="Articulo" action="mostrarArticulos">Comprar</g:link>
        <g:link controller="Cesta" action="mostrarCesta">Ver cesta</g:link>
        <g:link controller="Session" action="logout">Cerrar sesión</g:link>
        <div class="estado">
            <p><u>Estado de cuenta:</u> ${session.cliente.estado}</p>
            <p><u>Penalizaciones:</u> ${session.cliente.strikes}</p>
            <g:if test="${session.cliente.deuda <= 0}"><p class="deuda-verde">Saldo: $${session.cliente.deuda} </p></g:if>
            <g:else><p class="deuda-rojo">Saldo: $${session.cliente.deuda} </p></g:else>
            <g:link controller="Cliente" action="calificacionesPendientes">Calificaciones Pendientes</g:link>
        </div>
        <asset:image src="logo.png" class="imagen" alt="Logo de la empresa"></asset:image>
    </div>
    <div>
        <div class="term-cond">
        <p class="term" >Términos y condiciones</p>
        <p>Sólo podrás crear un pedido en simultáneo, podrás cancelar el pedido sólo cuando se encuentre en estado de confirmación.</p>
        <p>El limite máximo de compra son <span style="color: green;">$5000.00</span>, no hay limite de articulos.</p>
        <p>Sólo puedes realizar pedidos dentro del horario del comedor. </p>
        <p>Si un pedido no es retirado y, en consecuencia, no se efectúa su pago, se aplicará una penalización. </p>
        <p>Al llegar a 3 puntos de penalización se te bloqueará la cuenta. Para desbloquearla deberás abonar lo adeudado.</p>
        <p>Horario del comedor:  00:00Hs a 00:00Hs.</p>
        </div>
    </div>
    <footer>
        <p>HungryFIUBA</p>
    </footer>
</body>
    
</html>


