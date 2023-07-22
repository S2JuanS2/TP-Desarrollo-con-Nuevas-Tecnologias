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

    Pedido crearPedido() {
        //No me acuerdo la sintaxis para recuperar un artículo de la base de datos
        //Pero acá iría eso
        Articulo articulo //el que saqué de la base de datos
        //cesta.agregarArticulo(Articulo) //Acá no sé cómo se define que esto se hace hasta que no hay más artículos
        //Pedido pedido = new Pedido(this,this.cesta)
        //return pedido
    }

    void agregarArticuloALaCesta(Articulo articulo) {
        //this.cesta.agregarArticulo(articulo);
    }

    void eliminarArticuloDeLaCesta(Articulo articulo) {
        //this.cesta.eliminarArticulo(articulo);
    }

}
