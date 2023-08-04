package hungryfiuba

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ClienteSpec extends Specification implements DomainUnitTest<Cliente> {

    def setup() {
    }

    def cleanup() {
    }

    void "test probar contraseña del cliente con una contraseña igual"() {
        given:"Dado un cliente con una contraseña igual a contraseña123"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")

        when:"cuando comparo la contraseña del cliente con una igual"
        boolean resultado = cliente.contrasenaCorrecta("contraseña123")

        then:"Las contraseñas son iguales"
        resultado == true
    }

    void "test probar contraseña del cliente con una contraseña distinta"() {
        given:"Dado un cliente con una contraseña igual a contraseña123"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")

        when:"cuando comparo la contraseña del cliente con una distinta"
        boolean resultado = cliente.contrasenaCorrecta("otracontraseña")

        then:"Las contraseñas son distintas"
        resultado == false
    }

    void "test probar si el cliente con un pedido tiene un pedido"() {
        given:"Dado un cliente y una lista de pedidos"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        Pedido pedido = new Pedido(cliente, cliente.cesta)
        List<Pedido> listaPedidos = []
        listaPedidos.add(pedido)

        when:"cuando consulto si tiene un pedido"
        boolean resultado = cliente.tieneUnPedido(listaPedidos)

        then:"El cliente tiene un pedido"
        resultado == true
    }

    void "test probar si el cliente sin pedido tiene un pedido"() {
        given:"Dado 2 clientes, el cliente 1 con un pedido y el cliente 2 no"
        Cliente cliente1 = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        Cliente cliente2 = new Cliente("María", "Gómez", "DNI", "98765432", "otracontraseña")
        Pedido pedido = new Pedido(cliente1, cliente1.cesta)
        List<Pedido> listaPedidos = []
        listaPedidos.add(pedido)

        when:"Cuando consulto si el cliente 2 tiene un pedido"
        boolean resultado = cliente2.tieneUnPedido(listaPedidos)

        then:"El cliente no tiene un pedido"
        resultado == false
    }

    void "test probar si un cliente con 2 strikes tiene menos de 3 strikes"() {
        given:"Dado un cliente con 2 strikes"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.strikes = 2

        when:"cuando consulto si tiene menos de 3 strikes"
        boolean resultado = cliente.tieneMenosDeTresStrikes()

        then:"El cliente tiene menos de 3 strikes"
        resultado == true
    }

    void "test probar si un cliente con 3 strikes tiene menos de 3 strikes"() {
        given:"Dado un cliente con 3 strikes"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.strikes = 3

        when:"cuando consulto si tiene menos de 3 strikes"
        boolean resultado = cliente.tieneMenosDeTresStrikes()

        then:"El cliente no tiene menos de 3 strikes"
        resultado == false
    }

    void "test probar si un cliente con 3 strikes tiene 3 strikes"() {
        given:"Dado un cliente con 3 strikes"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.strikes = 3

        when:"cuando consulto si tiene 3 strikes"
        boolean resultado = cliente.tieneTresStrikes()

        then:"El cliente tiene 3 strikes"
        resultado == true
    }

    void "test probar si un cliente con 2 strikes tiene 3 strikes"() {
        given:"Dado un cliente con 2 strikes"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.strikes = 2

        when:"cuando consulto si tiene 3 strikes"
        boolean resultado = cliente.tieneTresStrikes()

        then:"El cliente no tiene 3 strikes"
        resultado == false
    }

    void "test agregar una calificacion a un cliente con 0 calificaciones"() {
        given:"Dado un cliente con 0 calificaciones"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")

        when:"Cuando le agrego una calificacion"
        cliente.agregarCalificacion()

        then:"la calificacion se incrementa en 1"
        cliente.calificacionesPendientes == 1
    }

    void "test aumentar la deuda de un cliente"() {
        given:"dado un cliente con una deuda de 0"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")

        when:"Cuando aumento su deuda en 500"
        cliente.aumentarDeuda(500)

        then:"El cliente tendra una deuda de 500"
        cliente.deuda == 500
    }

    void "test disminuir la deuda de un cliente"() {
        given:"dado un cliente con una deuda de 1000"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.deuda = 1000

        when:"Cuando disminuyo su deuda en 300"
        cliente.disminuirDeuda(300)

        then:"El cliente tendra una deuda de 700"
        cliente.deuda == 700
    }

    void "test probar que un cliente con calificaciones pendientes, tiene calificaciones pendientes"() {
        given:"Dado un cliente con 2 calificaciones pendientes"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.calificacionesPendientes = 2

        when:"Cuando consulto si tiene calificaciones pendientes"
        boolean resultado = cliente.tieneCalifacionesPendientes()

        then:"El cliente tiene calificaciones pendientes"
        resultado == true
    }

    void "test probar que un cliente sin calificaciones pendientes, no tiene calificaciones pendientes"() {
        given:"Dado un cliente sin calificaciones pendientes"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.calificacionesPendientes = 0

        when:"Cuando consulto si tiene calificaciones pendientes"
        boolean resultado = cliente.tieneCalifacionesPendientes()

        then:"El cliente no tiene calificaciones pendientes"
        resultado == false
    }

    void "test probar que un cliente con cuenta bloqueada, tiene la cuenta bloqueada"() {
        given:"Dado un cliente con la cuenta bloqueada"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.estado = EstadoCuenta.BLOQUEADA

        when:"Cuando consulto si tiene la cuenta bloqueada"
        boolean resultado = cliente.tieneCuentaBloqueada()

        then:"El cliente tiene la cuenta bloqueada"
        resultado == true
    }

    void "test probar que un cliente con cuenta no bloqueada, no tiene la cuenta bloqueada"() {
        given:"Dado un cliente con la cuenta desbloqueada"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.estado = EstadoCuenta.NO_BLOQUEADA

        when:"Cuando consulto si tiene la cuenta bloqueada"
        boolean resultado = cliente.tieneCuentaBloqueada()

        then:"El cliente no tiene la cuenta bloqueada"
        resultado == false
    }

    void "test probar que un cliente con 2 strikes al sumarle otro strike se le bloquea la cuenta"() {
        given:"Dado un cliente con 2 strikes"
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.strikes = 2

        when:"Cuando penalizo al cliente"
        cliente.penalizar()

        then:"Se le bloquea la cuenta y se le asigan 3 strikes"
        cliente.strikes == 3
        cliente.estado == EstadoCuenta.BLOQUEADA
    }

    void "test probar que un cliente con 1 strike al sumarle otro strike no se le bloquea la cuenta"() {
        given:
        Cliente cliente = new Cliente("Juan", "Pérez", "DNI", "12345678", "contraseña123")
        cliente.strikes = 1

        when:"Cuando penalizo al cliente"
        cliente.penalizar()

        then:"Se le incrementa 1 strike pero no se le bloquea la cuenta"
        cliente.strikes == 2
        cliente.estado == EstadoCuenta.NO_BLOQUEADA
    }
}
