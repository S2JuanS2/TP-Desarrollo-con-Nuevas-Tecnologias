package hungryfiuba

class Cliente {

    String nombre
    String apellido
    String identificadorTipo
    String identificadorValor
    //LocalDate fechaDeNacimiento
    Cesta cesta


    static constraints = {

        nombre nullable: false, blank: false
        apellido nullable: false, blank: false
        identificadorTipo nullable: false, blank: false
        identificadorValor nullable: false, blank: false
        //fechaDeNacimiento nullable: false, blank: false

    }


    Cliente(String nombre,String apellido,String identificadorTipo,String identificadorValor) {
        this.nombre = nombre
        this.apellido = apellido
        this.identificadorTipo = identificadorTipo
        this.identificadorValor = identificadorValor
        cesta = new Cesta()
    }

    void agregarArticulos() {
        
    }

    Pedido crearPedido() {
       Pedido pedido = new Pedido(this) 
    }

}
