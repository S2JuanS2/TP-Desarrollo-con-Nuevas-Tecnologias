package hungryfiuba

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ArticuloSpec extends Specification implements DomainUnitTest<Articulo> {

    def setup() {
    }

    def cleanup() {
    }

    void "test consultar si hay stock en un articulo con stock mayor a 0"() {
        given:"Dado un articulo con stock mayor a 0"
        Articulo articulo = new Articulo("Pepsi", 100, 1, 10, "urlimagen", "Descrip")

        when:"Cuando se pregunta si hay stock"
        boolean resultado = articulo.hayStock()

        then:"Hay stock"
        resultado == true
    }

    void "test consultar si hay stock en un articulo con stock igual a 0"() {
        given:"Dado un articulo con stock igual a 0"
        Articulo articulo = new Articulo("Pepsi", 100, 1, 0, "urlimagen", "Descrip")

        when:"Cuando se pregunta si hay stock"
        boolean resultado = articulo.hayStock()

        then:"No hay stock"
        resultado == false
    }

    void "test Se agrega un articulo a una cesta con un precio mayor al limite de compra"() {
        given:"Dado una cesta con un monto actual de 4900 y un articulo de precio 150"
        Cesta cesta = new Cesta()
        cesta.montoTotal = 4900.00
        Articulo articulo = new Articulo("Pepsi", 150, 2, 1, "urlimagen", "Descrip")

        when:"Cuando se consulta si supera el limite de 5000"
        boolean resultado = articulo.superaElLimiteDeCompra(cesta)

        then:"Supera el limite"
        resultado == true
    }

    void "test Se agrega un articulo a una cesta con un precio igual al limite de compra"() {
        given:"Dado una cesta con un monto actual de 5000 y un articulo de precio 200"
        Cesta cesta = new Cesta()
        cesta.montoTotal = 5000 
        Articulo articulo = new Articulo("Pepsi", 200, 3, 1, "urlimagen", "Descrip")

        when:"Cuando se consulta si supera el limite de 5000"
        boolean resultado = articulo.superaElLimiteDeCompra(cesta)

        then:"Supera el limite"
        resultado == true
    }

    void "test Se agrega un articulo a una cesta con un precio menor al limite de compra"() {
        given:"Dado una cesta con un monto actual de 4700 y un articulo de precio 300"
        Cesta cesta = new Cesta()
        cesta.montoTotal = 4700
        Articulo articulo = new Articulo("Pepsi", 300, 4, 1, "urlimagen", "Descrip")

        when:"Cuando se consulta si supera el limite de 5000"
        boolean resultado = articulo.superaElLimiteDeCompra(cesta)

        then:"No supera el limite"
        resultado == false
    }
}
