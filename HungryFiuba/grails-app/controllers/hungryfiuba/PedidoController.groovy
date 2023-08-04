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
        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        def pedido = Pedido.findByCliente(cliente)
        render(view: "/pedidoCreado", model: [pedido: pedido])
    }

    //determina el horario de apertura del comedor
    boolean comedorAbierto(){
        LocalDateTime hora = LocalDateTime.now()
        return (hora.getHour() >= Administrador.HORA_APERTURA_COMEDOR && hora.getHour() < Administrador.HORA_CLAUSURA_COMEDOR)
    }

    //permite a un cliente crear un nuevo pedido, siempre que el cliente no tenga la cuenta bloqueada, la cesta 
    //no esté vacía y no tenga ningún pedido en curso. Se realizan verificaciones adicionales de la hora actual 
    //para garantizar que los pedidos se realicen dentro del horario permitido. La función muestra vistas específicas 
    //en función de las condiciones y acciones realizadas.
    def crearPedido(){
        def cliente = session.cliente
        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }

        cliente = Cliente.get(cliente.id)
        Cesta cesta = cliente.cesta
        def listaPedidos = Pedido.list()

        if(cliente.tieneCuentaBloqueada()){
            render(view: "/cuentaBloqueada", model:[cliente: cliente])
            return
        }
        if(!comedorAbierto()){
            render(view:"/comedorCerrado")
            return
        }
        if(!cesta.tieneArticulos()){
            render(view:"/cestaVacia")
            return
        }
        if(!cliente.tieneUnPedido(listaPedidos) ){
            pedidoService.guardarPedido(cliente.id)
            redirect(action: "pedidoCreado")
        }else{
            def pedidoParticular = Pedido.findByCliente(cliente)
            render(view: "/pedidoEnCurso", model:[pedido: pedidoParticular])
        }
    }
    
    //permite al cliente cancelar un pedido pendiente de confirmación. Si el pedido está en estado de confirmación,
    // se cancela el pedido y se vacía la cesta de compras del cliente. Después de cancelar el pedido, el cliente es
    // redirigido a la vista /bienvenida . Si el pedido no se puede cancelar, se muestra la vista /pedidoEnCurso con
    // información sobre el pedido en curso.
    def cancelarPedido(){
        Cliente cliente = session.cliente
        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        Pedido pedido = Pedido.findByCliente(cliente)

        if(pedido.enConfirmacion()){
            pedidoService.eliminarPedido(cliente.id, pedido.id)
            cestaService.vaciarCesta(cliente.id)
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
        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        Pedido pedido = Pedido.findByCliente(cliente)

        if(!pedido.estaPago()){
            pedidoService.pagarPedido(pedido.id)
            render(view: "/pedidoPago")
        }else{
            render(view:"/pedidoYaPago")
        }
    }
}
