package hungryfiuba

class ArticuloController {

    static scaffold = Articulo

    def mostrarArticulos(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        def listaPedidos = Pedido.list()
        boolean clienteExisteEnPedidos = listaPedidos.any { pedido -> pedido.cliente == cliente }

        if (session.cliente) {
            if(clienteExisteEnPedidos){
                render(view: "/pedidoEnCurso")
            }else{
                def articulos = Articulo.list()
                render(view: '/mostrarArticulos', model: [articulos: articulos])
            }
            
        } else {
            render(view: "/registroFallido")
        }
    }

}
