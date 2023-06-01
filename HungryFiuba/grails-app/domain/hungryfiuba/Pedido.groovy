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

}
