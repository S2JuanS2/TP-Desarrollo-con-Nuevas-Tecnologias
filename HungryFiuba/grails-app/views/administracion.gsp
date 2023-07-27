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
            <th>Estado de cuenta</th>
            <th>Pedido</th>
            <th>Estado del pedido</th>
            <th>Confirmar</th>
            <th>Cancelar</th>
        </tr>
        <g:each var="pedido" in="${pedidos}">
            <tr>
                <th>${pedido.cliente.nombre} ${pedido.cliente.apellido}</th>
                <th>${pedido.cliente.estado}</th>
                <th>${pedido.id}</th>
                <th>${pedido.estado}</th>
                <th><button>Confirmar</button></th>
                <th><button>Cancelar</button></th>
            </tr>
        </g:each>
    </table>
    <h1>Lista de articulos</h1>
    <table>
        <tr>
            <th>Nombre</th>
            <th>stock</th>
            <th>Precio</th>
            <th>codigo</th>
        </tr>
        <g:each var="articulo" in="${articulos}">
            <tr>
                <th>${articulo.nombre}</th>
                <th>${articulo.stock}</th>
                <th>${articulo.codigo}</th>
            </tr>
        </g:each>
    </table>

        <g:link>
            <button>Agregar Articulos</button>
        </g:link>

</html>