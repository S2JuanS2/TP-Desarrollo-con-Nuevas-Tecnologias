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

    //obtiene una única instancia del objeto Administrador y garantizar que 
    //exista solo una instancia del Administrador en toda la aplicación.
    static Administrador obtenerAdministrador(){
        if(!administrador){
            administrador = new Administrador("admin", 0);
        }
        return administrador
    }
    
    //calcula y retorna la calificación promedio para el estado si hay calificaciones
    // registradas. Si no hay calificaciones, simplemente retorna la calificación del estado existente.
    int mostrarCalificacionEstado(){
        if(cantidadCalificaciones != 0){
            return ((califEstado/cantidadCalificaciones))
        }else{
            return (califEstado)
        }
    }

    //calcula y retorna la calificación promedio para la rapidez si hay calificaciones
    // registradas. Si no hay calificaciones, simplemente retorna la calificación de rapidez existente.
    int mostrarCalificacionRapidez(){
        if(cantidadCalificaciones != 0){
            return ((califRapidez/cantidadCalificaciones))
        }else{
            return (califRapidez)
        }
    }

    //calcula y retorna la calificación promedio para la página si hay calificaciones
    // registradas. Si no hay calificaciones, simplemente retorna la calificación de la página existente.
    int mostrarCalificacionPagina(){
        if(cantidadCalificaciones != 0){
            return ((califPagina/cantidadCalificaciones))
        }else{
            return (califPagina)
        }
    }

    //incrementa la cantidad de calificaciones
    void aumentarCantidadCalificaciones(){
        cantidadCalificaciones++
    }
    
}