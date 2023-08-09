package hungryfiuba
import java.time.LocalDateTime

class Administrador {

    static final int HORA_APERTURA_COMEDOR = 8
    static final int HORA_CLAUSURA_COMEDOR = 22

    String nombre
    int califRapidez
    int califEstado
    int califPagina
    int cantidadCalificaciones

    static constraints = {

        nombre nullable: false
        califEstado nullable: false, min: 0
        califRapidez nullable: false, min: 0
        califPagina nullable: false, min: 0
        cantidadCalificaciones nullable: false, min: 0
        
    }

    Administrador(String nombre, int cantidadCalificaciones) {
        if(cantidadCalificaciones < 0){
            throw new IllegalArgumentException("La cantidad de calificaciones no puede ser menor a cero")
        }
        if(nombre == null){
            throw new NullPointerException("El nombre no puede estar vacío")
        }
        this.nombre = nombre
        this.cantidadCalificaciones = 0
        this.califRapidez = 0
        this.califPagina = 0
        this.califEstado = 0
    }

    // Calcula y retorna la calificación promedio del estado si hay calificaciones registradas.
    // Si no hay calificaciones, simplemente retorna la calificación del estado existente.
    // Retorna -1 si la cantidad de calificaciones es negativa.
    int calcularCalificacionPromedioEstado() {
        if (cantidadCalificaciones < 0) {
            throw new ArithmeticException("La cantidad de calificaciones no puede ser menor a cero");
        }
        if (cantidadCalificaciones != 0) {
            return califEstado / cantidadCalificaciones;
        } else {
            return califEstado;
        }
    }

    //calcula y retorna la calificación promedio para la rapidez si hay calificaciones
    // registradas. Si no hay calificaciones, simplemente retorna la calificación de rapidez existente.
    int calcularCalificacionPromedioRapidez(){
        if(cantidadCalificaciones < 0){
            throw new ArithmeticException("cantidad de calificaciones no puede ser menor a cero")
        }
        if(cantidadCalificaciones != 0){
            return ((califRapidez/cantidadCalificaciones))
        }else{
            return (califRapidez)
        }
    }

    //calcula y retorna la calificación promedio para la página si hay calificaciones
    // registradas. Si no hay calificaciones, simplemente retorna la calificación de la página existente.
    int calcularCalificacionPromedioPagina(){
        if(cantidadCalificaciones < 0){
            throw new ArithmeticException("cantidad de calificaciones no puede ser menor a cero")
        }
        if(cantidadCalificaciones != 0){
            return ((califPagina/cantidadCalificaciones))
        }else{
            return (califPagina)
        }
    }

    //incrementa la cantidad de calificaciones
    void aumentarCantidadCalificaciones(){
        setCantidadCalificaciones(cantidadCalificaciones + 1)
    }

    //toma las sumas de calificaciones en tres aspectos y actualiza las calificaciones 
    //y cantidades correspondientes en el objeto administrador.
    void actualizarCalificaciones(int aspectoUnoSuma, int aspectoDosSuma, int aspectoTresSuma) {
        aumentarCantidadCalificaciones()
        setCalifRapidez(califRapidez + aspectoUnoSuma)
        setCalifEstado(califEstado + aspectoDosSuma)
        setCalifPagina(califPagina + aspectoTresSuma)
    }
    
    //determina el horario de apertura del comedor
    boolean comedorAbierto(){
        LocalDateTime hora = LocalDateTime.now()
        return (hora.getHour() >= Administrador.HORA_APERTURA_COMEDOR && hora.getHour() < Administrador.HORA_CLAUSURA_COMEDOR)
    }
}