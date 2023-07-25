package hungryfiuba

class PedidoController {

    static scaffold = Pedido

    def pedidoService

    def comenzarPedido(){

        def cliente = session.cliente

        pedidoService.guardarPedido(cliente.id)

        render(view: "/bienvenida")
    }

    def pedidoCreado(){
        render(view: "/pedidoCreado")
    }
    
}
