package hungryfiuba

class Administrador {

    private static Administrador administrador

    String nombre

    int califRapidez
    int califEstado
    int califPagina
    int cantidadCalificaciones

    static constraints = {

        nombre nullable: false
        
    }

    private Administrador(String nombre, int cantidadCalificaciones) {
        this.nombre = nombre
        this.cantidadCalificaciones = 0
        this.califRapidez = 0
        this.califPagina = 0
        this.califEstado = 0
    }

    static Administrador obtenerAdministrador(){
        if(!administrador){
            administrador = new Administrador("admin", 0);
        }
        return administrador
    }
    
    int mostrarCalificacionEstado(){

        if(cantidadCalificaciones != 0){
            return ((califEstado/cantidadCalificaciones))
        }else{
            return (califEstado)
        }

    }

    int mostrarCalificacionRapidez(){

        if(cantidadCalificaciones != 0){
            return ((califRapidez/cantidadCalificaciones))
        }else{
            return (califRapidez)
        }
    }


    int mostrarCalificacionPagina(){
        if(cantidadCalificaciones != 0){
            return ((califPagina/cantidadCalificaciones))
        }else{
            return (califPagina)
        }

    }

    //devuleve true si el pedido no se encunetra pago y si listo para entregar
    boolean pedidoEnEstadoNoPago(Pedido pedido){
        return pedido.estadoPago == EstadoDelPago.PENDIENTE_DE_PAGO && pedido.estado == EstadoPedido.LISTO_PARA_ENTREGAR
    }

    //devuleve true si la cantidad de strikes del cliente es menor a 3 
    boolean clienteConMenosDeTresStrikes(Pedido pedido){
       return pedido.cliente.strikes < 3
    }

    //devuleve true si la cantidad de strikes del cliente es igual a 3 
    boolean clienteConTresStrikes(Pedido pedido){
        return pedido.cliente.strikes == 3
    }

    //devuleve true si el pedido se encuentra entregado
    boolean pedidoEnEstadoParaVaciarCesta(Pedido pedido){
        return pedido.estado == EstadoPedido.ENTREGADO
    }
}