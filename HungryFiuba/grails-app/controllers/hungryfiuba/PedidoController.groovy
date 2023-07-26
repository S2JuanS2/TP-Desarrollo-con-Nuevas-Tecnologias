package hungryfiuba
import java.time.LocalDateTime

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

        def listaPedidos = Pedido.list()
        boolean clienteExisteEnPedidos = listaPedidos.any { pedido -> pedido.cliente == cliente }
        LocalDateTime hora = LocalDateTime.now()
        if(cesta.cantidadDeArticulos>0){
            if(!clienteExisteEnPedidos ){
                if((hora.getHour()) >= 20){
                    render(view:"/comedorCerrado")
                }else{
                    pedidoService.guardarPedido(cliente.id)
                    redirect(action: "pedidoCreado")
                }
            }else{
                render(view: "/pedidoEnCurso", model:[pedidos: listaPedidos])
            }
        }else{
            render(view:"/cestaVacia")
        }
        

    }
    
}
