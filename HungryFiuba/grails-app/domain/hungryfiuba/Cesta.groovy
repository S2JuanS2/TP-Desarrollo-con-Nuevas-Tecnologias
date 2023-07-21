package hungryfiuba

class Cesta {

    List<Articulo> articulos
    int cantidadDeArticulos

    static constraints = {

        articulos nullable: false

    }



    Cesta() {
        articulos = []
        cantidadDeArticulos = 
    }

//En estos hay que recibir el artículo? O el id y buscarlo por id en la base de datos?
    void agregarArticulo(Articulo articulo) {
        articulos.add(articulo)
        cantidadDeArticulos++
    }

    void eliminarArticulo(Articulo articulo) {
        articulos.remove(articulo) //Es remove?
        cantidadDeArticulos--
    }
}
