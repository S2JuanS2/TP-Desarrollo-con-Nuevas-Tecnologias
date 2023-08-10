<!DOCTYPE html>
<html>
<head>
    <title>Cuenta Bloqueada</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <div class="conteiner">
        <h1>Cuenta bloqueada</h1>
        <p>${cliente.nombre} usted tiene una deuda de $${cliente.deuda}</p>
        <p>Por favor, abone su deuda para desbloquear la cuenta.</p>
        <g:link controller="Cliente" action="pagarDeuda">Desbloquear cuenta</g:link>
        <a href="/inicio">Volver a Inicio</a>
    </div>
    <footer>
        <p>HungryFIUBA</p>
    </footer>
</body>
</html>