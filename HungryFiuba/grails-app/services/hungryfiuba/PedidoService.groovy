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
        cliente.aumentarDeuda(cesta.montoTotal)
        Pedido pedido = new Pedido(cliente, cesta)
        pedido.save(failOnError: true)
    }

    //elimina un pedido específico en la base de datos
    @Transactional
    def eliminarPedido(long pedidoId){
        Pedido pedido = Pedido.get(pedidoId)
        pedido.delete()
    }
    
    //actualiza el estado de un pedido a "En Preparación" y guarda los cambios en la base de datos.
    @Transactional
    def cambiarAEnPreparacion(Pedido pedido) {
            pedido.setEstado(EstadoPedido.EN_PREPARACION)
            pedido.save()
    }

    ////actualiza el estado de un pedido a "Listo para entregar" y guarda los cambios en la base de datos.
    @Transactional
    def cambiarAListoParaEntregar(Pedido pedido) {
            pedido.setEstado(EstadoPedido.LISTO_PARA_ENTREGAR)
            pedido.save()
    }

    ////actualiza el estado de un pedido a "entregado" y guarda los cambios en la base de datos.
    @Transactional
    def cambiarAEntregado(Pedido pedido) {
            pedido.setEstado(EstadoPedido.ENTREGADO)
            pedido.cliente.agregarCalificacion()
            pedido.save()
    }

    //realiza el pago de un pedido en la base de datos. Actualiza el saldo de la deuda del cliente y marca el 
    //estado de pago del pedido como "PAGADO". 
    @Transactional
    def pagarPedido(long pedidoId){
        Pedido pedido = Pedido.get(pedidoId)
        pedido.cliente.disminuirDeuda(pedido.precioTotal)
        pedido.setEstadoPago(EstadoDelPago.PAGADO)
        pedido.save()
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
        eliminarPedido(pedidoId)
        cestaService.vaciarCesta(clienteId)
    } 
}
