package hungryfiuba
import java.time.LocalDateTime

class PedidoController {

    static scaffold = Pedido

    def pedidoService
    def cestaService

    //muestra la vista de bienvenida 
    def comenzarPedido(){
        render(view: "/bienvenida")
    }

    //muestra la vista /pedidoCreado que contiene información sobre un pedido recién creado por el cliente. Después de realizar un pedido, el cliente es redirigido a esta vista para mostrar los detalles del pedido y proporcionar información sobre el estado del pedido.
    def pedidoCreado(){
        Cliente cliente = session.cliente
        def pedido = Pedido.findByCliente(cliente)
        render(view: "/pedidoCreado", model: [pedido: pedido])
    }

    //muestra la vista de cesta vacia
    def cestaVacia(){
        render(view: "/cestaVacia" )
    }

    //
    def crearPedido(){
        def cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        Cesta cesta = cliente.cesta

        def listaPedidos = Pedido.list()
        boolean clienteExisteEnPedidos = listaPedidos.any { pedido -> pedido.cliente == cliente }
        LocalDateTime hora = LocalDateTime.now()
        if(cliente.estado != EstadoCuenta.BLOQUEADA){ 
            if(cesta.cantidadDeArticulos>0){
                if(!clienteExisteEnPedidos ){
                   if((hora.getHour() >= 22)){      //>= 1 despues lo ponemos jode mucho
                       render(view:"/comedorCerrado")
                    }else{
                        pedidoService.guardarPedido(cliente.id)
                        session.cliente = cliente
                        redirect(action: "pedidoCreado")
                    }
                }else{
                    def pedidoParticular = Pedido.findByCliente(cliente)
                    render(view: "/pedidoEnCurso", model:[pedido: pedidoParticular])
                }
            }else{
                render(view:"/cestaVacia")
            }
        
        }else{
            render(view: "/cuentaBloqueada", model:[cliente: cliente])
           
        }
    }
    def pagarDeuda(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        render(view: "/pagarDeuda", model:[cliente: cliente])
        
    }
    
    def cancelarPedido(){

        Cliente cliente = session.cliente

        Pedido pedido = Pedido.findByCliente(cliente)

        if(pedido.estado == EstadoPedido.EN_CONFIRMACION){
            pedidoService.eliminarPedido(cliente.id, pedido.id)
            cestaService.vaciarCesta(cliente.id)
            session.cliente = Cliente.get(cliente.id)
            render(view: "/bienvenida")
        }else{
            render(view: "/pedidoEnCurso", model:[pedido: pedido])
        }
    }

    def pagarPedido(){
        def cliente = session.cliente
        Pedido pedido = Pedido.findByCliente(cliente)
        if(pedido.estadoPago != EstadoDelPago.PAGADO){
            pedidoService.pagarPedido(pedido.id)
            session.cliente = Cliente.get(cliente.id)
            render(view: "/pedidoPago")
        }else{
            render(view:"/pedidoYaPago")
        }
        
    }

}
