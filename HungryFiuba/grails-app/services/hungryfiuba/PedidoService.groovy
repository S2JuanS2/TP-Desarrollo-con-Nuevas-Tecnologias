package hungryfiuba

import grails.gorm.transactions.Transactional

class PedidoService {
    def cestaService
   
    //guarda el pedido realizado por un cliente en la base de datos. Al realizar esta acción, se actualiza
    // la deuda del cliente con el costo del pedido y se crea un registro del pedido en la base de datos. 
    @Transactional 
    def guardarPedido(long clienteId) {
        Cliente cliente = Cliente.get(clienteId)
        Cesta cesta = cliente.cesta
        cliente.deuda += cesta.montoTotal
        Pedido pedido = new Pedido(cliente, cesta)
        pedido.save(failOnError: true)
    }

    //elimina un pedido específico realizado por un cliente en la base de datos. 
    @Transactional
    def eliminarPedido(long clienteId, long pedidoId){
        def cliente = Cliente.get(clienteId)
        def pedido = Pedido.get(pedidoId)
        def cesta = pedido.cesta
        pedido.delete(flush: true)
    }

    //elimina un pedido específico en la base de datos
    @Transactional
    def eliminarPedido(long pedidoId){
        Pedido pedido = Pedido.get(pedidoId)
        pedido.delete(flush: true)
    }
    
    //actualiza el estado de un pedido a "En Preparación" y guarda los cambios en la base de datos.
    @Transactional
    def cambiarAEnPreparacion(Pedido pedido) {
            pedido.estado = EstadoPedido.EN_PREPARACION
            pedido.save(flush: true)
    }

    ////actualiza el estado de un pedido a "Listo para entregar" y guarda los cambios en la base de datos.
    @Transactional
    def cambiarAListoParaEntregar(Pedido pedido) {
            pedido.estado = EstadoPedido.LISTO_PARA_ENTREGAR
            pedido.save(flush: true)
    }

    ////actualiza el estado de un pedido a "entregado" y guarda los cambios en la base de datos.
    @Transactional
    def cambiarAEntregado(Pedido pedido) {
            pedido.estado = EstadoPedido.ENTREGADO
            pedido.cliente.calificacionesPendientes++
            pedido.save(flush: true)
    }

    //realiza el pago de un pedido en la base de datos. Actualiza el saldo de la deuda del cliente y marca el 
    //estado de pago del pedido como "PAGADO". 
    @Transactional
    def pagarPedido(long pedidoId){
        Pedido pedido = Pedido.get(pedidoId)
        pedido.cliente.deuda -= pedido.precioTotal
        pedido.estadoPago = EstadoDelPago.PAGADO
        pedido.save(flush: true)
    }

    //cancela un pedido si se cumplen ciertas condiciones y realiza acciones adicionales como penalizar al cliente 
    //(si el pedido no ha sido pagado y está listo para entregar) y vaciar la cesta asociada al cliente 
    //(dependiendo del estado del pedido).
    @Transactional
    def cancelarYActualizarPedido(Pedido pedido, Cliente cliente){
        if (pedido.puedeSerCancelado()) {
            if(!pedido.estaPago() && pedido.listoParaEntregar()) {
                cliente.penalizar()
            }
            if(pedido.fueEntregado()){
                cestaService.vaciarCestaDePedidoFinalizado(cliente.id)
            }else{
                cestaService.vaciarCesta(cliente.id)
            }
            eliminarPedido(pedido.id)
        }
    }   

    //cancela un pedido eliminando el pedido de la base de datos y luego vacía la cesta del cliente eliminando los artículos de la cesta.
    @Transactional
    def cancelacionPedido(long clienteId, long pedidoId){
        eliminarPedido(clienteId, pedidoId)
        cestaService.vaciarCesta(clienteId)
    } 
}
