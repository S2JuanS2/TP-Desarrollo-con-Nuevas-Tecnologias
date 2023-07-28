<!DOCTYPE html>
<html>
<head>
    <title>Usted tiene calificaciones pendientes</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Segunda pregunta:</h1>
    
     <h3>Estado del Pedido</h3>
    <g:link controller="cliente" action="segundoApectoUnaEstrella">
        <button>1</button>
    </g:link>
    <g:link controller="cliente" action="segundoApectoDosEstrellas">
        <button>2</button>
    </g:link>
    <g:link controller="cliente" action="segundoApectoTresEstrellas">
        <button>3</button>
    </g:link>
    <g:link controller="cliente" action="segundoApectoCuatroEstrellas">
        <button>4</button>
    </g:link>
    <g:link controller="cliente" action="segundoApectoCincoEstrellas">
        <button>5</button>
    </g:link>

    <a href="/inicio">Volver a Inicio</a>
</body>
</html>