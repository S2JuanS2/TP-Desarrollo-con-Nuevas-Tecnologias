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
        precioTotal nullable: false, min: 0.00
        momentoDeCreacion nullable: false
        estadoPago nullable: false
    }

    Pedido(Cliente cliente,Cesta cesta) {
        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        if(!cesta){
            throw new ObjetoNoExisteException("La cesta no existe")
        }
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

    //devuelve true si el estado esta en confirmacion 
    boolean enConfirmacion(){
        return this.estado == EstadoPedido.EN_CONFIRMACION
    }

    //devuelve true si el estado esta en preparacion
    boolean enPreparacion(){
        return this.estado == EstadoPedido.EN_PREPARACION
    }

    //devuelve true si el estado esta en listo para entregar
    boolean listoParaEntregar(){
        return this.estado == EstadoPedido.LISTO_PARA_ENTREGAR
    }

    //devuelve true si el estado esta en entregado
    boolean fueEntregado(){
        return estado == EstadoPedido.ENTREGADO
    }

    //devuelve true si el estado esta en pagado
    boolean estaPago(){
       return estadoPago == EstadoDelPago.PAGADO
    }
    
    

    //devuleve true si el pedido esta en confirmacion o listo para entregar o entregado
    boolean puedeSerCancelado(){
        return (enConfirmacion() || listoParaEntregar() || fueEntregado())
    }
    
    // verifica si ha transcurrido al menos una hora desde la creación del pedido 
    //y si el pedido está en los estados de "En Preparación" o "Listo para Entregar".
    // Si ambas condiciones se cumplen, la función retorna true, lo que indica que el 
    //pedido debe ser cancelado. Si alguna de las condiciones no se cumple, la función 
    //retorna false, lo que indica que el pedido no necesita ser cancelado en este momento.
    boolean debeSerCancelado(){
        LocalDateTime ahora = LocalDateTime.now()
        long horasTranscurridas = momentoDeCreacion.until(ahora, ChronoUnit.HOURS)
        return (horasTranscurridas >= 1 && (enPreparacion() || listoParaEntregar()))
    }

    
}
