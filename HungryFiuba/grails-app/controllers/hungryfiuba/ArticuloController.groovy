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
                def pedido = Pedido.findByCliente(cliente)
                render(view: "/pedidoEnCurso", model: [pedido: pedido])
            }else{
                def articulos = Articulo.list()
                render(view: '/mostrarArticulos', model: [articulos: articulos])
            }
            
        } else {
            render(view: "/registroFallido")
        }
    }

    //HARDOCDEADO PARA TEST
    def agregarArticuloAdministrador(){

        def articulo = new Articulo("Pepsi", 320, 72, 10, "https://tusuper.com.ar/image/cache/catalog/P2020/Bebidas/Pepsi%201,5l-800x800.jpg")

        articulo.save()

        redirect(controller: "administrador", action: "vistaAdministrador")

    }   

}
