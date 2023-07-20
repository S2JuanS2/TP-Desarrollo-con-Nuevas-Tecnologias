<html>
    <head>
        <title>Mostrar Clientes</title>
    </head>
    <body>

    <h1>LISTADO DE CLIENTES</h1>

    <g:each var="cliente" in="${clientes}">

    <h2>${cliente.nombre}</h2>
       
    </g:each>

    </body>
</html>