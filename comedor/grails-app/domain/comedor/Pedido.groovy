package comedor

import java.time.LocalDateTime


class Pedido {

    Cliente cliente
    Articulo articulo
    
    int cantidadDeArticulos
    BigDecimal precioTotal

    LocalDateTime momentoCreacion
    LocalDateTime momentoEntrega

    static constraints = {
        cliente nullable: false
        articulo nullable: false
        precioTotal nullable: false
        momentoCreacion nullable: false
        momentoEntrega nullable: true
    }

    Pedido(Cliente cliente, Articulo articulo, int cantidadDeArticulos, LocalDateTime momentoActual) {
        assert cliente != null
        assert articulo != null
        assert cantidadDeArticulos >= 1
        assert articulo.stock >= cantidadDeArticulos
        
        this.cliente = cliente
        this.articulo = articulo
        this.cantidadDeArticulos = cantidadDeArticulos
        this.precioTotal = cantidadDeArticulos * articulo.precio
        this.momentoCreacion = momentoActual
        articulo.stock -= cantidadDeArticulos
    }
}
