<!DOCTYPE html>
<html>
<head>
    <title>Cuenta Bloqueada</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Cuenta bloqueada</h1>
    <p>Usted tiene una deuda pendiente de: ${cliente.deuda}<br></p>
    <p>Por favor, abone su deuda para desbloquear la cuenta.</p>
    <a href="/inicio">Volver a Inicio</a>
    <g:link controller="Pedido" action="pagarDeuda">
        <button>PagarDeuda</button>
    </g:link>
</body>
</html>