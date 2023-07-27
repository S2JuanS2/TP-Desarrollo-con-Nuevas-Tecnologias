<!DOCTYPE html>
<html>
<head>
    <title>Cuenta Bloqueada</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Cuenta bloqueada</h1>
    <p>Usted tiene una deuda pendiente de: ${session.cliente.deuda}<br></p>
    <p>Por favor, abone su deuda para desbloquear la cuenta.</p>
    <a href="/inicio">Volver a Inicio</a>
    <a href="/pagarDeuda">Pagar deuda</a>
</body>
</html>