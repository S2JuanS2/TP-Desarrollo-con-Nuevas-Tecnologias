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

    void incrementarCantidadDeArticulos(){
        cantidadDeArticulos++
    }

    void disminuirCantidadDeArticulos(){
        cantidadDeArticulos--
    }

    //
    boolean tieneArticulos( ){
        return this.cantidadDeArticulos>0
    }

    void actualizarMontoTotal(BigDecimal precio){
        montoTotal += precio
    }

}
