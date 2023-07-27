package hungryfiuba
import java.time.LocalDateTime

class PedidoController {

    static scaffold = Pedido

    def pedidoService

    def comenzarPedido(){

        render(view: "/bienvenida")
    }

    def pedidoCreado(){

        Cliente cliente = session.cliente
        def pedido = Pedido.findByCliente(cliente)
        render(view: "/pedidoCreado", model: [pedido: pedido])
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
        if(cliente.estado != EstadoCuenta.BLOQUEADA){ 
            if(cesta.cantidadDeArticulos>0){
                if(!clienteExisteEnPedidos ){
                   // if(!(hora.getHour()) ){//>= 1 despues lo ponemos jode mucho
                     //   render(view:"/comedorCerrado")
                    //}else{
                        pedidoService.guardarPedido(cliente.id)
                        redirect(action: "pedidoCreado")
                    //}
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

        def cliente = session.cliente
        def pedido = Pedido.findByCliente(cliente)
        pedidoService.eliminarPedido(cliente.id, pedido.id)
    
        render(view: "/bienvenida")
    }

}
