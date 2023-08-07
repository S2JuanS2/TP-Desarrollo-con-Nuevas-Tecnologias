<!DOCTYPE html>
<html>
<head>
    <title>Pedido creado</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <div class="conteiner">
        <h1>Pedido creado con exito</h1>
        <h3>Detalles del pedido</h3>
        <p>Cliente: ${session.nombre} ${session.apellido}</p>
        <p>Hora de realizacion: ${pedido.momentoDeCreacion.getHour()}:${pedido.momentoDeCreacion.getMinute()} </p>
        <p><strong>Estado del pedido:</strong> <em>${pedido.estado}</em> </p>
        <p><strong>Estado del pago:</strong> <em>${pedido.estadoPago}</em></p>
        <h3>Lista de articulos</h3>
        <g:each var="articulo" in="${pedido.cesta.articulos}">
            <p>${articulo.nombre}.......................................$${articulo.precio}</p>
        </g:each>
        <h3>Precio total: $${pedido.precioTotal} </h3>
        <g:link class="cancelarPedido" controller="pedido" action="cancelarPedido"  href="#" onclick="return confirmarOperacion(this)" data-pedido="${pedido.estado}">Cancelar pedido</g:link>
        <g:link controller="pedido" action="pagarPedido">Pagar pedido</g:link>
        <a href="/inicio">Volver a Inicio</a>
        <g:link controller="Session" action="logout">Cerrar sesión</g:link>
    </div>
    <footer>
        <p>HungryFIUBA</p>
    </footer>

        <script>

        function confirmarOperacion(elemento){
            var pedidoEstado = elemento.getAttribute("data-pedido") || "${pedido.estado}";
            console.log(pedidoEstado)
            if(pedidoEstado != "A confirmar"){
                var opcion = confirm("No puedes cancelar el pedido, el pedido se encuentra " + pedidoEstado);
                }else{
                    var opcion = confirm("¿Seguro que quieres cancelar el pedido?");
                }
            return opcion;
        }
    </script>

</body>
</html>