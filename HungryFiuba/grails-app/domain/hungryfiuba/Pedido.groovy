package hungryfiuba

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
       cantidadDeArticulos = cesta.getCantidadDeArticulos
       precioTotal = cesta.getTotalPrice
    }

    //Metodos? No sé si sería enviar, recibir, etc, eso no se como se maneja xd

}
