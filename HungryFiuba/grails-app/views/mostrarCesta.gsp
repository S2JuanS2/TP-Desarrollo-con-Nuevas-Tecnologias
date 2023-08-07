<!-- grails-app/views/cliente/mostrarCesta.gsp -->
<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Cesta</title>
        <asset:stylesheet src="mostrarArticulos.css"/>
        <asset:stylesheet src="registroExitoso.css"/>
    </head>
    <body>

    <div class="conteiner">
        <h1>Cesta de compras de ${session.nombre} ${session.apellido}</h1>
        <g:if test="${cesta.cantidadDeArticulos == 0}">
            <h3>Cesta vacía!</h3>
            </g:if>
        <div class="articulos-container">
            <g:each var="articulo" in="${cesta.articulos}">
                <div class="articulo-container">
                    <div class="articulo-nombre">${articulo.nombre}</div>
                        <div class="articulo-precio">${articulo.descripcion}</div>
                        <img class="articulo-imagen" src="${articulo.imagenUrl}" alt="Imagen del artículo"/>
                        <div class="articulo-precio">Precio: $${articulo.precio}</div>
                        <g:link class="agregar-carrito-btn" controller="cesta" action="eliminarArticulo" params="[articulo: articulo.id]" >Eliminar de la cesta
                        </g:link>
                            <div id="customAlert" class="custom-alert">
                                <div class="custom-alert-content">
                                    <span id="customAlertMessage"></span>
                                </div>
                            </div>
                    </div>
            </g:each>
        </div>
        <g:if test="${cesta.cantidadDeArticulos > 0}" >
            <div class="cantidad-articulos">${cesta.cantidadDeArticulos} articulo(s) en la cesta.</div>
                <h2 class="monto-total">Monto TOTAL: $${cesta.montoTotal}.00</h2>
        </g:if>
        <g:link controller="Session" action="logout">Cerrar sesión</g:link>
        <a href="/inicio">Volver</a>
    </div>


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