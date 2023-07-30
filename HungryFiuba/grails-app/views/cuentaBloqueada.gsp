<!DOCTYPE html>
<html>
<head>
    <title>Cuenta Bloqueada</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <div class="conteiner">
        <h1>Cuenta bloqueada</h1>
        <p>Usted tiene una deuda pendiente de: $${cliente.deuda}.00<br></p>
        <p>Por favor, abone su deuda para desbloquear la cuenta.</p>
        <g:link controller="Pedido" action="pagarDeuda">PagarDeuda</g:link>
        <a href="/inicio">Volver a Inicio</a>
    </div>
    <footer>
        <p>HungryFIUBA</p>
    </footer>
</body>
</html>