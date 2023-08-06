package hungryfiuba

import grails.gorm.transactions.Transactional


class CestaService {

    // permite al cliente agregar un artículo a su cesta de compras. 
    //Al realizar esta acción, se actualiza la cantidad de artículos, 
    //el monto total y el stock disponible en la base de datos. 
    @Transactional
    def agregarArticuloACesta(long articuloId, long clienteId) {
        Cliente cliente = Cliente.get(clienteId)
        Articulo articulo = Articulo.get(articuloId)

        //cliente.cesta.cantidadDeArticulos++
        articulo.stock--
        //cliente.cesta.montoTotal += articulo.precio
        //cliente.cesta.articulos.add(articulo)
        cliente.cesta.agregarArticulo(articulo)
        cliente.cesta.save(flush: true)
    }

    //agrega un artículo a la cesta de un cliente siempre que el artículo 
    //tenga suficiente stock disponible y no supere el límite de compra 
    //establecido para la cesta. Si ambas condiciones se cumplen, el 
    //artículo se agrega a la cesta del cliente utilizando la función agregarArticuloACesta().
    @Transactional
    def agregarACesta(Articulo articulo, Cesta cesta, long cliente){
        if(articulo.hayStock() && !articulo.superaElLimiteDeCompra(cesta)){
            agregarArticuloACesta(articulo.id, cliente)
        }
    }

    //permite al cliente eliminar un artículo de su cesta de compras. 
    //Al realizar esta acción, se actualiza la cantidad de artículos, 
    //el monto total y el stock disponible en la base de datos.
    @Transactional
    def eliminarArticuloACesta(long articuloId, long clienteId) {
        Cliente cliente = Cliente.get(clienteId)
        Articulo articulo = Articulo.get(articuloId)
        
        articulo.stock++
        cliente.cesta.eliminarArticulo(articulo)
        cliente.cesta.save(flush: true)
    }

    //permite al cliente vaciar completamente su cesta de compras. 
    //Al realizar esta acción, se actualiza la cantidad de artículos, 
    //el monto total, el stock disponible y la deuda del cliente en la base de datos. 
    @Transactional
    def vaciarCesta(long clienteId){
        Cliente cliente = Cliente.get(clienteId)
        Cesta cesta = cliente.cesta
        Articulo articuloEnStock
        cesta.articulos.each{ articulo ->
            articuloEnStock = Articulo.get(articulo.id)
            articuloEnStock.stock++
            cliente.disminuirDeuda(articuloEnStock.precio)
            cesta.actualizarCesta(articuloEnStock.precio)
        }
        cliente.cesta.vaciarCesta()
        cliente.cesta.save(flush: true)
    }

    //permite al cliente vaciar completamente su cesta de compras después de que se haya finalizado un pedido.
    // Al realizar esta acción, se actualiza la cantidad de artículos y el monto total de la cesta del cliente 
    //en la base de datos. 
    @Transactional
    def vaciarCestaDePedidoFinalizado(long clienteId){
        Cliente cliente = Cliente.get(clienteId)
        Cesta cesta = cliente.cesta
        Articulo articuloEnStock
        cesta.articulos.each{ articulo ->
            articuloEnStock = Articulo.get(articulo.id)
            cesta.actualizarCesta(articuloEnStock.precio)
        }
        cliente.cesta.vaciarCesta()
        //cliente.cesta.articulos.clear()
        cliente.cesta.save(flush: true)
    }
}
