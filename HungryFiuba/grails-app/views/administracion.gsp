<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <asset:stylesheet src="administracion.css"/>
    
</head>
    <body>
        <h1>ADMINISTRACIÓN DE PEDIDOS</h1>
        <h1>Lista de pedidos</h1>
        <table>
            <tr>
                <th>PEDIDO N°</th>
                <th>CLIENTE</th>
                <th>ESTADO DE CUENTA</th>
                <th>STRIKES</th>
                <th>DEUDA</th>
                <th>PRECIO</th>
                <th>ESTADO DEL PAGO</th>
                <th>ESTADO DEL PEDIDO</th>
                <th>ACCIÓN</th>
                <th>ACCIÓN</th>
            </tr>
            <g:each var="pedido" in="${pedidos}">
                <tr  onmouseover="showTable()" onmouseout="hideTable()">
                    <th>${pedido.id}</th>
                    <th>${pedido.cliente.nombre} ${pedido.cliente.apellido}</th>
                    <th>${pedido.cliente.estado}</th>
                    <th> ${pedido.cliente.strikes}</th>
                    <th>$ ${pedido.cliente.deuda}</th>
                    <th>$ ${pedido.precioTotal}</th>
                    <th>${pedido.estadoPago}</th>
                    <th>${pedido.estado}</th>
                    <th><g:link controller="administrador" action="confirmarPedido" params="[pedido: pedido.id]" >Estado siguiente</g:link></th>
                    <th><g:link controller="administrador" action="cancelarPedido" params="[pedido: pedido.id]" >Remover</g:link></th>
                </tr>
            </g:each>

        </table>
        <table class="sub-table" id="sub-table">
            <g:each var="pedido" in="${pedidos}">
                <tr>
                    <th>PEDIDO N°</th>
                    <th>Cant. Art</th>
                    <g:each var="articuloPedido" in="${pedido.cesta.articulos}">
                        <th>Articulo</th>
                    </g:each>
                <tr>
                    <th>${pedido.id}</th>
                    <th>${pedido.cantidadDeArticulos}</th>
                    <g:each var="articuloPedido" in="${pedido.cesta.articulos}">
                        <th class="celda" >${articuloPedido.nombre}</th>
                    </g:each>
                </tr>
            </g:each>
        </table>

        <h1>Lista de articulos</h1>
        <table>
            <tr>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>PRECIO</th>
                <th>CÓDIGO</th>
                <th>STOCK</th>
                <th>ACCIÓN</th>
                <th>ACCIÓN</th>
            </tr>
            <g:each var="articulo" in="${articulos}">
                <tr>
                    <th>${articulo.nombre}</th>
                    <th>${articulo.descripcion}</th>
                    <th>$ ${articulo.precio}</th>
                    <th>${articulo.codigo}</th>
                    <th>${articulo.stock}</th>
                    <th><g:link controller="Articulo" action="aumentarStock" params="[articulo: articulo.id]" >+ stock</g:link></th>
                    <th><g:link controller="Articulo" action="reducirStock" params="[articulo: articulo.id]" >- stock</g:link></th>
                </tr>
            </g:each>
        </table>

        <div class="agregar-articulos">
            <h2>Agregar Articulos</h2>
            <g:form id="hola" controller="Articulo" action="agregarArticuloAdministrador" method="POST">

                <input type="text" name="nombre" pattern="[A-Za-z ]+" required minlength="1" maxlength="50" placeholder="Nombre de articulo" required/>
                <input type="number" name="stock" placeholder="Cantidad de stock" required/>
                <input type="number" name="precio" required min="1" max="200000" placeholder="Precio" required />
                <input type="number" name="codigo" placeholder="Código" required />
                <input type="text" name="descripcion" placeholder="Descripción" required />
                <input type="text" name="imagenUrl" required minlength="1" maxlength="5000" placeholder="Nombre de imagen" required/>
                    
                <button type="submit" class="agregar">Agregar articulo</button>
            </g:form>
        </div>

        <h2>Estadísticas</h2>
        <p>Calif. página: ${admin.mostrarCalificacionPagina()} estrellas.</p>
        <p>Calif. rapidéz: ${admin.mostrarCalificacionRapidez()} estrellas.</p>
        <p>Calif. pedidos: ${admin.mostrarCalificacionEstado()} estrellas.</p>
        <p>Cantidad de calificaciones: ${admin.cantidadCalificaciones}.</p>

        <a href="/inicio" class="volver" >Volver al inicio</a>

        <script>
            function showTable(){
                var table = document.getElementById("sub-table");
                table.style.display = "inline-block";
            }

            function hideTable(){
                var table = document.getElementById("sub-table");
                table.style.display = "none";
            }
        </script>
    </body>
</html>