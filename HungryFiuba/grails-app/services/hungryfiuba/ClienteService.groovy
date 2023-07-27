package hungryfiuba

import grails.gorm.transactions.Transactional

class ClienteService {

    @Transactional //<- es para no cerrar la sesion, todo lo que yo acceda desde atributos tiene que permanecer en la session (ej: cliente.cesta)
    def actualizarDeuda(long clienteId) {

        Cliente cliente = Cliente.get(clienteId)
        cliente.deuda = 0
        cliente.estado = EstadoCuenta.NO_BLOQUEADA
        cliente.save(flush: true)
        
    }
}