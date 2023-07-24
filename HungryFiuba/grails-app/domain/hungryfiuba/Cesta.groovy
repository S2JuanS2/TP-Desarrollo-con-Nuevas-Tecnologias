package hungryfiuba

class Cesta {

    static hasMany = [articulos: Articulo]
    //List<Articulo> articulos
    int cantidadDeArticulos
    

    static constraints = {

       // hasMany nullable: true

    }

    Cesta() {
        //hasMany = []
        cantidadDeArticulos = 0
    }

    void agregarArticulo(Articulo articulo) {
       cesta.addToArticulos(articulo)
       cantidadDeArticulos++
       cesta.save(flush: true)
    }


}
