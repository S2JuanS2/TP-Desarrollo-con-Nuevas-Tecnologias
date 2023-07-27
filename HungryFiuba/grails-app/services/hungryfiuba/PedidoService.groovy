package hungryfiuba

import grails.gorm.transactions.Transactional

class PedidoService {

    @Transactional //<- es para no cerrar la sesion, todo lo que yo acceda desde atributos tiene que permanecer en la session (ej: cliente.cesta)
    def guardarPedido(long clienteId) {

        Cliente cliente = Cliente.get(clienteId)
        Cesta cesta = cliente.cesta

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

        def pedido = Pedido.get(pedidoId)

        pedido.delete(flush: true)

    }

    @Transactional
    def confirmarPedido(long pedidoId){

        def pedido = Pedido.get(pedidoId)

        pedido.estado = EstadoPedido.EN_PREPARACION

        pedido.save(flush: true)

    }

    @Transactional
    def pagarPedido(long pedidoId){

        Pedido pedido = Pedido.get(pedidoId)

        pedido.estadoPago = EstadoDelPago.PAGADO
        pedido.save(flush: true)
    }



}
