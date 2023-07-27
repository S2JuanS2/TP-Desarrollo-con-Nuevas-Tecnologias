<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Articulos</title>
        <asset:stylesheet src="mostrarArticulos.css"/>
        <asset:stylesheet src="registroExitoso.css"/>
    </head>
    <body>

    <h1>LISTADO DE ARTICULOS</h1>
    <div class="articulos-container">
        <g:each var="articulo" in="${articulos}">
            <div class="articulo-container">
                <div class="articulo-nombre">${articulo.nombre}</div>
                <div class="articulo-precio">Precio: ${articulo.precio}</div>
                <div class="articulo-stock">Stock disponible: ${articulo.stock}</div>
                <img class="articulo-imagen" src="${articulo.imagenUrl}" alt="Imagen del artículo"/>
                <g:link controller="cesta" action="agregarArticulo" params="[articulo: articulo.id]" > 
                    <button class="agregar-carrito-btn" data-articulo-id="${articulo.id}" data-stock="${articulo.stock}">Agregar al carrito</button>
                </g:link>
            </div>
        </g:each>
    </div>
    <g:link controller="Administrador" action="logout">
        <button>Logout</button>
    </g:link>
    <a href="/inicio">Volver</a>

    <script>
        var botonesArticulo = document.querySelectorAll('.agregar-carrito-btn');
        botonesArticulo.forEach(function(boton) {
            var articuloId = boton.dataset.articuloId;
            var articuloStock = boton.dataset.stock;
            boton.addEventListener('click', function() {
                if(articuloStock > 0){
                    alert('¡Articulo añadido!');
                }else{
                    alert('¡No hay mas stock!');
                }
            });
        });
    </script>

    </body>
</html>
