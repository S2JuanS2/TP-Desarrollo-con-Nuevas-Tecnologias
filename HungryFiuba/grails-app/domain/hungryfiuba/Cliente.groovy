package hungryfiuba

class Cliente {

    String nombre
    String apellido
    String identificadorTipo
    String identificadorValor
    String contrasena
    Cesta cesta


    static constraints = {

        nombre nullable: false, blank: false
        apellido nullable: false, blank: false
        identificadorTipo nullable: false, blank: false
        identificadorValor nullable: false, blank: false
        contrasena nullable: false, blank: false
        cesta nullable: true
    }


    Cliente(String nombre,String apellido,String identificadorTipo,String identificadorValor,String contrasena) {
        this.nombre = nombre
        this.apellido = apellido
        this.identificadorTipo = identificadorTipo
        this.identificadorValor = identificadorValor
        this.contrasena = contrasena
        cesta = new Cesta()
    }

    void agregarArticuloALaCesta(Articulo articulo) {
        this.cesta.agregarArticulo(articulo);
    }

    void eliminarArticuloDeLaCesta(Articulo articulo) {
        this.cesta.eliminarArticulo(articulo);
    }

}
