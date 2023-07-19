package hungryfiuba

class Administrador {

    String nombre

    static constraints = {

        nombre nullable: false
        
    }

    Administrador(String nombre) {
        this.nombre = nombre
    }

    Articulo cargarStock(String nombre, BigDecimal precio, int codigo, int stock){
        Articulo articulo = new Articulo(nombre,precio,codigo,stock)
        articulo.save() //Para guardar en la base de datos? xd ni idea
    }
}
