package hungryfiuba

class Cesta {

    static final int MAXIMO_COMPRA = 5000

    List<Articulo> articulos
    int cantidadDeArticulos
    int montoTotal

    static hasMany = [articulos: Articulo]

    static constraints = {

       articulos nullable: true
       cantidadDeArticulos nullable: false, min: 0
       montoTotal nullable: false, min: 0

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
        if(tieneArticulos()){
            cantidadDeArticulos--
        }
    }

    //devuelve true si la cantidad de articulos de la cesta es mayor a 0 
    boolean tieneArticulos( ){
        return (cantidadDeArticulos>0)
    }

    //actualiza el monto total de la cesta
    void actualizarMontoTotal(BigDecimal precio){
        montoTotal += precio
    }

}
