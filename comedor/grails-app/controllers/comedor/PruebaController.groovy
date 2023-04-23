package comedor

import java.time.LocalDateTime
import java.time.LocalDate

class CrearPedidoCommand {
    Long clienteId
    Long articuloId
    Integer cantidad

    static constraints = {
        clienteId nullable: false, min: 1L
        articuloId nullable: false, min: 1L
        cantidad nullable: false, min: 1
    }
}

class PruebaController {

    def pedidoService

    static allowedMethods = [
        'index': 'GET',
        'pedido': ['GET', 'POST'],
    ]

    def otro() {
    }

    def metodoGet() {
        Articulo a = Articulo.get(1)
        render([a: 1, b: 2, c: [3,1,11]] as grails.converters.JSON)
        // render "metodoGet"
    }
    
    def metodoPost() {
        render "metodoPost"
    }

    def index() {
    }

    def crearCliente() {

        Cliente cliente = new Cliente(
            nombre: params.nombre,
            apellido: params.apellido,
            identificadorTipo: params.identificadorTipo,
            identificadorValor: params.identificadorValor,
            fechaDeNacimiento: LocalDate.now(),
        ).save(failOnError: true)

        render "registrandome ${cliente}"
    }
    
    def listadoClientes() {
        [clientes: Cliente.list()]
    }
    
    def pedido() {
        [
            clientes: Cliente.list(),
            articulos: Articulo.list(),
        ]
    }

    def crearPedido(CrearPedidoCommand cmd) {
        if (!cmd.hasErrors()) {
            Pedido p = pedidoService.crearPedido(cmd.clienteId, cmd.articuloId, cmd.cantidad)
            redirect action: 'pedidoCreadoBien', id: p.id
        } else {
            render "hubo errores ${cmd.errors}"
        }
    }

    def pedidoCreadoBien(Long id) {
        Pedido pedido = Pedido.get(id)
        if (pedido) {
            [
                pedido: pedido,
            ]
        } else {
            render(status: 404, view: '/notFound')
        }
    }
    
    def error() {
        throw new IllegalArgumentException()
    }
}
