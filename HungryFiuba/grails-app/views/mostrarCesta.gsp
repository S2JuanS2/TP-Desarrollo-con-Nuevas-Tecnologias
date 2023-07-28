<!-- grails-app/views/cliente/mostrarCesta.gsp -->
<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Cesta</title>
        <asset:stylesheet src="mostrarArticulos.css"/>
        <asset:stylesheet src="registroExitoso.css"/>
    </head>
    <body>

    <h1>Cesta de compras de ${session.cliente.nombre} ${session.cliente.apellido}</h1>
                <h3>Articulos en cesta</h3>
                <div class="articulos-container">
                <g:each var="articulo" in="${cesta.articulos}">
                    <div class="articulo-container">
                        <div class="articulo-nombre">${articulo.nombre}</div>
                        <img class="articulo-imagen" src="${articulo.imagenUrl}" alt="Imagen del artículo"/>
                        <div class="articulo-precio">Precio: $${articulo.precio}</div>
                        <g:link controller="cesta" action="eliminarArticulo" params="[articulo: articulo.id]" > 
                            <button class="agregar-carrito-btn">Eliminar de la cesta</button>
                            <div id="customAlert" class="custom-alert">
                                <div class="custom-alert-content">
                                <span id="customAlertMessage"></span>
                                </div>
                            </div>
                        </g:link>
                    </div>
                </g:each>
                </div>
                <div class="cantidad-articulos">Cantidad de articulos: ${cesta.cantidadDeArticulos}</div>
                <h2 class="monto-total">Monto TOTAL: $${cesta.montoTotal} </h2>
    <g:link controller="Administrador" action="logout">Cerrar sesión</g:link>
    <a href="/inicio">Volver</a>


    <script>
        var botonesArticulo = document.querySelectorAll('.agregar-carrito-btn');
        botonesArticulo.forEach(function(boton) {
            boton.addEventListener('click', function() {
                    showCustomAlert("Articulo eliminado con éxito")
            });
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