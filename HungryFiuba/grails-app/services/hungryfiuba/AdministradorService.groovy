package hungryfiuba

import grails.gorm.transactions.Transactional


class AdministradorService {

    @Transactional
    def calificaciones(Long clienteId){
        Cliente cliente = Cliente.get(clienteId)
        Administrador administrador = Administrador.findByNombre("admin")
        administrador.cantidadCalificaciones++

        //ESTA MAL ESTO ES TEMPORAL
        administrador.califRapidez = (cliente.aspectoUnoSuma + administrador.califRapidez)/administrador.cantidadCalificaciones
        administrador.califEstado = (cliente.aspectoDosSuma + administrador.califEstado)/administrador.cantidadCalificaciones
        administrador.califPagina =  (cliente.aspectoTresSuma + administrador.califPagina)/administrador.cantidadCalificaciones

        administrador.save(flush: true)
    }

}