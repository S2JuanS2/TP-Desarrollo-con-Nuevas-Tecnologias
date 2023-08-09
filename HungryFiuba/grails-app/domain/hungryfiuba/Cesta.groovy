package hungryfiuba

class Cesta {

    static final BigDecimal MAXIMO_COMPRA = 5000.00

    List<Articulo> articulos
    int cantidadDeArticulos
    BigDecimal montoTotal

    static hasMany = [articulos: Articulo]

    static constraints = {
        articulos nullable: true
        cantidadDeArticulos nullable: false, min: 0
        montoTotal nullable: false, min: 0.00
    }

    Cesta() {
        articulos = []
        cantidadDeArticulos = 0
        montoTotal = 0
    }

    //incrementa la cantidad de articulos
    void incrementarCantidadDeArticulos(){
        setCantidadDeArticulos(cantidadDeArticulos+1)
    }

    // disminuye la cantidad de articulos
    void disminuirCantidadDeArticulos(){
        if(tieneArticulos()){
            setCantidadDeArticulos(cantidadDeArticulos-1)
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

    void eliminarArticulo(Articulo articulo) {
        disminuirCantidadDeArticulos()
        setMontoTotal(montoTotal - articulo.precio)
        articulos.remove(articulo)
    }

    void vaciarCesta() {
        setCantidadDeArticulos(0)
        articulos.clear()
        setMontoTotal(0)
    }

    void actualizarCesta(BigDecimal precio) {
        disminuirCantidadDeArticulos()
        setMontoTotal(montoTotal - precio)
    }

    void agregarArticulo(Articulo articulo) {
        incrementarCantidadDeArticulos()
        setMontoTotal(montoTotal + articulo.precio)
        articulos.add(articulo)
    }

}
