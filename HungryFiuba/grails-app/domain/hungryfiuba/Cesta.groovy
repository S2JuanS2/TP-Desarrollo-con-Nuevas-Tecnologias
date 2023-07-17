package hungryfiuba

class Cesta {

    List<Articulo> articulos
    int cantidadDeArticulos

    static constraints = {

        articulos nullable: false

    }

    Cesta() {
        articulos = []
        cantidadDeArticulos = 0
    }

    void agregarArticulo(Articulo articulo) {
        articulos.add(articulo)
        cantidadDeArticulos++
    }
}
