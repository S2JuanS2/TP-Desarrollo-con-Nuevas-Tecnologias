package hungryfiuba

class Cliente {

    String nombre
    String apellido
    String identificadorTipo
    String identificadorValor
    //LocalDate fechaDeNacimiento



    static constraints = {

        nombre nullable: false, blank: false
        apellido nullable: false, blank: false
        identificadorTipo nullable: false, blank: false
        identificadorValor nullable: false, blank: false
        //fechaDeNacimiento nullable: false, blank: false

    }
}
