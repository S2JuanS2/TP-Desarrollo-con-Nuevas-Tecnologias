package hungryfiuba

class Articulo {

    String nombre
    BigDecimal precio
    int codigo
    int stock

    static constraints = {

        nombre nullable: false
        precio nullable: false, min: 0.0
        codigo nullable: false
        stock nullable: false

    }

    Articulo(String nombre, BigDecimal precio, int codigo, int stock) {
        this.nombre = nombre
        this.precio = precio
        this.codigo = codigo
        this.stock = stock
    }
}
