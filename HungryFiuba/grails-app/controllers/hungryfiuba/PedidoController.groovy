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
    def cestaVacia(){
        render(view: "/cestaVacia" )
    }
    def crearPedido(){

        def cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        Cesta cesta = cliente.cesta
        if(cesta.cantidadDeArticulos>0){
            pedidoService.guardarPedido(cliente.id)
            redirect(action: "pedidoCreado")
        }else{
            render(view:"/cestaVacia")
        }
        

    }
    
}
