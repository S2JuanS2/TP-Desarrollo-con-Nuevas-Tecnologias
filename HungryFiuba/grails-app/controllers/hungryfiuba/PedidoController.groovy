package hungryfiuba

class PedidoController {

    static scaffold = Pedido

    def pedidoService

    def comenzarPedido(){

        render(view: "/bienvenida")
    }

    def pedidoCreado(){

        def pedidos = Pedido.list()
        render(view: "/pedidoCreado", model: [pedidos: pedidos])
    }

    def crearPedido(){

        def cliente = session.cliente

        pedidoService.guardarPedido(cliente.id)

        redirect(action: "pedidoCreado")

    }
    
}
