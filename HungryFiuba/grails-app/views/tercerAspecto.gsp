<!DOCTYPE html>
<html>
<head>
    <title>Usted tiene calificaciones pendientes</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Tercera pregunta:</h1>
    
      <h3>Atencion al cliente</h3>
    <g:link controller="cliente" action="tercerApectoUnaEstrella">
        <button>1</button>
    </g:link>
    <g:link controller="cliente" action="tercerApectoDosEstrellas">
        <button>2</button>
    </g:link>
    <g:link controller="cliente" action="tercerApectoTresEstrellas">
        <button>3</button>
    </g:link>
    <g:link controller="cliente" action="tercerApectoCuatroEstrellas">
        <button>4</button>
    </g:link>
    <g:link controller="cliente" action="tercerApectoCincoEstrellas">
        <button>5</button>
    </g:link>

    <a href="/inicio">Volver a Inicio</a>
</body>
</html>