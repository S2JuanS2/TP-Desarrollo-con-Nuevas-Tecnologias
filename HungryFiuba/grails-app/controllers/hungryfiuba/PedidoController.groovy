package hungryfiuba

class PedidoController {

    static scaffold = Pedido
    def crearPedido(){
        Cesta cesta = New Cesta()
        Pedido pedido = New Pedido(session.cliente, cesta)
        pedido.save(failOnError: true)
    }
    def mostrarCesta() {
        if (session.cliente) {
            // Obtener la cesta del cliente - No es necesario ya esta en la sesión
            // Pasar el cliente y la cesta a la vista mostrarCesta.gsp
            render(view: "/mostrarCesta")
        } else {
            // Si no hay cliente autenticado, redirigir a la página de inicio de sesión
            render(view: "/registroFallido")
        }
    }
    
    def agregarArticulo(){

        Cliente cliente = session.cliente

        Articulo articulo = Articulo.findByNombre(params.nombre)

        if(cliente && articulo){
            //cliente.cesta.agregarArticulo(articulo)

        }
        cliente.save(flush: true)

        render(view: "/mostrarArticulos")
    }

    
}
