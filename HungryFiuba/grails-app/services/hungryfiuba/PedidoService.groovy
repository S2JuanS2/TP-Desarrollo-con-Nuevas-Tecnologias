package hungryfiuba

import grails.gorm.transactions.Transactional

class PedidoService {

    @Transactional //para no cerrar la session, todo lo que yo acceda desde atributos tiene que permanecer en la session (ej: cliente.cesta)
    def guardarPedido(long clienteId) {

        Cliente cliente = Cliente.get(clienteId)
        Cesta cesta = cliente.cesta

        Pedido pedido = new Pedido(cliente, cesta)
        pedido.save(failOnError: true)
        
    }



}
