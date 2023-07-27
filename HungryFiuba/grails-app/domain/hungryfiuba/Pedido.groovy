package hungryfiuba

import java.time.LocalDateTime

enum EstadoPedido {

    EN_CONFIRMACION,
    EN_PREPARACION,
    LISTO_PARA_ENTREGAR,
    ENTREGADO,
    CANCELADO
}

enum EstadoDelPago {
    PAGO,
    PENDIENTE_DE_PAGO
}

class Pedido {

    int cantidadDeArticulos
    BigDecimal precioTotal
    EstadoPedido estado
    EstadoDelPago estadoPago

    LocalDateTime momentoDeCreacion
    //LocalDateTime momentoDeEntrega

    Cliente cliente
    Cesta cesta

    static belongsTo = [cesta: Cesta, cliente: Cliente]

    static constraints = {

        cliente nullable: false
        cesta nullable: false
        precioTotal nullable: false
        momentoDeCreacion nullable: false
        estadoPago nullable: false
    }

    Pedido(Cliente cliente,Cesta cesta) {
        this.cliente = cliente
        this.cesta = cesta
        this.estado = EstadoPedido.EN_CONFIRMACION
        this.estadoPago = EstadoDelPago.PENDIENTE_DE_PAGO
        this.cantidadDeArticulos = this.cesta.cantidadDeArticulos
        this.momentoDeCreacion = LocalDateTime.now()
        
        List<Articulo> articulos = this.cesta.articulos
        def suma = 0
        articulos.each { articulo ->
            suma += articulo.precio
        }

        this.precioTotal = suma

    }
    
}
