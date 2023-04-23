<html>
    <head>
        <title>titulo de la pagina</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellido</th>
                    <th scope="col">Apellido</th>
                    <th scope="col">Apellido</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${clientes}" var="cliente">
                    <tr class="table-primary">
                        <td>${cliente.id}</td>
                        <td>${cliente.nombre}</td>
                        <td>${cliente.apellido}</td>
                        <td>${cliente.identificadorTipo}</td>
                        <td>${cliente.identificadorValor}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </body>
</html>