package hungryfiuba

class Cesta {

    List<Articulo> articulos
    int cantidadDeArticulos
    
    static hasMany = [articulos: Articulo]

    static constraints = {

       articulos nullable: true

    }

    Cesta() {
        articulos = []
        cantidadDeArticulos = 0
    }

    void agregarArticulo(Articulo articulo) {
       //cesta.addToArticulos(articulo)
    }

    def incrementarCantidadDeArticulos(){
        cantidadDeArticulos++
    }


}
