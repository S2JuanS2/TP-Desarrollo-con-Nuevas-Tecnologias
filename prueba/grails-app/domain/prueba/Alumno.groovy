package prueba

class Alumno {

    String nombre
    String apellido
    Long padron
    String email

  static constraints = {

        nombre blank: false, nullable: false
        apellido blank: false, nullable: false
        padron nullable: false
        email email: true, nullable: false, blank: false
   }
}
