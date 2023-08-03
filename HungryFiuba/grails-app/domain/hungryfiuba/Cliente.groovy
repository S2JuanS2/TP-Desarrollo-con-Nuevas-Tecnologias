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

    //
    boolean clienteExisteEnPedidos(List<Pedido> lista ){
        def listaPedidos = lista
        return listaPedidos.any { pedido -> pedido.cliente.equals(this) }
    }

    //devuleve true si la cantidad de strikes del cliente es menor a 3 
    boolean clienteConMenosDeTresStrikes(){
       return this.strikes < 3
    }

    //devuleve true si la cantidad de strikes del this es igual a 3 
    boolean clienteConTresStrikes(){
        return this.strikes == 3
    }

    //
    boolean califacionesPendientes(){
        return this.calificacionesPendientes>0
    }

    //
    boolean cuentaBloqueada(){
        return this.estado != EstadoCuenta.BLOQUEADA
    }
}
