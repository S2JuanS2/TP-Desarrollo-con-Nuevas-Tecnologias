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
    

    void agregarArticulo(Articulo articulo) {
       //cesta.addToArticulos(articulo)
    }

    def incrementarCantidadDeArticulos(){
        cantidadDeArticulos++
    }


}
