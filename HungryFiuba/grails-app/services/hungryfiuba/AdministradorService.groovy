package hungryfiuba

import grails.gorm.transactions.Transactional


class AdministradorService {

    @Transactional
    def calificaciones(){
        Administrador administrador = Administrador.get(cantidadCalificaciones)
        administrador.cantidadCalificaciones++
        administrador.save(flush: true) 
    }

}