package hungryfiuba

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AdministradorSpec extends Specification implements DomainUnitTest<Administrador> {

    def setup() {
        Administrador admin = Administrador.obtenerAdministrador()
    }

    def cleanup() {
    }

    void "test calcular el promedio de la calificacion de estado"() {
        given:"Dado una calificacion total de estado de 15 y una cantidad de calificaciones de 3"
        admin.califEstado = 15
        admin.cantidadCalificaciones = 3

        when:"Cuando se pide mostrar la calificacion"
        int calificacionEstado = admin.mostrarCalificacionEstado()

        then:"Se muestra en promedio"
        calificacionEstado == 5
    }

    void "test calcular el promedio de la calificacion de rapidez"() {
        given:"Dado una calificacion de rapidez total de 20 y una cantidad de calificaciones de 4"
        admin.califRapidez = 20
        admin.cantidadCalificaciones = 4

        when:"Cuando se pide mostrar la calificacion"
        int calificacionRapidez = admin.mostrarCalificacionRapidez()

        then:"Se muestra en promedio"
        calificacionRapidez == 5
    }

    void "test calcular el promedio de la calificacion de pagina"() {
        given:"Dado una calificacion de pagina total de 25 y una cantidad de calificaciones de 5"
        admin.califPagina = 25
        admin.cantidadCalificaciones = 5

        when:"Cuando se pide mostrar la calificacion"
        int calificacionPagina = admin.mostrarCalificacionPagina()

        then:"Se muestra en promedio"
        calificacionPagina == 5
    }

    void "test aumentar la cantidad de calificaciones"() {
        given:"Dada una cantidad inicial de calificaciones"
        int cantidadInicial = admin.cantidadCalificaciones

        when:"Cuando se aumenta en 1"
        admin.aumentarCantidadCalificaciones()

        then:"Se incrementa la cantidad en 1"
        admin.cantidadCalificaciones == cantidadInicial + 1
    }    
}
