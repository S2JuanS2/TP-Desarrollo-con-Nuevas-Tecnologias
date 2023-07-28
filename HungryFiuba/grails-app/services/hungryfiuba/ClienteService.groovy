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
    @Transactional
    def actualizarCalificacion(long clienteId,int estrella, int aspecto){
        Cliente cliente = Cliente.get(clienteId)
        if(aspecto == 1){
            cliente.aspectoUnoSuma += estrella  
        }else if(aspecto == 2){
            cliente.aspectoDosSuma += estrella
        }else if(aspecto == 3){
            cliente.aspectoTresSuma += estrella
        }
        else if(aspecto == 0){
            cliente.calificacionesPendientes--
        }
    cliente.save(flush: true) 
    }
}