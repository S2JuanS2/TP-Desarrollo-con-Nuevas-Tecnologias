package hungryfiuba

import grails.gorm.transactions.Transactional


class AdministradorService {

    @Transactional
    def calificaciones(){
        Administrador administrador = Administrador.findByNombre("admin")
        administrador.cantidadCalificaciones++
        administrador.save(flush: true)
    }

}