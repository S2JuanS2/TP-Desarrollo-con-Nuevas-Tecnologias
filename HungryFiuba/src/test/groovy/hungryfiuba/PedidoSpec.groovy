package hungryfiuba

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PedidoSpec extends Specification implements DomainUnitTest<Pedido> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        given: "dado un articulo con 10 de stock"
            Articulo articulo = new Articulo("Coca",400,13,10,"url","1l")

        when: "cuando agrega una coca a su cesta"
            //

        then:"se descuenta el stock del articulo"
            articulo.stock == 9

    }
}
