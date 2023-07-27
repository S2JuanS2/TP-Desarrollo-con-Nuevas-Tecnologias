package hungryfiuba

import java.time.LocalDateTime

enum EstadoPedido {

    EN_CONFIRMACION,
    EN_PREPARACION,
    LISTO_PARA_ENTREGAR,
    ENTREGADO,
    CANCELADO
}

class Pedido {

    int cantidadDeArticulos
    BigDecimal precioTotal
    EstadoPedido estado

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
      
    }

    Pedido(Cliente cliente,Cesta cesta) {
        this.cliente = cliente
        this.cesta = cesta
        estado = EstadoPedido.EN_CONFIRMACION
        
        cantidadDeArticulos = cesta.cantidadDeArticulos
        momentoDeCreacion = LocalDateTime.now()
        
        List<Articulo> articulos = cesta.articulos
        def suma = 0
        articulos.each { articulo ->
            suma += articulo.precio
        }

        precioTotal = suma

    }
    
}
