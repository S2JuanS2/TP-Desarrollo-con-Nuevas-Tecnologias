package hungryfiuba

import java.time.LocalDateTime

enum EstadoPedido {

    EN_CONFIRMACION("A confirmar"),
    EN_PREPARACION("En preparación"),
    LISTO_PARA_ENTREGAR("Listo para retirar"),
    ENTREGADO("Entregado"),
    CANCELADO("Cancelado")

    final String descripcion

    EstadoPedido(String descripcion){
        this.descripcion = descripcion
    }

    String toString(){
        return descripcion
    }
}

enum EstadoDelPago {

    PENDIENTE_DE_PAGO("Pendiente de pago"),
    PAGADO("Pagado")

    final String descripcion

    EstadoDelPago(String descripcion){
        this.descripcion = descripcion
    }

    String toString(){
        return descripcion
    }
}

class Pedido {

    int cantidadDeArticulos
    BigDecimal precioTotal
    EstadoPedido estado
    EstadoDelPago estadoPago

    LocalDateTime momentoDeCreacion

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

    //
    boolean enConfirmacion(){
        return this.estado == EstadoPedido.EN_CONFIRMACION
    }

    //
    boolean enPreparacion(){
        return this.estado == EstadoPedido.EN_PREPARACION
    }
    
    //
    boolean listoParaEntregar(){
        return this.estado == EstadoPedido.LISTO_PARA_ENTREGAR
    }

    boolean fueEntregado(){
        return estado == EstadoPedido.ENTREGADO
    }

    //
    boolean estaPago(){
       return estadoPago == EstadoDelPago.PAGADO
    }
    
    void confirmar(){
        if(enConfirmacion()){
            estado = EstadoPedido.EN_PREPARACION
        }else if(enPreparacion()){
            estado = EstadoPedido.LISTO_PARA_ENTREGAR
        }else if(listoParaEntregar()){
            estado = EstadoPedido.ENTREGADO
        }
    } 

    //devuleve true si el pedido esta en confirmacion o listo para entregar o entregado
    boolean puedeSerCancelado(){
        return (enConfirmacion() || listoParaEntregar() || fueEntregado())
    }
    
    //
    boolean debeSerCancelado(){
        LocalDateTime ahora = LocalDateTime.now()
        long horasTranscurridas = momentoDeCreacion.until(ahora, ChronoUnit.HOURS)
        return (horasTranscurridas >= 1 && (enPreparacion() || listoParaEntregar()))
    }


    
}
