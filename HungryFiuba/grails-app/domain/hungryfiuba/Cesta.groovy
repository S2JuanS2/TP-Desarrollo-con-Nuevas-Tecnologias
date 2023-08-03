package hungryfiuba

class Cesta {

    List<Articulo> articulos
    int cantidadDeArticulos
    int montoTotal

    static hasMany = [articulos: Articulo]

    static constraints = {

       articulos nullable: true

    }

    Cesta() {
        articulos = []
        cantidadDeArticulos = 0
        montoTotal = 0
    }

    //incrementa la cantidad de articulos
    void incrementarCantidadDeArticulos(){
        cantidadDeArticulos++
    }

    // disminuye la cantidad de articulos
    void disminuirCantidadDeArticulos(){
        cantidadDeArticulos--
    }

    //devuelve true si la cantidad de articulos de la cesta es mayor a 0 
    boolean tieneArticulos( ){
        return this.cantidadDeArticulos>0
    }

    //actualiza el monto total de la cesta
    void actualizarMontoTotal(BigDecimal precio){
        montoTotal += precio
    }

}
