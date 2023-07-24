package hungryfiuba

class Cesta {

    List<Articulo> articulos
    int cantidadDeArticulos
    Cliente cliente  //relación uno a uno

    static constraints = {

        articulos nullable: true
        cliente nullable: false, unique: true

    }

    Cesta() {
        articulos = []
        cantidadDeArticulos = 0
    }

    void agregarArticulo(Articulo articulo) {
       articulos.add(articulo)
        cantidadDeArticulos++
    }

    void eliminarArticulo(Articulo articulo) {
        articulos.remove(articulo)
        cantidadDeArticulos--
    }
}
