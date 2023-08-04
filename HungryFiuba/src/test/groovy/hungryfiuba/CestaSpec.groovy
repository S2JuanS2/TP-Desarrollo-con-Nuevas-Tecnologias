package hungryfiuba

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CestaSpec extends Specification implements DomainUnitTest<Cesta> {

    def setup() {
    }

    def cleanup() {
    }

    void "test incrementar la cantidad de articulos de una cesta"() {
        given:"Dada una cesta con 0 articulos"
        Cesta cesta = new Cesta()

        when:"Cuando se incrementa la cantidad de articulos"
        cesta.incrementarCantidadDeArticulos()

        then:"Se incrementa en 1"
        cesta.cantidadDeArticulos == 1
    }

    void "test decrementar la cantidad de articulos de una cesta con una cantidad mayor a 0"() {
        given:"Dada una cesta con 5 articulos"
        Cesta cesta = new Cesta()
        cesta.cantidadDeArticulos = 5

        when:"Cuando se decrementa la cantidad de articulos"
        cesta.disminuirCantidadDeArticulos()

        then:"Se decrementa en 1"
        cesta.cantidadDeArticulos == 4
    }

    void "test decrementar la cantidad de articulos de una cesta con una cantidad igual a 0"() {
        given:"Dada una cesta con 0 articulos"
        Cesta cesta = new Cesta()

        when:"Cuando se decrementa la cantidad de articulos"
        cesta.disminuirCantidadDeArticulos()

        then:"La cantidad de articulos sigue siendo 0"
        cesta.cantidadDeArticulos == 0
    }

    void "test consultar si una cesta con 2 articulos tiene articulos"() {
        given:"Dada una cesta con 2 articulos"
        Cesta cesta = new Cesta()
        cesta.cantidadDeArticulos = 2

        when:"Cuando se consulta si tiene articulos"
        boolean resultado = cesta.tieneArticulos()

        then:"Tiene articulos"
        resultado == true
    }

    void "test consultar si una cesta con 0 articulos tiene articulos"() {
        given:"Dada una cesta con 0 articulos"
        Cesta cesta = new Cesta()

        when:"Cuando se consulta si tiene articulos"
        boolean resultado = cesta.tieneArticulos()

        then:"No tiene articulos"
        resultado == false
    }

    void "test actualizar monto total de la cesta"() {
        given:"Dada una cesta con un monto total de 100"
        Cesta cesta = new Cesta()
        cesta.montoTotal = 100

        when:"Cuando se le suma 50"
        cesta.actualizarMontoTotal(50.0)

        then:"El monto total sube a 150"
        cesta.montoTotal == 150
    }
}
