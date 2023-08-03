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

    //muestra la vista /pedidoCreado que contiene información sobre un pedido recién creado por el cliente. 
    //Después de realizar un pedido, el cliente es redirigido a esta vista para mostrar los detalles del pedido 
    //y proporcionar información sobre el estado del pedido.
    def pedidoCreado(){
        Cliente cliente = session.cliente
        def pedido = Pedido.findByCliente(cliente)
        render(view: "/pedidoCreado", model: [pedido: pedido])
    }

    //muestra la vista de cesta vacia
    def cestaVacia(){
        render(view: "/cestaVacia" )
    }

    //permite a un cliente crear un nuevo pedido, siempre que el cliente no tenga la cuenta bloqueada, la cesta 
    //no esté vacía y no tenga ningún pedido en curso. Se realizan verificaciones adicionales de la hora actual 
    //para garantizar que los pedidos se realicen dentro del horario permitido. La función muestra vistas específicas 
    //en función de las condiciones y acciones realizadas.
    def crearPedido(){
        def cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        Cesta cesta = cliente.cesta
        def listaPedidos = Pedido.list()
        boolean clienteExisteEnPedidos = listaPedidos.any { pedido -> pedido.cliente == cliente }
        LocalDateTime hora = LocalDateTime.now()

        if(cliente.cuentaBloqueada()){ 
            if(cesta.tieneArticulos()){
                if(!cliente.clienteExisteEnPedidos(listaPedidos) ){
                   if((hora.getHour() >= 24)){      //>= 1 despues lo ponemos jode mucho
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

    //muestra una vista que permite al cliente visualizar y pagar su deuda. Al obtener el cliente actualizado de la base 
    //de datos, se asegura de que cualquier cambio en el saldo del cliente se refleje adecuadamente en la vista /pagarDeuda. 
    def pagarDeuda(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        render(view: "/pagarDeuda", model:[cliente: cliente]) 
    }
    
    //permite al cliente cancelar un pedido pendiente de confirmación . Si el pedido está en estado de confirmación,
    // se cancela el pedido y se vacía la cesta de compras del cliente. Después de cancelar el pedido, el cliente es
    // redirigido a la vista /bienvenida . Si el pedido no se puede cancelar, se muestra la vista /pedidoEnCurso con
    // información sobre el pedido en curso.
    def cancelarPedido(){
        Cliente cliente = session.cliente
        Pedido pedido = Pedido.findByCliente(cliente)

        if(pedido.enConfirmacion()){
            pedidoService.eliminarPedido(cliente.id, pedido.id)
            cestaService.vaciarCesta(cliente.id)
            session.cliente = Cliente.get(cliente.id)
            render(view: "/bienvenida")
        }else{
            render(view: "/pedidoEnCurso", model:[pedido: pedido])
        }
    }

    //permite al cliente pagar un pedido pendiente de pago. Si el pedido no ha sido pagado, se marca como pagado y 
    //se actualiza el estado de pago del pedido. Después de realizar el pago, el cliente es redirigido a la vista
    // /pedidoPago. Si el pedido ya ha sido pagado, se muestra la vista /pedidoYaPago para indicar que no es posible
    //realizar otro pago para este pedido.
    def pagarPedido(){
        def cliente = session.cliente
        Pedido pedido = Pedido.findByCliente(cliente)
        if(pedido.estaPago()){
            pedidoService.pagarPedido(pedido.id)
            session.cliente = Cliente.get(cliente.id)
            render(view: "/pedidoPago")
        }else{
            render(view:"/pedidoYaPago")
        }
    }
}
