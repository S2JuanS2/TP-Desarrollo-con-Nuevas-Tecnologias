<!DOCTYPE html>
<html>
<head>
    <title>Usted tiene calificaciones pendientes</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Numero de calificaciones pendientes: ${cliente.calificacionesPendientes} </h1>
    <g:link controller="cliente" action="calificar">Calificar</g:link>
    <a href="/inicio">Volver a Inicio</a>
</body>
</html>