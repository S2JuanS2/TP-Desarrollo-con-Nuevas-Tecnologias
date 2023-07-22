<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Articulos</title>
    </head>
    <body>

    <h1>LISTADO DE ARTICULOS</h1>

    <g:each var="articulo" in="${articulos}">

    <h2>nombre: ${articulo.nombre}</h2>
    <h2>precio: ${articulo.precio}</h2>
       
    </g:each>

    </body>
</html>