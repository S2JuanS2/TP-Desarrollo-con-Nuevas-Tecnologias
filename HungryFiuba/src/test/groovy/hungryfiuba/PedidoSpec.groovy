package hungryfiuba

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PedidoSpec extends Specification implements DomainUnitTest<Pedido> {

    def setup() {
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
    }

    def cleanup() {
    }

    void "test probar que un pedido en estado de confirmación esta en confirmación"() {
        given:"Dado un pedido en estado de confirmación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_CONFIRMACION

        when:"Cuando consulto si esta en estado de confirmación"
        boolean resultado = pedido.enConfirmacion()

        then:"El pedido esta en confirmación"
        resultado == true
    }

    void "test probar que un pedido en estado de preparación, no esta en confirmación"() {
        given:"Dado un pedido en estado de preparación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_PREPARACION

        when:"Cuando consulto si esta en estado de confirmación"
        boolean resultado = pedido.enConfirmacion()

        then:"El pedido no esta en confirmación"
        resultado == false
    }

    void "test probar que un pedido en estado de preparación esta en preparación"() {
        given:"Dado un pedido en estado de preparación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_PREPARACION

        when:"Cuando consulto si esta en estado de preparación"
        boolean resultado = pedido.enPreparacion()

        then:"El pedido esta en preparación"
        resultado == true
    }

    void "test probar que un pedido en estado de confirmación no esta en preparación"() {
        given:"Dado un pedido en estado de preparación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_CONFIRMACION

        when:"Cuando consulto si esta en estado de preparación"
        boolean resultado = pedido.enPreparacion()

        then:"El pedido no esta en preparación"
        resultado == false
    }

    void "test probar que un pedido en estado de listo para entregar esta en listo para entregar"() {
        given:"Dado un pedido en estado de listo para entregar"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.LISTO_PARA_ENTREGAR

        when:"Cuando consulto si esta en estado de listo para entregar"
        boolean resultado = pedido.listoParaEntregar()

        then:"El pedido esta listo para entregar"
        resultado == true
    }

    void "test probar que un pedido en estado de confirmación, no esta en listo para entregar"() {
        given:"Dado un pedido en estado de confirmación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_CONFIRMACION

        when:"Cuando consulto si esta en estado de listo para entregar"
        boolean resultado = pedido.listoParaEntregar()

        then:"El pedido no esta listo para entregar"
        resultado == false
    }
    
        void "test probar que un pedido en estado de entregado esta entregado"() {
        given:"Dado un pedido en estado entregado"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.ENTREGADO

        when:"Cuando consulto si esta en estado de entregado"
        boolean resultado = pedido.fueEntregado()

        then:"El pedido fue entregado"
        resultado == true
    }

    void "test probar que un pedido en estado de confirmación, no esta entregado"() {
        given:"Dado un pedido en estado de confirmación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_CONFIRMACION

        when:"Cuando consulto si esta en estado de entregado"
        boolean resultado = pedido.fueEntregado()

        then:"El pedido no fue entregado"
        resultado == false
    }

    void "test probar que un pedido en estado pagado, esta pagado"() {
        given:"Dado un pedido pagado"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estadoPago = EstadoDelPago.PAGADO

        when:"Cuando consulto si el pedido esta pago"
        boolean resultado = pedido.estaPago()

        then:"El pedido está pago"
        resultado == true
    }

    void "test probar que un pedido en estado pendiente de pago, no esta pagado"() {
        given:"Dado un pedido no pagado"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estadoPago = EstadoDelPago.PENDIENTE_DE_PAGO

        when:"Cuando consulto si el pedido esta pago"
        boolean resultado = pedido.estaPago()

        then:"El pedido no está pago"
        resultado == false
    }

    void "test probar que si se confirma un pedido en estado de confirmación pasa a preparación"() {
        given:"Dado un pedido en estado de confirmación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_CONFIRMACION

        when:"Cuando confirmo el pedido"
        pedido.confirmar()

        then:"El pedido del estado pasa a preparación"
        pedido.estado == EstadoPedido.EN_PREPARACION
    }

    void "test probar que si se confirma un pedido en estado de preparación pasa a listo para entregar"() {
        given:"Dado un pedido en estado de preparación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_PREPARACION

        when:"Cuando confirmo el pedido"
        pedido.confirmar()

        then:"El pedido del estado pasa a listo para entregar"
        pedido.estado == EstadoPedido.LISTO_PARA_ENTREGAR
    }

    void "test probar que si se confirma un pedido en estado de listo para entregar pasa a entregado"() {
        given:"Dado un pedido en estado de listo para entregar"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.LISTO_PARA_ENTREGAR

        when:"Cuando confirmo el pedido"
        pedido.confirmar()

        then:"El pedido del estado pasa a entregado"
        pedido.estado == EstadoPedido.ENTREGADO
    }

    void "test probar que un pedido en estado de confirmación puede ser cancelado"() {
        given:"Dado un pedido en estado de confirmación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_CONFIRMACION

        when:"Cuando consulto si puede ser cancelado"
        boolean resultado = pedido.puedeSerCancelado()

        then:"El pedido puede ser cancelado"
        resultado == true
    }

    void "test probar que un pedido en estado de listo para entregar puede ser cancelado"() {
        given:"Dado un pedido en estado listo para entregar"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.LISTO_PARA_ENTREGAR

        when:"Cuando consulto si puede ser cancelado"
        boolean resultado = pedido.puedeSerCancelado()

        then:"El pedido puede ser cancelado"
        resultado == true
    }

    void "test probar que un pedido en estado entregado puede ser cancelado"() {
        given:"Dado un pedido en estado entregado"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.ENTREGADO

        when:"Cuando consulto si se puede cancelar"
        boolean resultado = pedido.puedeSerCancelado()

        then:"El pedido puede ser cancelado"
        resultado == true
    }

    void "test probar que un pedido en estado de preparación, no puede ser cancelado"() {
        given:"Dado un pedido en estado de preparación"
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        pedido.estado = EstadoPedido.EN_PREPARACION

        when:"Cuando consulto si se puede cancelar"
        boolean resultado = pedido.puedeSerCancelado()

        then:"El pedido no puede ser cancelado"
        resultado == false
    }
}
