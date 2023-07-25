package hungryfiuba

import java.time.LocalDateTime

class Pedido {

    Cliente cliente
    Cesta cesta

    int cantidadDeArticulos
    BigDecimal precioTotal

    //LocalDateTime momentoDeCreacion
    //LocalDateTime momentoDeEntrega


    static constraints = {

        cliente nullable: false
        cesta nullable: false
        precioTotal nullable: false
        //momentoDeCreacion nullable: false
        //momentoDeEntrega nullable: false
    }

    Pedido(Cliente cliente,Cesta cesta) {
       this.cliente = cliente
       this.cesta = cesta
       cantidadDeArticulos = 1
       precioTotal = 0

    }
    
}
