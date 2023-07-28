package hungryfiuba

class Administrador {

    String nombre
    int cantidadCalificaciones

    static constraints = {

        nombre nullable: false
        
    }

    Administrador(String nombre, int cantidadCalificaciones) {
        this.nombre = nombre
        this.cantidadCalificaciones = 0
    }
}
