package hungryfiuba

import grails.gorm.transactions.Transactional

class ClienteService {

    //actualiza la deuda de un cliente y establecer su estado de cuenta como no bloqueado. Al realizar esta acción, se actualiza la información del cliente en la base de datos. 
    @Transactional 
    def actualizarDeuda(long clienteId) {
        Cliente cliente = Cliente.get(clienteId)
        cliente.deuda = 0
        cliente.estado = EstadoCuenta.NO_BLOQUEADA
        cliente.save(flush: true)
    }

    //permite actualizar las calificaciones de un cliente en diferentes aspectos. También permite marcar una 
    //calificación como completada mediante la reducción del contador de calificaciones pendientes. 
    @Transactional
    def actualizarCalificacion(long clienteId,int estrella, int aspecto){
        Cliente cliente = Cliente.get(clienteId)
        if(aspecto == 1){
            cliente.aspectoUnoSuma = estrella  
        }else if(aspecto == 2){
            cliente.aspectoDosSuma = estrella
        }else if(aspecto == 3){
            cliente.aspectoTresSuma = estrella
        }
        else if(aspecto == 0){
            cliente.calificacionesPendientes--
        }
    cliente.save(flush: true) 
    }
}