package hungryfiuba

import grails.gorm.transactions.Transactional


class AdministradorService {

    @Transactional
    def calificaciones(Long clienteId){
        Cliente cliente = Cliente.get(clienteId)
        Administrador administrador = Administrador.findByNombre("admin")
        administrador.cantidadCalificaciones++

        administrador.califRapidez += cliente.aspectoUnoSuma
        administrador.califEstado += cliente.aspectoDosSuma
        administrador.califPagina += cliente.aspectoTresSuma

        administrador.save(flush: true)
    }

}