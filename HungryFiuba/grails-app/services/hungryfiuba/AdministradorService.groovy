package hungryfiuba

import grails.gorm.transactions.Transactional


class AdministradorService {

    // registrar las calificaciones proporcionadas por un cliente en tres aspectos diferentes. 
    //Estas calificaciones se suman a los contadores correspondientes en el objeto Administrador. 
    //La función es transaccional para garantizar la consistencia de los datos en la base de datos. 
    @Transactional
    def calificaciones(Long clienteId){
        Cliente cliente = Cliente.get(clienteId)
        Administrador administrador = Administrador.obtenerAdministrador()
        administrador.cantidadCalificaciones++

        administrador.califRapidez += cliente.aspectoUnoSuma
        administrador.califEstado += cliente.aspectoDosSuma
        administrador.califPagina += cliente.aspectoTresSuma

        administrador.save(flush: true)
    }

}