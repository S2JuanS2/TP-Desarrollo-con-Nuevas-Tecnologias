package hungryfiuba
enum EstadoCuenta{
    BLOQUEADA,
    NO_BLOQUEADA
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
        this.estadoCuenta = EstadoCuenta.NO_BLOQUEADA
    }
    
}
