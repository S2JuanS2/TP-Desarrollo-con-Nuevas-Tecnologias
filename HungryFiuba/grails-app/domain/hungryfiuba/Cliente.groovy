package hungryfiuba
enum EstadoCuenta{
    BLOQUEADA("Bloqueada"),
    NO_BLOQUEADA("Desbloqueada")

    final String descripcion

    EstadoCuenta(String descripcion){
        this.descripcion = descripcion
    }

    String toString(){
        return descripcion
    }
}
class Cliente {

    String nombre
    String apellido
    String identificadorTipo
    String identificadorValor
    String contrasena
    Cesta cesta
    EstadoCuenta estado
    int deuda
    int strikes
    int aspectoUnoSuma
    int aspectoDosSuma
    int aspectoTresSuma
    int calificacionesPendientes

    static belongsTo = [cesta: Cesta]


    static constraints = {

        nombre nullable: false, blank: false
        apellido nullable: false, blank: false
        identificadorTipo nullable: false, blank: false
        identificadorValor nullable: false, blank: false
        contrasena nullable: false, blank: false
        estado nullable: false
        deuda nullable: true
    }


    Cliente(String nombre,String apellido,String identificadorTipo,String identificadorValor,String contrasena) {
        this.nombre = nombre
        this.apellido = apellido
        this.identificadorTipo = identificadorTipo
        this.identificadorValor = identificadorValor
        this.contrasena = contrasena
        this.deuda = 0
        this.strikes = 0
        this.estado = EstadoCuenta.NO_BLOQUEADA
    }

    //devuleve true si la contrasenia es correcta 
    boolean clienteCodigoCorrecto(String contrasena){
        return this.contrasena == contrasena 
    }

    //verificar si un cliente tiene al menos un pedido en una lista de pedidos dada.
    // Si el cliente tiene al menos un pedido en la lista, la función retorna true, 
    //lo que indica que el cliente tiene al menos un pedido. Si el cliente no tiene 
    //ningún pedido en la lista, la función retorna false, lo que indica que el cliente 
    //no tiene ningún pedido registrado en la lista.
    boolean tieneUnPedido(List<Pedido> listaPedidos){
        return listaPedidos.any { pedido -> pedido.cliente.equals(this) }
    }

    //devuleve true si la cantidad de strikes del cliente es menor a 3 
    boolean tieneMenosDeTresStrikes(){
       return this.strikes < 3
    }

    //devuleve true si la cantidad de strikes del this es igual a 3 
    boolean tieneTresStrikes(){
        return this.strikes == 3
    }

    // incrementa la cantidad de strikes
    void sumarStrike(){
        strikes++
    }

    //aumenta el numero de calificaciones pendientes
    void agregarCalificacion(){
        calificacionesPendientes++
    }

    //aumenta el valor de la deuda
    void aumentarDeuda(int montoTotal){
        deuda += montoTotal
    }

    //disminuye el valor de una deuda
    void disminuirDeuda(int montoTotal){
        deuda -= montoTotal
    }

    //devuelve true si el numero de calificaciones pendientes es mayor a 0
    boolean tieneCalifacionesPendientes(){
        return this.calificacionesPendientes>0
    }

    //devuelve true si el estado de la cuenta esta en bloqueada
    boolean tieneCuentaBloqueada(){
        return this.estado == EstadoCuenta.BLOQUEADA
    }

    //bloquea la cuenta 
    void bloquearCuenta(){
        estado = EstadoCuenta.BLOQUEADA
    }

    //penaliza al cliente. En el caso de que el clinete tenga menos de 3 strikes se le aumneta en uno la cantida
    // del mismo. Caso contrario se le bloquea la cuenta 
    void penalizar(){
        if(cliente.tieneMenosDeTresStrikes()){
            cliente.sumarStrike()
        } 
        if(cliente.tieneTresStrikes()){
            cliente.bloquearCuenta()
        } 
    }
}
