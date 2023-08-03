package hungryfiuba

class Articulo {

    String nombre
    BigDecimal precio
    int codigo
    int stock
    String imagenUrl
    String descripcion

    static constraints = {

        nombre nullable: false
        precio nullable: false, min: 0.0
        codigo nullable: false
        stock nullable: false
        imagenUrl nullable: false
    }

    Articulo(String nombre, BigDecimal precio, int codigo, int stock,String imagenUrl, String descripcion) {
        this.nombre = nombre
        this.precio = precio
        this.codigo = codigo
        this.stock = stock
        this.imagenUrl = imagenUrl
        this.descripcion = descripcion
    }

    //
    boolean hayStock(){
        return stock > 0
    }

    void aumentarStock(){
        stock++
    }

    void reducirStock(){
        if(hayStock()){
            stock--
        }
    }

    //
    boolean superaElLimiteDeCompra(Cesta cesta){
        def precioTotal = cesta.montoTotal + this.precio
        return (precioTotal > 5000)
    }

   

}
