package hungryfiuba

class Cesta {

    List<Articulo> articulos
    int cantidadDeArticulos

    static constraints = {

        articulos nullable: true

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
