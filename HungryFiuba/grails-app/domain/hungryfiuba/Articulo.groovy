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
}
