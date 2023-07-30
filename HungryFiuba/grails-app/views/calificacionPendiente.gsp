<!DOCTYPE html>
<html>
<head>
    <title>Calificacion</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <div class="conteiner">
        <h1>${cliente.nombre} tiene(s) ${cliente.calificacionesPendientes} calificación(es) pendiente(s)</h1>
        <g:link controller="cliente" action="calificar">Calificar</g:link>
        <a href="/inicio">Volver a Inicio</a>
    </div>
</body>
</html>