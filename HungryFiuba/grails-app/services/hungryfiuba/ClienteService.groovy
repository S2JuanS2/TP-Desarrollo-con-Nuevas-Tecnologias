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
    def actualizarCalificacion(long clienteId,String estrella, int aspecto){
        Cliente cliente = Cliente.get(clienteId)
        int numero

        //Casteador manual de string a entero de ultima generación.
        switch(estrella) {
            case "1":
                numero = 1
                break
            case "2":
                numero = 2
                break
            case "3":
                numero = 3
                break
            case "4":
                numero = 4
                break
            case "5":
                numero = 5
                break
        }

        if(aspecto == 1){
            cliente.aspectoUnoSuma = numero
        }else if(aspecto == 2){
            cliente.aspectoDosSuma = numero
        }else if(aspecto == 3){
            cliente.aspectoTresSuma = numero
        }else if(aspecto == 0){
            cliente.calificacionesPendientes--
        }

    cliente.save(flush: true) 
    }
}