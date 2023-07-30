<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Articulos</title>
        <asset:stylesheet src="mostrarArticulos.css"/>
        <asset:stylesheet src="registroExitoso.css"/>
    </head>
    <body>
    <div class="conteiner">
        <h1>Articulos en stock</h1>
        <div class="articulos-container">
                <g:if test="${!articulos}"><h3>No hay articulos en stock!</h3></g:if>
            <g:each var="articulo" in="${articulos}">
                <div class="articulo-container">
                    <div class="articulo-nombre">${articulo.nombre}</div>
                    <div class="articulo-precio">${articulo.descripcion}</div>
                    <img class="articulo-imagen" src="${articulo.imagenUrl}" alt="Imagen del artículo"/>
                    <div class="articulo-precio">Precio: $${articulo.precio}</div>
                    <div class="articulo-stock">Stock disponible: ${articulo.stock}</div>
                    <g:link class="agregar-carrito-btn" data-articulo-id="${articulo.id}" data-stock="${articulo.stock}" 
                        controller="cesta" action="agregarArticulo" params="[articulo: articulo.id]">Agregar a la cesta
                    </g:link>
                        <div id="customAlert" class="custom-alert">
                            <div class="custom-alert-content">
                                <span id="customAlertMessage"></span>
                            </div>
                        </div>
                </div>
            </g:each>
        </div>
        <g:link controller="Administrador" action="logout">Cerrar sesión</g:link>
        <a href="/inicio">Volver</a>
    </div>

    <script>
        var botonesArticulo = document.querySelectorAll('.agregar-carrito-btn');
        botonesArticulo.forEach(function(boton) {
            var articuloId = boton.dataset.articuloId;
            var articuloStock = boton.dataset.stock;
            boton.addEventListener('click', function() {
                if(articuloStock > 0){
                    showCustomAlert("Articulo agregado con éxito")
                }else{
                    boton.style.display = "none"
                    showCustomAlert("¡No hay más stock!")
                }
            });
        });

        var botonesArticulo = document.querySelectorAll('.agregar-carrito-btn');
        botonesArticulo.forEach(function(boton) {
            var articuloId = boton.dataset.articuloId;
            var articuloStock = boton.dataset.stock;
            if(articuloStock > 0){
                boton.style.display = "inline-block";
            }else{
                boton.textContent = "Agotado";
                boton.style.backgroundColor = "red";
                boton.style.cursor = 'not-allowed';

            };
        });

        function showCustomAlert(mensaje){
            const customAlert = document.getElementById('customAlert');
            customAlert.style.display = 'block';

            const message = mensaje;
            const customAlertMessage = document.getElementById('customAlertMessage')
            customAlertMessage.innerText = message;
        }

    </script>

    <footer>
        <p>HungryFIUBA</p>
    </footer>
    </body>
</html>
