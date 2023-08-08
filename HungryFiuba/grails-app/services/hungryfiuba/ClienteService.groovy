package hungryfiuba

import grails.gorm.transactions.Transactional

class ClienteService {
    def administradorService
    //actualiza la deuda de un cliente y establecer su estado de cuenta como no bloqueado. Al realizar esta acción, se actualiza la información del cliente en la base de datos. 
    @Transactional 
    def actualizarDeuda(long clienteId) {
        Cliente cliente = Cliente.get(clienteId)
        cliente.desbloquearCuenta()
        session.setAttribute('estado',cliente.estado.toString())
        cliente.save(flush: true)
    }

    //permite actualizar las calificaciones de un cliente en diferentes aspectos. También permite marcar una 
    //calificación como completada mediante la reducción del contador de calificaciones pendientes. 
    @Transactional
    def actualizarCalificacion(long clienteId,String estrella, int aspecto){
        Cliente cliente = Cliente.get(clienteId)
        int numero
        
        if(estrella){
            numero = estrella.toInteger()
        }

        if(aspecto == 1){
            cliente.aspectoUnoSuma = numero
            session.setAttribute('aspectoUnoSuma',numero)
        }else if(aspecto == 2){
            cliente.aspectoDosSuma = numero
            session.setAttribute('aspectoDosSuma',numero)           
        }else if(aspecto == 3){
            cliente.aspectoTresSuma = numero
            session.setAttribute('aspectoTresSuma',numero)
        }else if(aspecto == 0){
            cliente.calificacionesPendientes--
            session.setAttribute('calificacionesPendientes',cliente.calificacionesPendientes)
        }

    cliente.save(flush: true) 
    }

    //
    @Transactional
    def mostrarCalificacion(long clienteId){
        actualizarCalificacion(clienteId,null, 0)
        administradorService.calificaciones(clienteId)
    }
}