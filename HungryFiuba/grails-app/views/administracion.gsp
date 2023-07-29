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
                <th>Cliente</th>
                <th>Deuda</th>
                <th>Strikes</th>
                <th>Estado de cuenta</th>
                <th>Pedido N°</th>
                <th>Precio</th>
                <th>Estado del pago</th>
                <th>Estado del pedido</th>
                <th>Acción</th>
                <th>Acción</th>
                <th>Calif.1</th>
                <th>Calif.2</th>
                <th>Calif.3</th>
            </tr>
            <g:each var="pedido" in="${pedidos}">
                <tr  onmouseover="showTable()" onmouseout="hideTable()">
                    <th>${pedido.cliente.nombre} ${pedido.cliente.apellido}</th>
                    <th>$ ${pedido.cliente.deuda}</th>
                    <th> ${pedido.cliente.strikes}</th>
                    <th>${pedido.cliente.estado}</th>
                    <th>${pedido.id}</th>
                    <th>$ ${pedido.precioTotal}</th>
                    <th>${pedido.estadoPago}</th>
                    <th>${pedido.estado}</th>
                    <th><g:link controller="administrador" action="confirmarPedido" params="[pedido: pedido.id]" >Estado siguiente</g:link></th>
                    <th><g:link controller="administrador" action="cancelarPedido" params="[pedido: pedido.id]" >Cancelar</g:link></th>
                    <th>${pedido.cliente.aspectoUnoSuma}</th>
                    <th>${pedido.cliente.aspectoDosSuma}</th>
                    <th>${pedido.cliente.aspectoTresSuma}</th>
                </tr>
            </g:each>

        </table>
        <table class="sub-table" id="sub-table">
            <g:each var="pedido" in="${pedidos}">
                <tr>
                    <th>Pedido N°</th>
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
                <th>Nombre</th>
                <th>Precio</th>
                <th>stock</th>
                <th>codigo</th>
                <th>Acción</th>
                <th>Acción</th>
            </tr>
            <g:each var="articulo" in="${articulos}">
                <tr>
                    <th>${articulo.nombre}</th>
                    <th>$ ${articulo.precio}</th>
                    <th>${articulo.stock}</th>
                    <th>${articulo.codigo}</th>
                    <th><g:link controller="Articulo" action="aumentarStock" params="[articulo: articulo.id]" >Aumentar stock</g:link></th>
                    <th><g:link controller="Articulo" action="reducirStock" params="[articulo: articulo.id]" >Descontar stock</g:link></th>
                </tr>
            </g:each>
        </table>

        <div class="agregar-articulos">
            <h2>Agregar Articulos</h2>
            <g:form id="hola" controller="Articulo" action="agregarArticuloAdministrador" method="POST">

                <input type="text" name="nombre" pattern="[A-Za-z ]+" required minlength="1" maxlength="50" placeholder="Nombre de articulo"/>
                <input type="number" name="stock" placeholder="Cantidad de stock"/>
                <input type="number" name="precio" required min="1" max="200000" placeholder="Precio">
                <input type="number" name="codigo" placeholder="Código">
                <input type="text" name="imagenUrl" required minlength="1" maxlength="5000" placeholder="Nombre de imagen"/>
                    
                <button type="submit" class="agregar">Agregar articulo</button>
            </g:form>
        </div>

        <h2>Estadísticas</h2>
        <p>[${admin.nombre}] Cantidad de calificaciones: ${admin.cantidadCalificaciones}.</p>

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