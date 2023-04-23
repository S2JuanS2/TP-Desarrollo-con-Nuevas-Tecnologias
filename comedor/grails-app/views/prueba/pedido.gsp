<html>
    <head>
        <title>titulo de la pagina</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <g:form action="crearPedido">
            <p>
                cliente: 
                <g:select
                    noSelection="['': 'elegí uno papá']"
                    optionKey="id"
                    optionValue="${{ cliente -> cliente.nombre + " " + cliente.apellido }}"
                    name="clienteId"
                    from="${clientes}" />
            </p>
            
            <p>
                articulo: 
                <g:select
                    noSelection="['': 'elegí uno papá']"
                    optionKey="id"
                    optionValue="${{ articulo -> articulo.nombre + " \$" + articulo.precio }}"
                    name="articuloId"
                    from="${articulos}" />
            </p>
            
            <p>
                cantidad:        
                <input type="number" name="cantidad" min="1" max="100">
            </p>

            <button type="submit">enviar</button>
        </g:form>

    </body>
</html>