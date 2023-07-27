<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <asset:stylesheet src="administracion.css"/>
    
</head>
<body>
    <h1>ADMINISTRACIÓN DE PEDIDOS</h1>
</body>
    <h1>Lista de pedidos</h1>
    <table>
        <tr>
            <th>Cliente</th>
            <th>Deuda</th>
            <th>Strikes</th>
            <th>Estado de cuenta</th>
            <th>Pedido Id</th>
            <th>Precio</th>
            <th>Estado del pago</th>
            <th>Estado del pedido</th>
            <th>Confirmar</th>
            <th>Cancelar</th>
        </tr>
        <g:each var="pedido" in="${pedidos}">
            <tr>
                <th>${pedido.cliente.nombre} ${pedido.cliente.apellido}</th>
                <th>$ ${pedido.cliente.deuda}</th>
                <th> ${pedido.cliente.strikes}</th>
                <th>${pedido.cliente.estado}</th>
                <th>${pedido.id}</th>
                <th>$ ${pedido.precioTotal}</th>
                <th>${pedido.estadoPago}</th>
                <th>${pedido.estado}</th>
                <th><g:link controller="Administrador" action="confirmarPedido" params="[pedido: pedido.id]" ><button>Confirmar</button></g:link></th>
                <th><g:link controller="Administrador" action="cancelarPedido" params="[pedido: pedido.id]" ><button>Cancelar</button></g:link></th>
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
                <th><g:link><button>Aumentar stock</button></g:link></th>
                <th><g:link><button>Descontar stock</button></g:link></th>
            </tr>
        </g:each>
    </table>

        <g:link controller="Articulo" action="agregarArticuloAdministrador">
            <button>Agregar Articulo</button>
        </g:link>

        <a href="/inicio">Volver</a>

</html>