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

    def incrementarCantidadDeArticulos(){
        cantidadDeArticulos++
    }

    //
    boolean tieneArticulos(Cesta cesta){
        return cesta.cantidadDeArticulos>0
    }

}
