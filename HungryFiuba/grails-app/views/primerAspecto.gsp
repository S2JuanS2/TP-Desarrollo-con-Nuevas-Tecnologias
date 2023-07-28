<!DOCTYPE html>
<html>
<head>
    <title>Usted tiene calificaciones pendientes</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Primera pregunta:</h1>
    
    <h3>Rapidez del Pedido</h3>
    <g:link controller="cliente" action="primerApectoUnaEstrella">
        <button>1</button>
    </g:link>
    <g:link controller="cliente" action="primerApectoDosEstrellas">
        <button>2</button>
    </g:link>
    <g:link controller="cliente" action="primerApectoTresEstrellas">
        <button>3</button>
    </g:link>
    <g:link controller="cliente" action="primerApectoCuatroEstrellas">
        <button>4</button>
    </g:link>
    <g:link controller="cliente" action="primerApectoCincoEstrellas">
        <button>5</button>
    </g:link>

    <a href="/inicio">Volver a Inicio</a>
</body>
</html>