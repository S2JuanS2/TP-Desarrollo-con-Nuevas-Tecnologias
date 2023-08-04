package hungryfiuba

class ExcepcionController {

    def index() { }
}

class ObjetoNoExisteException extends Exception {
    
    ObjetoNoExisteException(String mensaje){
        super(mensaje)
    }
}
