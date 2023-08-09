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
        codigo nullable: false, min: 0
        stock nullable: false, min: 0
        precio nullable: false, min: 0.00
        imagenUrl nullable: false
        descripcion nullable: false
    }

    Articulo(String nombre, int precio, int codigo, int stock,String imagenUrl, String descripcion) {
        
        assert precio > 0
        assert codigo > 0
        assert stock >= 0
        assert nombre != null
        assert imagenUrl != null
        assert descripcion != null

        this.nombre = nombre
        this.precio = precio
        this.codigo = codigo
        this.stock = stock
        this.imagenUrl = imagenUrl
        this.descripcion = descripcion
    }

    //devuleve true si el stock es mayor a 0
    boolean hayStock(){
        return stock > 0
    }

    void decrementarStockEnUno(){
        if(hayStock()){
            setStock(stock -1)
        }
    }

    void incrementarStockEnUno(){
        setStock(stock +1)
    }

    //verificar si agregar un artículo a la cesta hará que el precio total de la cesta supere
    // el límite de compra de 5000. Si el precio total supera el límite, la función retorna true,
    // lo que indica que agregar el artículo superaría el límite de compra. Si el precio total no 
    //supera el límite, la función retorna false, lo que indica que agregar el artículo no superaría el 
    //límite de compra y, por lo tanto, es posible agregar el artículo a la cesta sin exceder el límite.
    boolean superaElLimiteDeCompra(Cesta cesta){
        def precioTotal = cesta.montoTotal + this.precio
        return (precioTotal > Cesta.MAXIMO_COMPRA)
    }

   

}
