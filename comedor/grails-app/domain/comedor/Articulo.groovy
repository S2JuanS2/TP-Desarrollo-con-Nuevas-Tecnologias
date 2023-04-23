package comedor

class Articulo {

    String nombre
    BigDecimal precio
    int codigo
    int stock

    static constraints = {
        nombre nullable: false
        precio nullable: false, min: 0.0
    }
}
