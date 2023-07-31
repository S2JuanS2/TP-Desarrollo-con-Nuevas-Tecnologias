package hungryfiuba

import grails.gorm.transactions.Transactional

class PedidoService {

    @Transactional //<- es para no cerrar la sesion, todo lo que yo acceda desde atributos tiene que permanecer en la session (ej: cliente.cesta)
    def guardarPedido(long clienteId) {

        Cliente cliente = Cliente.get(clienteId)
        Cesta cesta = cliente.cesta

        cliente.deuda += cesta.montoTotal

        Pedido pedido = new Pedido(cliente, cesta)
        pedido.save(failOnError: true)
        
    }

    @Transactional
    def eliminarPedido(long clienteId, long pedidoId){

        def cliente = Cliente.get(clienteId)
        def pedido = Pedido.get(pedidoId)
        def cesta = pedido.cesta

        pedido.delete(flush: true)

    }

    @Transactional
    def eliminarPedido(long pedidoId){

        Pedido pedido = Pedido.get(pedidoId)

        pedido.delete(flush: true)

    }

    @Transactional
    def confirmarPedido(long pedidoId){

        Pedido pedido = Pedido.get(pedidoId)
        if(pedido.estado == EstadoPedido.EN_PREPARACION){
            pedido.estado = EstadoPedido.LISTO_PARA_ENTREGAR
        }else if(pedido.estado == EstadoPedido.EN_CONFIRMACION){
            pedido.estado = EstadoPedido.EN_PREPARACION
        }else if(pedido.estado == EstadoPedido.LISTO_PARA_ENTREGAR){
            pedido.estado = EstadoPedido.ENTREGADO
            pedido.cliente.calificacionesPendientes++
        }
        pedido.save(flush: true)

    }

    @Transactional
    def pagarPedido(long pedidoId){

        Pedido pedido = Pedido.get(pedidoId)
        
        pedido.cliente.deuda -= pedido.precioTotal

        pedido.estadoPago = EstadoDelPago.PAGADO
        pedido.save(flush: true)
    }



}
