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
    BigDecimal deuda
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
        deuda nullable: false
        strikes nullable: false, min: 0
        aspectoUnoSuma nullable: false
        aspectoDosSuma nullable: false
        aspectoTresSuma nullable: false
        calificacionesPendientes nullable: false
    }


    Cliente(String nombre,String apellido,String identificadorTipo,String identificadorValor,String contrasena) {

        assert nombre != null
        assert apellido != null
        assert identificadorTipo != null
        assert identificadorValor != null
        assert contrasena != null

        this.nombre = nombre
        this.apellido = apellido
        this.identificadorTipo = identificadorTipo
        this.identificadorValor = identificadorValor
        this.contrasena = contrasena
        this.strikes = 0
        this.aspectoUnoSuma = 0
        this.aspectoDosSuma = 0
        this.aspectoTresSuma = 0
        this.calificacionesPendientes = 0
    }

    //devuleve true si la contrasenia es correcta 
    boolean contrasenaCorrecta(String contrasena){
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
    boolean estaEnCondicionesDeSeguirUsandoLaApp(){
       return this.strikes < 3
    }

    //devuleve true si la cantidad de strikes del this es igual a 3 
    boolean estaEnCondicionesDeSerBloqueado(){
        return this.strikes == 3
    }

    //incrementa la cantidad de strikes
    void sumarStrike(){
        setStrikes(strikes +1)
    }

    //aumenta el numero de calificaciones pendientes
    void agregarCalificacion(){
        setCalificacionesPendientes(calificacionesPendientes +1)
    }

    void decrementarCalificacionEnUno(){
        setCalificacionesPendientes(calificacionesPendientes -1)
    }

    //aumenta el valor de la deuda
    void aumentarDeuda(BigDecimal montoTotal){
        setDeuda(deuda + montoTotal)
    }

    //disminuye el valor de una deuda
    void disminuirDeuda(BigDecimal montoTotal){
        setDeuda(deuda - montoTotal)
    }

    //devuelve true si el numero de calificaciones pendientes es mayor a 0
    boolean tieneCalifacionesPendientes(){
        return calificacionesPendientes>0
    }

    //devuelve true si el estado de la cuenta esta en bloqueada
    boolean tieneCuentaBloqueada(){
        return estado == EstadoCuenta.BLOQUEADA
    }

    //bloquea la cuenta 
    void bloquearCuenta(){
        if(estado != EstadoCuenta.BLOQUEADA) {
            estado = EstadoCuenta.BLOQUEADA
        }
    }

    //penaliza al cliente. En el caso de que el clinete tenga menos de 3 strikes se le aumneta en uno la cantida
    // del mismo. Caso contrario se le bloquea la cuenta 
    void penalizar(){
        if(estaEnCondicionesDeSeguirUsandoLaApp()){
            sumarStrike()
        } 
        if(estaEnCondicionesDeSerBloqueado()){
            bloquearCuenta()
        } 
    }

    def desbloquearCuenta() {
        setDeuda(0)
        setEstado(EstadoCuenta.NO_BLOQUEADA)
        setStrikes(0)
    }
}
